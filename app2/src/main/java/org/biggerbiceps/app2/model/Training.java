package org.biggerbiceps.app2.model;

import org.biggerbiceps.app2.repositories.Identifiable;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Michał on 2014-08-18.
 */
public abstract class Training implements Identifiable {
    public Date DateOfTraining;
    public LinkedList<Achievement> _achievements;
    private String mId;

    protected Training()
    {
        this(new Date());
    }

    public Training(Date date)
    {
        DateOfTraining = date;
        _achievements = new LinkedList<Achievement>();
    }

    public boolean HasAchievements()
    {
        return (_achievements!=null && _achievements.size() > 0);
    }

    protected abstract void UpdateAchievement();

    @Override
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    @Override
    public String toString() {
        return String.format("%s @ %tD",getClass().getSimpleName(), DateOfTraining);
    }
}

