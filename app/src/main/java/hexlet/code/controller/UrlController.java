package hexlet.code.controller;

import hexlet.code.datatemplate.BasePage;
import hexlet.code.datatemplate.paths.ListPage;
import hexlet.code.datatemplate.paths.UrlPage;
import hexlet.code.model.UrlModel;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import lombok.SneakyThrows;
import hexlet.code.repository.CheckRepository;
import hexlet.code.repository.UrlRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {

    public static void showList(Context ctx) {
        var inputData = new ListPage(UrlRepository.withLatestCheck());
        ctx.render("paths/UrlList.jte", model("urlListData", inputData));
    }

    @SneakyThrows
    public static void showURL(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id)
                    .orElseThrow(() -> new NotFoundResponse("No url with provided Id"));
        var checks = CheckRepository.findAllByUrlId(url.getId());
        var inputData = new UrlPage(url, checks);
        ctx.render("paths/urlDetails.jte", model("urlDetails", inputData));
    }

    @SneakyThrows
    public static void getNewURL(Context ctx) {
        String message = "";
        try {
            if (ctx.formParam("url").isEmpty() || ctx.formParam("url").isEmpty()) {
                throw new NullPointerException();
            }
            URL url = new URI(ctx.formParam("url")).toURL();
            String protocol = url.getProtocol() + "://";
            String host = url.getHost();
            int port = url.getPort();
            String urlPath = port == -1 ? protocol + host
                    : protocol + host + ":" + port;
            if (UrlRepository.findByName(urlPath) == null) {
                UrlRepository.addURL(new UrlModel(urlPath));
                message = "Страница успешно добавлена";
                var inputData = new ListPage(UrlRepository.getAll());
                inputData.setMsg(message);
                ctx.render("paths/UrlList.jte", model("urlListData", inputData));
            } else {
                message = "Страница уже существует";
                throw new IllegalAccessException();
            }

        } catch (URISyntaxException | NullPointerException | IllegalArgumentException | MalformedURLException e) {
            var inputData = new BasePage();
            inputData.setMsg("Неверный адрес");
            ctx.render("index.jte", model("data", inputData));

        } catch (IllegalAccessException e) {
            var inputData = new BasePage();
            inputData.setMsg(message);
            ctx.render("index.jte", model("data", inputData));

        }

//            ctx.sessionAttribute("msg", message);
//            var inputData = new ListPage(UrlRepository.getAll());
//            inputData.setMsg(ctx.consumeSessionAttribute("msg"));
//            ctx.render("paths/UrlList.jte", model("urlListData", inputData));
    }
}
