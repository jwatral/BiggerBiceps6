package com.example.jwatral.biggerbiceps6.greendaoexample.backend.repositories;

import java.util.LinkedList;

/**
 * Created by Michał on 2014-08-18.
 */
public interface IRepository<T> {

    public LinkedList<T> Get();

    // TODO: add, delete, etc....
}
