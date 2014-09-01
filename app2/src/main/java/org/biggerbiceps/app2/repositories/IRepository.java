package org.biggerbiceps.app2.repositories;

import java.util.List;

/**
 * Created by Michał on 2014-08-18.
 */
public interface IRepository<T extends Identifiable> {

    public List<T> getAll();
    public T getById(String pId);
    public void insert(T pEntity);
    public void delete(T pEntity);
    public void clear();


    // TODO: add, delete, etc....
}
