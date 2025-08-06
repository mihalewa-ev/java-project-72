package hexlet.code.controller;

import hexlet.code.datatemplate.paths.UrlPage;
import io.javalin.http.Context;

import io.javalin.http.NotFoundResponse;
import kong.unirest.Unirest;
import hexlet.code.model.CheckModel;
import hexlet.code.model.UrlModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import hexlet.code.repository.CheckRepository;
import hexlet.code.repository.UrlRepository;
import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class ChecksController {

    public static void check(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        UrlModel url = UrlRepository.findById(urlId)
                .orElseThrow(() -> new NotFoundResponse("No such url"));
        var check = new CheckModel(urlId);
        String message = "Проверка пройдена";
        try {
            var urlPath = url.getName();
            var response = Unirest.get(urlPath).asString();
            int statusCode = response.getStatus();
            String responseBody = response.getBody();
            check.setStatusCode(statusCode);
            Document document = Jsoup.parse(responseBody);
            check.setTitle(document.title());
            check.setH1(parseH1(document));
            check.setDescription(parseDescription(document));
            CheckRepository.addCheck(check);
            ctx.sessionAttribute("msg", message);
        } catch (Exception e) {
            message = "Ошибка при обращении к странице - " + url.getName();
            ctx.sessionAttribute("msg", message);
        } finally {
            var inputData = new UrlPage(url, CheckRepository.findAllByUrlId(urlId));
            inputData.setMsg(ctx.consumeSessionAttribute("msg"));
            ctx.render("paths/urlDetails.jte", model("urlDetails", inputData));
        }
    }

    private static String parseH1(Document body) {
        var h1 = body.select("h1").isEmpty() ? "" : body.selectFirst("h1").text();
        return h1;
    }

    private static String parseDescription(Document body) {
        var description = ((body.select("meta").isEmpty())
            || body.select("meta[name=description]").hasAttr("content"))
            ? body.selectFirst("meta[name=description]").attr("content") : "";
        return description;
    }
}
