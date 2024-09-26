package com.nbc.modals;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields
public class UserModal {
    private int userId;
    private String title;
    private String body;

    public UserModal(int userId,String title, String body)
    {
        this.userId=userId;
        this.title=title;
        this.body=body;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
