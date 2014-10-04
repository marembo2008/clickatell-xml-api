package com.anosym.clickatell.xml.api;

import java.util.List;

/**
 *
 * @author marembo
 */
public interface ClickatellXmlApiService {

    boolean sendSms(List<String> toPhoneNumbers, String message);

    boolean sendSms(String message, String toPhoneNumber);
}
