package com.aldomora.petagram.restAPI;

/**
 * Created by root on 23/06/16.
 */
public final class ConstantesRestAPI {
    public final static String VERSION = "/v1/";
    public final static String ROOT_URL = "https://api.instagram.com" + VERSION;
    public final static String ACCESS_TOKEN = "3439180779.48c1aa3.a7f25d9e0fc4444ab5a7c1b0e8adfe3f";
    public final static String URL_GET_RECENT = "users/{id}/media/recent/?";

    public final static String URL_GIVE_LIKE = "media/{idMedia}/likes?";

    public final static String URL_GET_SEARCH = "users/search?";

    public final static String URL_CHANGE_RELATIONSHIP = "users/{id}/relationship?";

    public static final String ROOT_URL_DB = "https://evening-fortress-14083.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";
    public static final String KEY_POST_LIKE = "registrar-like/";
    public static final String KEY_GET_LIKE_NOTIF = "like-media/{id}/{user}/";
}