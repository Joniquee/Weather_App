package com.example.weatherapp;

import android.annotation.SuppressLint;

import java.util.Map;

public class UrlBuilder {

    private String[] cities = {"Moscow", "Saint Petersburg", "Nizhniy Novgorod", "Sochi", "Chaykovskiy", "Nizhniy Tagil"};
    private String[] periods = {"Today", "Tomorrow", "3 Days", "10 Days", "Month"};

    private Map<String, Integer> codes = Map.of("Moscow", 4368, "Saint Petersburg", 4079, "Nizhniy Novgorod", 4355, "Sochi", 5233, "Chaykovskiy", 11777, "Nizhniy Tagil", 4478);

    String URL = "";
    UrlBuilder(String baseUrl, String city, String period){
        Integer code = codes.get(city);
        switch (city){
            case "Saint Petersburg":
                city = "sankt-peterburg";
                break;
            case "Nizhniy Novgorod":
                city = "nizhny-novgorod";
                break;
            case "Sochi":
                city = "sochi";
                break;
            case "Chaykovskiy":
                city = "chaykovsky";
                break;
            case "Nizhniy Tagil":
                city = "nizhny-tagil";
                break;
            case "Moscow":
                city = "moscow";
                break;
        }
        switch (period){
            case "Today":
                period = "/";
                break;
            case "Tomorrow":
                period = "/tomorrow/";
                break;
            case "3 Days":
                period = "/3-days/";
                break;
            case "10 Days":
                period = "/10-days/";
                break;
            case "Month":
                period = "/month/";
                break;
        }
        URL = baseUrl + city + "-" + code + period;
    }
    public String getURL(){
        return URL;
    }
}
