package com.example.biruk.androidclientchat.model;

import java.util.List;

public class NewGroup {

    private String name;
    private String profilePic;
    private String bio;
    private List<String> participants;
    private Message lastMsg;
    private List<Message> messages;
    public NewGroup(String name, String profilePic, String bio, List<String> participants, Message lastMsg, List<Message> messages){
        this.name = name;
        this.profilePic = profilePic;
        this.bio = bio;
        this.participants = participants;
        this.lastMsg = lastMsg;
        this.messages = messages;
    }
    public String getName() {
        return name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getBio() {
        return bio;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public Message getLastMsg() {
        return lastMsg;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
