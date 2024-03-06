package com.example.ssc_voting;

public class CandidatesData {
    private String firstname;
    private String lastname;
    private String gender;
    private String age;
    private String course_section;
    private String profileImage;
    private String position;
    private String partylist;
    private boolean isSelected;
    private Integer voteCounts;

    public Integer getVoteCounts() {
        return voteCounts;
    }

    public void setVoteCounts(Integer voteCounts) {
        this.voteCounts = voteCounts;
    }

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getCourse_section() {
        return course_section;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getPosition() {
        return position;
    }

    public String getPartylist() {
        return partylist;
    }

    public CandidatesData(String firstname, String lastname, String gender, String age, String course_section, String profileImage, String position, String partylist) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.course_section = course_section;
        this.profileImage = profileImage;
        this.position = position;
        this.partylist = partylist;
    }

    public CandidatesData() {
    }
}
