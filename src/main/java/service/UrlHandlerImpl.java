package service;

public class UrlHandlerImpl implements UrlHandler {
    private static final UrlHandlerImpl urlHandler = new UrlHandlerImpl();

    public static UrlHandlerImpl getInstance(){
        return urlHandler;
    }

    public UrlHandlerImpl() {
    }

    public Long getIdFromUrl(String url){
       return Long.valueOf(url.split("/")[3]);
    }
}
