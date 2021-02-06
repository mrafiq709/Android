package com.ist.retrofit2;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerResponse implements Serializable {

    // SerialzedName use kora hoy JSON object er same name ta k indicate korte
    // r na hoy variable name onojayi search kore default hoile
    @SerializedName("ip")
    private String ip;
    @SerializedName("ip_decimal")
    private double ipDecimal;
    @SerializedName("country")
    private String country;
    @SerializedName("country_iso")
    private String countryIso;
    @SerializedName("city")
    private String city;
    @SerializedName("hostname")
    private String hostname;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getIpDecimal() {
        return ipDecimal;
    }

    public void setIpDecimal(double ipDecimal) {
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
