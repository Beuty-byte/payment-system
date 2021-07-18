package service;

import java.util.Map;

/**
 * interface for work with register user
 */
public interface Registered {
    /**
     * method for register user in system
     * @param parameterMap Map with inputs parameters with form
     * @return true if registration was it's successful or else false
     */
    boolean registerInSystem(Map<String, String[]> parameterMap);
}
