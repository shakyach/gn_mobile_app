package com.golfnationsmob.GolfModel;

/**
 * Created by shakya
 */
public class GolfCourse {

    private String m_courseName;
    public boolean m_selector;

    public GolfCourse() {
    }

    public GolfCourse(String courseName,boolean selector)
    {
        this.m_courseName = courseName;
        this.m_selector = selector;
    }
    /**
     *
     * @return
     * golfcourse name
     */
    public String getCourse() {
        return m_courseName;
    }
    /**
     *
     * @return
     * Selector
     */
    public boolean getSelector() {
        return m_selector;
    }
    /**
     *
     * @param app
     * Selector
     */
    public void setSelector(boolean selector) {
        this.m_selector = selector;
    }




}
