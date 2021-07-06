package service;

import domain.CreditCard;

import java.util.List;
import java.util.Map;

public interface VerifiableData {
    List<String> validate(Map<String, String[]> dataWithForm);
 }
