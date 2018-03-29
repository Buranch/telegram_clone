package com.example.biruk.androidclientchat.model;


import com.stfalcon.chatkit.commons.modeels.IDialog;

import java.util.List;


/*
 * Created by troy379 on 04.04.17.
 */
public class Dialog implements IDialog<Message> {

    private String _id;
    private String profPic;
    private String name;
    private List<String> participants;
    private Message lastMsg;
    private String type;
    private int unreadCount;
    public Dialog(String id, String name, String photo,
                  List<String> participants, Message lastMsg, int unreadCount, String type) {
        this._id = id;
        this.name = name;
        this.profPic = photo;
        this.participants = participants;
        this.lastMsg = lastMsg;
        this.unreadCount = unreadCount;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String getId() {
        return _id;
    }

    public String getProfPic() {
        return profPic;
    }

    public String getName() {
        return name;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public Message getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(Message lastMsg) {
        this.lastMsg = lastMsg;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
