package hexlet.code.repository;

import lombok.SneakyThrows;
import hexlet.code.model.UrlModel;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UrlRepository extends BaseDB {

    protected static List<UrlModel> urls = new ArrayList<UrlModel>();


    public static void addURL(UrlModel url) throws SQLException {
        String query = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        var date = new Date();
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, url.getName());
            preparedStatement.setTimestamp(2, new Timestamp(date.getTime()));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong(1));
                if (dataSource.getJdbcUrl().startsWith("jdbc:h2")) {    //  тут костыль, потому, что h2 возвращает
                    url.setCreated(generatedKeys.getTimestamp(2));  //  в generatedKeys только сгенерированные
                } else {                                                //  поля, а postgre - все
                    url.setCreated(generatedKeys.getTimestamp(3));
                }
            } else {
                throw new SQLException("Database have not returned an id or createdAt after saving an entity");
            }
            urls.add(url);
        }
    }

    @SneakyThrows
    public static Optional<UrlModel> findById(Long id) {
        String query = "SELECT * FROM urls WHERE id = (?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            var urlList = preparedStatement.executeQuery();
            if (urlList.next()) {
                var url = new UrlModel(urlList.getString("name"));
                url.setCreated(urlList.getTimestamp("created_at"));
                url.setId(id);
                return Optional.of(url);
            }
            return Optional.empty();
        }
    }

    public static List<UrlModel> getAll() throws SQLException {
        String query = "SELECT * FROM urls;";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {
            var urlList = preparedStatement.executeQuery();
            var result = new ArrayList<UrlModel>();
            while (urlList.next()) {
                var url = new UrlModel(urlList.getString("name"));
                url.setId(urlList.getLong("id"));
                url.setCreated(urlList.getTimestamp("created_at"));
                result.add(url);
            }
            return result;
        }
    }

    public static UrlModel findByName(String address) {
        UrlModel result = null;
        try {
            result = urls.stream()
                    .filter(entity -> entity.getName().equals(address))
                    .toList()
                    .getFirst();
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public static List<UrlModel> withLatestCheck() {
        var result = new ArrayList<UrlModel>();
        String query = "SELECT DISTINCT ON (urls.id)"
                + "                    urls.id, "
                + "                    urls.name, "
                + "                    urls.created_at AS created, "
                + "                    url_checks.created_at AS last_check, "
                + "                    url_checks.status_code  AS status_code"
                + "                FROM urls "
                + "                LEFT JOIN url_checks ON "
                + "                    (url_checks.url_id = urls.id)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(query)) {
            var urlList = preparedStatement.executeQuery();
            while (urlList.next()) {
                var url = new UrlModel(urlList.getString("name"));
                url.setId(urlList.getLong("id"));
                url.setCreated(urlList.getTimestamp("created"));
                url.setLastCheckTime(urlList.getTimestamp("last_check"));
                url.setStatusCode(urlList.getInt("status_code"));
                result.add(url);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
