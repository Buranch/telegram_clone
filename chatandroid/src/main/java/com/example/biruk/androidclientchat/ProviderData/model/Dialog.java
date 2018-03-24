package com.example.biruk.androidclientchat.ProviderData.model;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by troy379 on 04.04.17.
 */
public class Dialog implements IDialog {
    private String id;
    private String pic;
    private String name;
    private String[] users;
//    private Message lastMessage;
    private String lastMessage;

    private int unreadCount;

    public Dialog(String id, String name, String pic,
                  String[] users, String lastMessage, int unreadCount) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    public String getId() {
        return id;
    }

    public String getDialogPhoto() {
        return pic;
    }

    public String getDialogName() {
        return name;
    }

    @Override
    public List<? extends IUser> getUsers() {
        return null;
    }

    @Override
    public IMessage getLastMessage() {
        return null;
    }

    public String[] getUsersId() { return users;}
//    public String getLastMessage() {
//        return lastMessage;
//    }

    @Override
    public void setLastMessage(IMessage message) {

    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
