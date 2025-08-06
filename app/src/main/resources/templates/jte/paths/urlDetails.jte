@import hexlet.code.datatemplate.paths.UrlPage
@import hexlet.code.model.CheckModel
@import hexlet.code.repository.CheckRepository
@import hexlet.code.utils.NamedRoutes

@param UrlPage urlDetails

@template.layout.template(
    data = urlDetails,
content = @`
    <div>
        <h1>Сайт: ${urlDetails.getUrl().getName()}</h1>
        <table class="table-primary table table-striped-columns">
            <tbody>
            <tr>
                <td>ID</td>
                <td>${urlDetails.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Адрес</td>
                <td>${urlDetails.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${CheckRepository.getTime(urlDetails.getUrl().getCreated())}</td>
            </tr>
            </tbody>
        </table>
        <hr>
            <form action="${NamedRoutes.checkPath(urlDetails.getUrl().getId())}" method="post">
                <input class="btn btn-primary" type="submit" value="Проверить" />
            </form>
        <hr>
        @if(!urlDetails.getChecks().isEmpty())
            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th>Дата проверки</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    @for (CheckModel check : urlDetails.getChecks())
                    <td>${check.getId()}</td>
                    <td>${check.getStatusCode()}</td>
                    <td>${check.getTitle()}</td>
                    <td>${check.getH1()}</td>
                    <td>${check.getDescription()}</td>
                    <td>${CheckRepository.getTime(check.getCreatedAt())}</td>
                </tr>
                @endfor
                </tbody>
            </table>
        @endif
        <div class="row">
            <a class="col" href="${NamedRoutes.root()}">На главную</a>  <a class="col" href="${NamedRoutes.urlList()}">К списку</a>
        </div>

    </div>
`
)