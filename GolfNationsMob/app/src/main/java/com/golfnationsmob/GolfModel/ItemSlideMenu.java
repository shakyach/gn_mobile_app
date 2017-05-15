package com.golfnationsmob.GolfModel;

/**
 * Created by shakya on 12/30/2016.
 */
public class ItemSlideMenu {

    private int imgId;
    private int MenuType;
    private String title;

    public ItemSlideMenu(int imgId, String title,int MenuType) {
        this.imgId = imgId;
        this.title = title;
        this.MenuType = MenuType;
    }

    public int getImgId() {
        return imgId;
    }
    public int getMenuType() {
        return MenuType;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

