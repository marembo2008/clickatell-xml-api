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

    boolean isSmsSimulation();
}
