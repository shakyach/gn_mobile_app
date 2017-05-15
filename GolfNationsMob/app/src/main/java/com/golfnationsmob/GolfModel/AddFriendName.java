package com.golfnationsmob.GolfModel;


/**
 * Created by shakya
 */
public class AddFriendName {

    private String m_friendName;
    public boolean m_selector;
    public int id;
    public AddFriendName() {
    }

    public AddFriendName(String m_friendName,boolean m_selector,int id)
    {
        this.m_friendName = m_friendName;
        this.m_selector = m_selector;
        this.id = id;

    }
    /**
     *
     * @return
     * The friendName
     */
    public String getName() {
        return m_friendName;
    }
    /**
     *
     * @return
     * Selector
     */
    public boolean getboolean() {
        return m_selector;
    }
    /**
     *
     * @return
     * id
     */
    public int getId() {
        return id;
    }

}
