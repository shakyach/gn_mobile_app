package com.golfnationsmob.GolfModel;

/**
 * Created by Miral on 2/16/2017.
 */
public class PracticeType {

    private String m_practicetype;
    public int m_practiceID;

    public PracticeType() {
    }

    public PracticeType(String practicetype,int practiceID)
    {
        this.m_practicetype = practicetype;
        this.m_practiceID = practiceID;
    }
    /**
     *
     * @return
     * golfcourse name
     */
    public String getPracticetype() {
        return m_practicetype;
    }
    public int getPracticeID() {
        return m_practiceID;
    }


}
