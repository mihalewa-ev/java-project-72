package hexlet.code.datatemplate.paths;

import hexlet.code.datatemplate.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import hexlet.code.model.UrlModel;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListPage extends BasePage {
    private List<UrlModel> urlList;
}
