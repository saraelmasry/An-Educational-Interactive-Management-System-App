package com.csc.ems.pojo;

public class UsersModel {
    String UserId;
    String UserEmail;


    public UsersModel(){}

    public UsersModel(String userId, String userEmail) {
        UserId = userId;
        UserEmail = userEmail;
    }

    public String getUserId() {
        return UserId;
    }


    public String getUserEmail() {
        return UserEmail;
    }

}
