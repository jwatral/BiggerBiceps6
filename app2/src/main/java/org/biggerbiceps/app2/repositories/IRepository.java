package org.biggerbiceps.app2.repositories;

import java.util.LinkedList;

/**
 * Created by Michał on 2014-08-18.
 */
public interface IRepository<T> {

    public LinkedList<T> Get();

    // TODO: add, delete, etc....
}
