package com.jaspreetsingh.CovidTracker.api;

import java.util.Map;

public class countrydata {

    private String updated;
    private String country;
    private String cases;
    private String todayCases;
    private String deaths;
    private String todayDeaths;
    private String recovered;
    private String todayRecovered;
    private Map<String,String> countryInfo;
    private String active;
    private String tests;

    public countrydata(String updated, String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, Map<String, String> countryInfo, String active, String tests) {
        this.updated = updated;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.countryInfo = countryInfo;
        this.active = active;
        this.tests = tests;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todayCases;
    }

    public void setTodaycases(String todaycases) {
        this.todayCases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todayDeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todayDeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayrecovered() {
        return todayRecovered;
    }

    public void setTodayrecovered(String todayrecovered) {
        this.todayRecovered = todayrecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public Map<String, String> getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(Map<String, String> countryInfo) {
        this.countryInfo = countryInfo;
    }
}
