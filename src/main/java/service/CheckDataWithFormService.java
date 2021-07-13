package service;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public interface CheckDataWithFormService {
    List<String> checkData(Map<String, String[]> dataWithForm, long creditCardId, ResourceBundle lang);
}
