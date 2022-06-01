package com.niit.mailnotification.model;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class EmailTemplate {

    private String email;

    private String subject;

    private String body;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
