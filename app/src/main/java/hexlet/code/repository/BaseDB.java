package hexlet.code.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Setter
public class BaseDB {
    public static HikariDataSource dataSource;

    public static String getTime(Timestamp timestamp) {
        var formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(timestamp.getTime());
    }

}
