package hexlet.code.controller;

import hexlet.code.datatemplate.BasePage;
import io.javalin.http.Context;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {

    public static void showRoot(Context ctx) throws SQLException {
        var inputData = new BasePage();
        inputData.setMsg(ctx.consumeSessionAttribute("msg"));
        ctx.render("index.jte", model("data", inputData));
    }

}
