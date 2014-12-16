package com.anosym.clickatell.xml.api.impl;

import com.anosym.clickatell.xml.api.ClickatellXmlApiConfigurationService;
import com.anosym.clickatell.xml.api.ClickatellXmlApiService;
import com.anosym.vjax.VMarshaller;
import com.anosym.vjax.xml.VDocument;
import com.anosym.vjax.xml.VElement;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static com.anosym.vjax.xml.VDocument.parseDocumentFromString;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;

/**
 *
 * @author marembo
 */
@Dependent
public class ClickatellXmlApiServiceImpl implements ClickatellXmlApiService {

    private static final Logger LOG = Logger.getLogger(ClickatellXmlApiServiceImpl.class.getName());

    private final ClickatellXmlApiConfigurationService xmlApiConfigurationService;

    /**
     * Provided if to be invoked from jse environment.
     *
     * @param xmlApiConfigurationService
     */
    @Inject
    public ClickatellXmlApiServiceImpl(final ClickatellXmlApiConfigurationService xmlApiConfigurationService) {
        this.xmlApiConfigurationService = xmlApiConfigurationService;
    }

    @Override
    public boolean sendSms(@Nonnull final List<String> toPhoneNumbers, @Nonnull final String message) {
        int successfullySentMsg = 0;
        int failedSentMsg = 0;
        boolean success = false;
        for (String to : toPhoneNumbers) {
            if (success = sendSms(message, to)) {
                successfullySentMsg++;
            } else {
                failedSentMsg++;
            }
            LOG.log(Level.INFO,
                    "successfullySentMsg: {0}, failedSentMsg: {1}",
                    new int[]{successfullySentMsg, failedSentMsg});
        }
        return success;
    }

    @Override
    public boolean sendSms(@Nonnull final String message, @Nonnull final String toPhoneNumber) {
        return sendSms(message, toPhoneNumber, true);
    }

    @Override
    public boolean sendSms(@Nonnull final String message, @Nonnull final String toPhoneNumber, final boolean useSenderId) {
        checkNotNull(emptyToNull(message), "the sms message must be specified");
        checkNotNull(emptyToNull(toPhoneNumber), "the sms toPhoneNumber must be specified");

        LOG.log(Level.INFO, "SMS: {0}", message);
        LOG.log(Level.INFO, "TO: {0}", toPhoneNumber);

        if (xmlApiConfigurationService.isSmsSimulation()) {
            return true;
        }

        try {
            final ClickatellApi capi = getApi(toPhoneNumber, message, useSenderId);
            final VElement elem = new VMarshaller<ClickatellApi>().marshall(capi);
            final String value = elem.toXmlString();
            final String request = "data=" + value;
            LOG.log(Level.INFO, request);

            final URL url = new URL(xmlApiConfigurationService.getXmlApiUrl());
            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("POST");

            final OutputStream out = urlConnection.getOutputStream();
            try (final DataOutputStream dOut = new DataOutputStream(out)) {
                dOut.writeBytes(request);
                dOut.flush();
            }
            final InputStream inn = urlConnection.getInputStream();
            final StringBuilder sb = new StringBuilder();
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inn))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
            }
            final String result = sb.toString();
            LOG.log(Level.INFO, result);

            if (!result.isEmpty()) {
                return getResultString(result);
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean getResultString(String info) {
        final VDocument doc = parseDocumentFromString(info);
        final VElement root = doc.getRootElement();
        final VElement sendMsgResp = root.findChild("sendMsgResp");
        if (sendMsgResp != null) {
            final VElement fault = sendMsgResp.findChild("fault");
            if (fault != null) {
                return false;
            }
            VElement apiMsgId = sendMsgResp.findChild("apiMsgId");
            if (apiMsgId != null) {
                return true;
            }
        }
        return false;
    }

    private ClickatellApi getApi(String toPhoneNumber, String message, final boolean useSenderId) {
        final String apiId = xmlApiConfigurationService.getApiId();
        final String username = xmlApiConfigurationService.getUsername();
        final String password = xmlApiConfigurationService.getPassword();
        final String fromNumber = useSenderId ? xmlApiConfigurationService.getFromNumber() : null;
        final ClickatellSmsData sms = new ClickatellSmsData(apiId, username, password, toPhoneNumber, message, fromNumber);

        return new ClickatellApi(sms);
    }
}
