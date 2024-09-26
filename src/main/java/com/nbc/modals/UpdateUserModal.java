package com.nbc.modals;

public class UpdateUserModal {
    private int id;
    private String title;
    private String body;
    private int userId;

    public UpdateUserModal(int id,int userId,String title, String body)
    {
        this.id=id;
        this.userId=userId;
        this.title=title;
        this.body=body;
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
