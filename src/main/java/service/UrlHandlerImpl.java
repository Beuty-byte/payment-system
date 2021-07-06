package service;

public class UrlHandlerImpl implements UrlHandler {
    private static final UrlHandlerImpl urlHandler = new UrlHandlerImpl();

    public static UrlHandlerImpl getInstance(){
        return urlHandler;
    }

    public UrlHandlerImpl() {
    }

    public Long getIdFromUrl(String url){
        Long paymentId = null;
        try {
            paymentId = Long.valueOf(url.split("/")[3]);
        }catch (NumberFormatException e){
            System.out.println("wrong format");
        }
        return paymentId;
    }
}
