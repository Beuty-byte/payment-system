package service;

import domain.CreditCard;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public interface VerifiableData {
    List<String> validate(Map<String, String[]> dataWithForm, ResourceBundle lang);
 }
