package service;

import java.util.List;
import java.util.Map;

public interface CheckDataWithFormService {
    List<String> checkData(Map<String, String[]> dataWithForm, long creditCardId);
}
