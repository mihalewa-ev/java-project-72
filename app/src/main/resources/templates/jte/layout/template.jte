@import gg.jte.Content
@import hexlet.code.datatemplate.BasePage
@import hexlet.code.utils.NamedRoutes

@param Content content
@param BasePage data = null

<!doctype html>
    <html lang="en">
        <head>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
            <title>Hexlet forth project</title>
        </head>
<%--        <body class="d-flex flex-column min-vh-100">--%>
<%--        @if(data != null && data.getMsg() != null)--%>
<%--            <div class="alert alert-primary" role="alert">--%>
<%--                <p>${data.getMsg()}</p>--%>
<%--            </div>--%>
<%--        @endif--%>
<%--        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">--%>
<%--            <div class="container-fluid">--%>
<%--                <ul class="navbar-nav">--%>
<%--                    <li class="container-fluid">--%>
<%--                        <a class="navbar-brand" href="/">Анализатор страниц</a>--%>
<%--                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"--%>
<%--                                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                            <span class="navbar-toggler-icon"></span>--%>
<%--                        </button>--%>
<%--                    </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link active" href="${NamedRoutes.root()}">Главная</a>--%>
<%--                        </li>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link" href="${NamedRoutes.urlList()}">Список страниц</a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--        </nav>--%>
<%--        <main>--%>
<%--            ${content}--%>
<%--        </main>--%>
<%--        </body>--%>
<%--    </html>--%>

        <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Анализатор страниц</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <div class="navbar-nav">
                        <a class="nav-link" href="${NamedRoutes.root()}">Главная</a>
                        <a class="nav-link" href="${NamedRoutes.urlList()}">Сайты</a>
                    </div>
                </div>
            </div>
        </nav>

        <main class="flex-grow-1">

            <section>
                @if(data != null && data.getMsg() != null)
                    <div class="alert alert-primary alert-dismissible" role="alert">
                        <p>${data.getMsg()}</p>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">
                        </button>
                    </div>
                @endif
                ${content}
            </section>
        </main>

        <footer class="footer border-top py-3 mt-5 bg-light">
            <div class="container-xl">
                <div class="text-center">
                    created by
                    <a href="https://ru.hexlet.io" target="_blank">Hexlet</a>
                </div>
            </div>
        </footer>
        </body>
</html>
