package org.biggerbiceps.app2.repositories;

import org.biggerbiceps.app2.model.Training;

import java.util.List;

/**
 * Created by jwatral on 01.09.2014.
 */
public class Repository<T extends Identifiable> implements IRepository<T> {
    public static final Repository<Training> TRAINING = new Repository<>(new TrainingRepositoryStub());

    private final IRepository<T> mRepository;

    public Repository(IRepository<T> mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public List<T> getAll() {
        return mRepository.getAll();
    }

    @Override
    public T getById(String pId) {
        return mRepository.getById(pId);
    }

    @Override
    public void insert(T pEntity) {
        mRepository.insert(pEntity);
    }

    @Override
    public void delete(T pEntity) {
        mRepository.delete(pEntity);
    }

    @Override
    public void clear() {
        mRepository.clear();
    }

}
