package com.anosym.clickatell.xml.api.impl;

import com.anosym.vjax.annotations.IgnoreGeneratedAttribute;
import com.anosym.vjax.annotations.Markup;

/**
 *
 * @author marembo
 */
@Markup(name = "sendMsg")
@IgnoreGeneratedAttribute
public class ClickatellSmsData {

    private String api_id;
    private String user;
    private String password;
    private String session_id;
    private String to;
    private String text;
    private String callback;
    private String cliMsgId;
    private String concat;
    private String deliv_ack;
    private String deliv_time;
    private String from;
    private String msg_type;
    private String udh;
    private String unicode;
    private String validity;
    private String req_feat;
    private String max_credits;
    private String queue;
    private String escalate;
    private String sequence_no;

    public ClickatellSmsData(
            String api_id,
            String username,
            String password,
            String to,
            String text,
            String from) {
        this.api_id = api_id;
        this.user = username;
        this.password = password;
        this.to = to;
        this.text = text;
        this.from = from;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getCliMsgId() {
        return cliMsgId;
    }

    public void setCliMsgId(String cliMsgId) {
        this.cliMsgId = cliMsgId;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getDeliv_ack() {
        return deliv_ack;
    }

    public void setDeliv_ack(String deliv_ack) {
        this.deliv_ack = deliv_ack;
    }

    public String getDeliv_time() {
        return deliv_time;
    }

    public void setDeliv_time(String deliv_time) {
        this.deliv_time = deliv_time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getUdh() {
        return udh;
    }

    public void setUdh(String udh) {
        this.udh = udh;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getReq_feat() {
        return req_feat;
    }

    public void setReq_feat(String req_feat) {
        this.req_feat = req_feat;
    }

    public String getMax_credits() {
        return max_credits;
    }

    public void setMax_credits(String max_credits) {
        this.max_credits = max_credits;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getEscalate() {
        return escalate;
    }

    public void setEscalate(String escalate) {
        this.escalate = escalate;
    }

    public String getSequence_no() {
        return sequence_no;
    }

    public void setSequence_no(String sequence_no) {
        this.sequence_no = sequence_no;
    }

    @Override
    public String toString() {
        return "ClickatellSmsData{" + "api_id=" + api_id + ", user=" + user + ", password=" + password + ", session_id=" + session_id + ", to=" + to + ", text=" + text + ", callback=" + callback + ", cliMsgId=" + cliMsgId + ", concat=" + concat + ", deliv_ack=" + deliv_ack + ", deliv_time=" + deliv_time + ", from=" + from + ", msg_type=" + msg_type + ", udh=" + udh + ", unicode=" + unicode + ", validity=" + validity + ", req_feat=" + req_feat + ", max_credits=" + max_credits + ", queue=" + queue + ", escalate=" + escalate + ", sequence_no=" + sequence_no + '}';
    }

}
