package com.golfnationsmob.GolfModel;

/**
 * Created by Miral on 3/3/2017.
 */
public class myclub {
    private String m_clubtype;
    private String m_clubdistance;
    public boolean m_clubOnOff;

    public myclub() {
    }

    public myclub(String clubtype,String clubdistance,boolean clubOnOff,int type)
    {
        this.m_clubtype = clubtype;
        this.m_clubdistance = clubdistance;
        this.m_clubOnOff = clubOnOff;
    }
    /**
     *
     * @return
     * golfcourse name
     */
    public String getClubname() {
        return m_clubtype;
    }
    public String getClubDistance() {
        return m_clubdistance;
    }
    public void setClubDistance(String clubdistance) {
        this.m_clubdistance = clubdistance;
    }
    /**
     *
     * @return
     * Selector
     */
    public boolean getClubOnOff() {
        return m_clubOnOff;
    }
    /**
     *
     * @param app
     * Selector
     */
    public void setClubOnOff(boolean clubOnOff) {
        this.m_clubOnOff = clubOnOff;
    }


}
