package com.example.ssc_voting;

public class UserData {
    private String firstname;
    private String lastname;
    private String course_section;
    private String password;
    private String studentID;
    private String email;
    private String profileImage;
    private String account_status;
    private String voting_status;
    private String key;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getCourse_section() {
        return this.course_section;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public String getAccount_status() {
        return this.account_status;
    }

    public String getVoting_status() {
        return voting_status;
    }

    public UserData(String firstname, String lastname, String course_section, String password, String studentID, String email, String profileImage, String account_status, String voting_status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.course_section = course_section;
        this.password = password;
        this.studentID = studentID;
        this.email = email;
        this.profileImage = profileImage;
        this.account_status = account_status;
        this.voting_status = voting_status;
    }

    public UserData() {
    }
}
