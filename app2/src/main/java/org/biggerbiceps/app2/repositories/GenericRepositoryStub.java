package org.biggerbiceps.app2.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jwatral on 01.09.2014.
 */
public class GenericRepositoryStub<T extends Identifiable> implements IRepository<T> {
    private Map<String, T> repository;

    public GenericRepositoryStub() {
        this.repository = new HashMap<>();
        fillDummyData();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public T getById(String pId) {
        return repository.get(pId);
    }

    @Override
    public void insert(T pEntity) {
        repository.put(pEntity.getId(), pEntity);
    }

    @Override
    public void delete(T pEntity) {
        repository.remove(pEntity.getId());
    }

    @Override
    public void clear() {
        repository.clear();
    }

    protected void fillDummyData() {

    }
}
