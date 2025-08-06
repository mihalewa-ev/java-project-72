package hexlet.code.datatemplate.paths;

import hexlet.code.datatemplate.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import hexlet.code.model.CheckModel;
import hexlet.code.model.UrlModel;

import java.util.List;


@Getter
@AllArgsConstructor
public class UrlPage extends BasePage {
    private UrlModel url;
    private List<CheckModel> checks;
}
