package com.forcs.eformsign.webhook.data;

public class Result {
    public String webhook_id;
    public String webhook_name;
    public String company_id;
    public String event_type;
    public Document document;
    public Pdf pdf;

    public String getWebhook_id() {
        return webhook_id;
    }

    public String getWebhook_name() {
        return webhook_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public Document getDocument() {
        return document;
    }
}
