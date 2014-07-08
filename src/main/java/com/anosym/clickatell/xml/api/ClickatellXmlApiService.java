/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
