package service;


import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * interface for work with validation inputs data
 */
public interface VerifiableData {
    /**
     * returns list errors if user input wrong data
     * @param dataWithForm data with form
     * @param lang resource bundle for for internationalization error message
     * @return list errors if user input wrong data or else return empty list
     */
    List<String> validate(Map<String, String[]> dataWithForm, ResourceBundle lang);
 }
