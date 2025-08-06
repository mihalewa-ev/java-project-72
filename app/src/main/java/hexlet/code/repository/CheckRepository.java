package hexlet.code.repository;

import hexlet.code.model.CheckModel;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckRepository extends BaseDB {
    protected static List<CheckModel> checks = new ArrayList<CheckModel>();

    public static void addCheck(CheckModel check) throws SQLException {
        String query =
                "INSERT INTO url_checks (url_id, status_code, title, h1, description, created_at) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, check.getUrlId());
            preparedStatement.setInt(2, check.getStatusCode());
            preparedStatement.setString(3, check.getTitle());
            preparedStatement.setString(4, check.getH1());
            preparedStatement.setString(5, check.getDescription());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                check.setId(generatedKeys.getLong(1));
//                if (dataSource.getJdbcUrl().startsWith("jdbc:h2")) {        //  тут костыль, потому, что h2 возвращает
//                    check.setCreatedAt(generatedKeys.getTimestamp(2));   //  в generatedKeys только сгенерированные
//                } else {                                                    //  поля, а postgre - все
//                    check.setCreatedAt(generatedKeys.getTimestamp(7));
//                }
            } else {
                throw new SQLException("Database have not returned an id or createdAt after saving an entity");
            }
            checks.add(check);
        }
    }

    public static List<CheckModel> findAllByUrlId(Long urlId) throws SQLException {
        String query =  "SELECT * FROM url_checks "
                +       "WHERE url_id = " + urlId
                +       " ORDER BY id DESC;";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {
            var checksList = preparedStatement.executeQuery();
            var result = new ArrayList<CheckModel>();
            while (checksList.next()) {
                var check = new CheckModel(checksList.getLong("url_id"));
                check.setId(checksList.getLong("id"));
                check.setTitle(checksList.getString("title"));
                check.setH1(checksList.getString("h1"));
                check.setDescription(checksList.getString("description"));
                check.setStatusCode(checksList.getInt("status_code"));
                check.setCreatedAt(checksList.getTimestamp("created_at"));
                result.add(check);
            }
            return result;
        }
    }

    public static CheckModel findByUrlId(Long urlId) {
        var result = checks.stream()
                .filter(check -> Objects.equals(check.getUrlId(), urlId))
                .findAny()
                .get();
        return result;
    }
}
