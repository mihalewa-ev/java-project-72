@import hexlet.code.datatemplate.BasePage
@import hexlet.code.utils.NamedRoutes

@param  BasePage data

@template.layout.template(
    data = data,
    content = @`
<%--        <div style="background-color: #14181c; color: #f0f2f4">--%>
<%--                <h2>Анализ страниц на наличие параметров для SE </h2>--%>
<%--                <hr>--%>
<%--                <form action="${hexlet.code.utils.NamedRoutes.urlList()}" method="post" class = "m-2 p-2">--%>
<%--                    <div>--%>
<%--                        <label for="urlInput" class="form-label">--%>
<%--                            Имя--%>
<%--                            <input class="form-control" placeholder="url" id="urlInput" aria-describedby="emailHelp" type="url" name="url"/>--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                    <input type="submit" class="btn btn-primary" value="Добавить адрес страницы" />--%>
<%--                </form>--%>
<%--                <hr>--%>
<%--        </div>--%>
    <div class="container-fluid bg-dark p-5">
        <div class="row">
            <div class="col-md-10 col-lg-8 mx-auto text-white">
                <h1 class="display-3 mb-0">Анализатор страниц</h1>
                <p class="lead">Бесплатно проверяйте сайты на SEO пригодность</p>
                <form action="${hexlet.code.utils.NamedRoutes.urlList()}" method="post" class="rss-form text-body">
                    <div class="row">
                        <div class="col">
                            <div class="form-floating">
                                <input id="url-input" autofocus type="text" required name="url" aria-label="url"
                                       class="form-control w-100" placeholder="ссылка" autocomplete="off">
                                <label for="url-input">Ссылка</label>
                            </div>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="h-100 btn btn-lg btn-primary px-sm-5">Проверить</button>
                        </div>
                    </div>
                </form>
                <p class="mt-2 mb-0">Пример: https://www.example.com</p>
            </div>
        </div>
    </div>
    <a href="${NamedRoutes.urlList()}">Список страниц</a>
    `
)