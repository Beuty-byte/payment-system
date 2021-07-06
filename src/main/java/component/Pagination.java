package component;

import java.util.List;
import java.util.Optional;

public class Pagination {
    private final int max = 6;
    private final String currentUri;
    private final String index = "page";
    private int currentPage;
    private final int amount;
    private final int total;
    private final int limit;
    private final String sortBy;

    public Pagination(int currentPage, int total, int limit, String currentUri, Optional<String> sortBy) {
        this.total = total;
        this.limit = limit;
        this.amount = amount();
        this.currentUri = currentUri;
        setCurrentPage(currentPage);
        this.sortBy = sortBy.orElse(null);
    }

    public String get(){
        StringBuilder links = new StringBuilder();
        List<Integer> limits = limits();
        String html = "<ul class=\"pagination\">";

        for(int page = limits.get(0); page <= limits().get(1); page++){
            if(page == currentPage){
                links.append("<li class=\"active\"><a href=\"#\">").append(page).append("</a></li>");
            }else {
                links.append(generateHtml(page, null));
            }
        }

        if(links.length() > 0){
            if(currentPage > 1){
                links.insert(0, generateHtml(1, "&lt;"));
            }

            if(currentPage < amount){
                links.append(generateHtml(amount, "&gt;"));
            }
        }
        html += links + "</ul>";
        return html;
    }

    private String generateHtml(int page, String text){
        if(text == null){
            text = Integer.toString(page);
        }

        if(sortBy == null) {
            return "<li><a href=\"" + currentUri + "?" + index + "=" + page + "\">" + text + "</a></li>";
        }
        return "<li><a href=\"" + currentUri + "?" + index + "=" + page + "&sort="+ sortBy +"\">" + text + "</a></li>";
    }

    private void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
        if(this.currentPage > 0){
            if(this.currentPage > this.amount){
                this.currentPage = this.amount;
            }
        }else {
            this.currentPage = 1;
        }
    }

    private List<Integer> limits(){
        int left = currentPage - (int) Math.round((double) max / 2);
        int start = left > 0 ? left : 1;
        int end = 0;
        if(start + max <= amount){
            end = start > 1 ? start + max : max;
        }else {
            end = amount;
            start = amount - max > 0 ? amount - max : 1;
        }
        return List.of(start,end);
    }

    private int amount(){
        return (int) Math.ceil((double) total / limit);
    }
}
