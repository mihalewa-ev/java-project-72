package hexlet.code.utils;

public class NamedRoutes {

    public static String root() {
        return "/";
    }

    public static String urlList() {
        return "/urls";
    }

    public static String urlPath(Long id) {
        return urlPath(String.valueOf(id));
    }
    public static String urlPath(String id) {
        return "/urls/" + id;
    }

    public static String checkPath(Long id) {
        return checkPath(String.valueOf(id));
    }
    public static String checkPath(String id) {
        return "/urls/" + id + "/checks";
    }
}
