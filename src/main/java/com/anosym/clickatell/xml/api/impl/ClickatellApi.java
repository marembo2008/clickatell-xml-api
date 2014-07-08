/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.anosym.clickatell.xml.api.impl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
}

