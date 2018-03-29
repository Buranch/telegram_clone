package com.example.biruk.androidclientchat.ProviderData.model;

/**
 * Created by Biruk on 3/11/2018.
 */

public class UserData {

    public UserData(String firstName, String lastName, String userName, String userID, boolean online, String profilePic, String lastSeen) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userID = userID;
        this.online = online;
        this.profilePic = profilePic;
        this.lastSeen = lastSeen;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String firstName;
    private String lastName;
    private String userName;
    private String userID;
    private boolean online;
    private String profilePic;
    private String lastSeen;


    private String _id;
}
