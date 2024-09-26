package com.nbc.modals;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields
public class PartialUpdateUserModal {
    private int id;
    private String title;
    private String body;
    private int userId;

    public PartialUpdateUserModal(int id,int userId,String title, String body)
    {
        this.id=id;
        this.userId=userId;
        this.title=title;
        this.body=body;
    }

    public PartialUpdateUserModal()
    {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
