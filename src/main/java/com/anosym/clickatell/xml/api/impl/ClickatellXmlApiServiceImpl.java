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
import javax.inject.Inject;

/**
 *
 * @author marembo
 */
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
    public boolean sendSms(List<String> toPhoneNumbers, String message) {
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
    public boolean sendSms(String message, String toPhoneNumber) {
        if (xmlApiConfigurationService.isSmsSimulation()) {
            LOG.log(Level.INFO, "SMS: {0}", message);
            LOG.log(Level.INFO, "TO: {0}", toPhoneNumber);
            return true;
        }
        try {
            ClickatellApi capi = getApi(toPhoneNumber, message);
            VElement elem = new VMarshaller<ClickatellApi>().marshall(capi);
            String value = elem.toXmlString();
            String request = "data=" + value;
            LOG.log(Level.INFO, request);
            URL url = new URL(xmlApiConfigurationService.getXmlApiUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("POST");
            OutputStream out = urlConnection.getOutputStream();
            try (DataOutputStream dOut = new DataOutputStream(out)) {
                dOut.writeBytes(request);
                dOut.flush();
            }
            InputStream inn = urlConnection.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
            String str;
            String result = "";
            while ((str = reader.readLine()) != null) {
                result += str;
            }
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
        VDocument doc = VDocument.parseDocumentFromString(info);
        VElement root = doc.getRootElement();
        VElement sendMsgResp = root.findChild("sendMsgResp");
        if (sendMsgResp != null) {
            VElement fault = sendMsgResp.findChild("fault");
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

    private ClickatellApi getApi(String toPhoneNumber, String message) {
        ClickatellSmsData sms = new ClickatellSmsData(xmlApiConfigurationService.getApiId(),
                                                      xmlApiConfigurationService.getUsername(),
                                                      xmlApiConfigurationService.getPassword(),
                                                      toPhoneNumber,
                                                      message,
                                                      xmlApiConfigurationService.getFromNumber());
        return new ClickatellApi(sms);
    }
}
