package com.example.apirest.Utils;

public class Api {

    public static final String baseUrl="http://192.168.0.5:8000/";

    public static PeriodistaService getPeriodistaService(){
        return  Cliente.getClient(baseUrl).create(PeriodistaService.class);
    }
}
