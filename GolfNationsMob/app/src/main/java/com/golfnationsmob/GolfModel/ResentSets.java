package com.golfnationsmob.GolfModel;

/**
 * Created by shakya on 2/15/2017.
 */
public class ResentSets {

    private String m_date;
    private String m_distance;
    private String m_outOFtotal;
    private String m_percentage;

    public ResentSets() {
    }

    public ResentSets(String date,String distance,String outOFtotal,String percentage)
    {
        this.m_date = date;
        this.m_distance = distance;
        this.m_outOFtotal = outOFtotal;
        this.m_percentage = percentage;
    }
    /**
     *
     * @return
     * golfcourse name
     */
    public String getDate() {
        return m_date;
    }

    public String getDistance() {
        return m_distance;
    }

    public String getoutOFtotal() {
        return m_outOFtotal;
    }

    public String getPercentage() {
        return m_percentage;
    }

    /*public void setSelector(boolean selector) {
        this.m_selector = selector;
    }*/



}
