package com.ist.retrofit2;

public class ServerResponse {

    private String ip;
    private Integer ipDecimal;
    private String country;
    private String countryIso;
    private String city;
    private String hostname;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getIpDecimal() {
        return ipDecimal;
    }

    public void setIpDecimal(Integer ipDecimal) {
        this.ipDecimal = ipDecimal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "ip='" + ip + '\'' +
                ", ipDecimal=" + ipDecimal +
                ", country='" + country + '\'' +
                ", countryIso='" + countryIso + '\'' +
                ", city='" + city + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
