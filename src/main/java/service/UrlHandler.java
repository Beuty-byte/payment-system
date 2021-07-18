package service;

/**
 * interface for work with url handler
 */
public interface UrlHandler {
    /**
     * returns id from url in number format
     * @param url url
     * @return id form url in number format
     */
    Long getIdFromUrl(String url);
}
