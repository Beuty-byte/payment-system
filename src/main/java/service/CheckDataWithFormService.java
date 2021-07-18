package service;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * interface for checking data with form
 */
public interface CheckDataWithFormService {
    /**
     * return list errors validation
     * @param dataWithForm map with input values
     * @param creditCardId credit card id
     * @param lang resource bundle for translate errors
     * @return lists with errors if they there are or else return empty list
     */
    List<String> checkData(Map<String, String[]> dataWithForm, long creditCardId, ResourceBundle lang);
}
