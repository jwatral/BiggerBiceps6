package org.biggerbiceps.app2.model;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Michał on 2014-08-18.
 */
public abstract class Training {
    public Date DateOfTraining;
    public LinkedList<Achievement> _achievements;

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
}

