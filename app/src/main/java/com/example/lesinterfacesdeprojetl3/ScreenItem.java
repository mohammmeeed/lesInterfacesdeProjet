package com.example.lesinterfacesdeprojetl3;
public class ScreenItem {
    String Des;
    int ScreenImg;

    public ScreenItem( String des, int screenImg) {
        Des = des;
        ScreenImg = screenImg;
    }

    public void setDes(String des) {
        Des = des;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getDes() {
        return Des;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}
