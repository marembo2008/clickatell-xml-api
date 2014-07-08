/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anosym.clickatell.xml.api;

/**
 *
 * @author marembo
 */
public interface ClickatellXmlApiConfigurationService {

    String getApiId();

    String getUsername();

    String getPassword();

    String getXmlApiUrl();

    String getFromNumber();
}
