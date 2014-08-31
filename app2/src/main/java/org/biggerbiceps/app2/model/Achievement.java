package org.biggerbiceps.app2.model;

/**
 * Created by Michał on 2014-08-18.
 */
public abstract class Achievement {
    public String Name;
    public double Value;

    protected Achievement(String name, double value)
    {
        Name = name;
        Value = value;
    }
}

