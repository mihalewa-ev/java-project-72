package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UrlModel {
    private Long        id;
    private String      name;
    private Timestamp   created;
    private Timestamp   lastCheckTime;
    private Integer     statusCode;

    public UrlModel(String name) {
        this.name = name;
    }
}
