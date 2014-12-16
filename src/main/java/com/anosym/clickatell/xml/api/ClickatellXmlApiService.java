package com.anosym.clickatell.xml.api;

import java.util.List;
import javax.annotation.Nonnull;

/**
 *
 * @author marembo
 */
public interface ClickatellXmlApiService {

    boolean sendSms(@Nonnull final List<String> toPhoneNumbers, @Nonnull final String message);

    boolean sendSms(@Nonnull final String message, @Nonnull final String toPhoneNumber);

    boolean sendSms(@Nonnull final String message, @Nonnull final String toPhoneNumber, final boolean useSenderId);
}
