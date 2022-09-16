package com.winnie.dto;

public class WeatherDto {

//            "AP": "999.3hPa",
//                    "Radar": "JC_RADAR_AZ9755_JB",
//                    "SD": "83%",
//                    "WD": "西南风",
//                    "WS": "小于3级",
//                    "WSE": "<3",
//                    "city": "深圳",
//                    "cityid": "101280601",
//                    "isRadar": "1",
//                    "njd": "暂无实况",
//                    "sm": "1.3",
//                    "temp": "28.3",
//                    "time": "17:50"


    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    private String ap;

    public String getRadar() {
        return radar;
    }

    public void setRadar(String radar) {
        this.radar = radar;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getWse() {
        return wse;
    }

    public void setWse(String wse) {
        this.wse = wse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String radar;
    private String sd;
    private String wd;
    private String ws;
    private String wse;
    private String city;
    private String isRadar;
    private String njd;
    private String sm;
    private String temp;
    private String time;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    private String cityId;

}
