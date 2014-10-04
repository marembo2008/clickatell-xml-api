package com.anosym.clickatell.xml.api.impl;

import com.anosym.vjax.annotations.IgnoreGeneratedAttribute;
import com.anosym.vjax.annotations.Markup;
import com.anosym.vjax.annotations.NoNamespace;

/**
 *
 * @author marembo
 */
@Markup(name = "clickAPI")
@IgnoreGeneratedAttribute
@NoNamespace
public class ClickatellApi {

    private ClickatellSmsData clickatellSmsData;

    public ClickatellApi(ClickatellSmsData clickatellSmsData) {
        this.clickatellSmsData = clickatellSmsData;
    }

    @Markup(name = "sendMsg")
    public ClickatellSmsData getClickatellSmsData() {
        return clickatellSmsData;
    }

    public void setClickatellSmsData(ClickatellSmsData clickatellSmsData) {
        this.clickatellSmsData = clickatellSmsData;
    }

    @Override
    public String toString() {
        return "ClickatellApi{" + "clickatellSmsData=" + clickatellSmsData + '}';
    }

}
