package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CheckModel {
    private Long        id;
    private int         statusCode;
    private String      title;
    private String      h1;
    private String      description;
    private Long        urlId;
    private Timestamp   createdAt;

    public CheckModel(Long urlId) {
        this.urlId = urlId;
    }
}
