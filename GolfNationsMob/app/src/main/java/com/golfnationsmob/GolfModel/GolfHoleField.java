package com.golfnationsmob.GolfModel;

/**
 * Created by shakya on 1/18/2017.
 */
public class GolfHoleField {

    private String m_GolfHoleField;
    private String m_GolfHoleFieldDistance;

    public GolfHoleField() {
    }

    public GolfHoleField(String GolfHoleField,String GolfHoleFieldDistance)
    {
        this.m_GolfHoleField = GolfHoleField;
        this.m_GolfHoleFieldDistance = GolfHoleFieldDistance;
    }
    /**
     *
     * @return
     * golfcourse field name
     */
    public String getGolfHoleField() {
        return m_GolfHoleField;
    }
    /**
     *
     * @return
     * golfcourse field Distance
     */
    public String getGolfHoleFieldDistance() {
        return m_GolfHoleFieldDistance;
    }

}
