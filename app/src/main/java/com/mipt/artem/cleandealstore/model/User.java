package com.mipt.artem.cleandealstore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artem on 20.08.16.
 */
public class User {

    public static final int MALE = 1;
    public static final int FEMALE = 2;
    public static final int NOT_CHOSEN = 3;
    public static final String DEFAULT_FIRST_NAME = "Регистрация";
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("email")
    String eMail;
    @SerializedName("sex")
    int sex;
    @SerializedName("phone")
    String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void updateUser(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        eMail = user.geteMail();
        sex = user.getSex();
        phone = user.getPhone();
    }
}
