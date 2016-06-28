package com.aldomora.petagram.restAPI.model;

import com.aldomora.petagram.Pojo.User;

import java.util.ArrayList;

/**
 * Created by root on 23/06/16.
 */
public class SearchResponse {
    ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
