package com.example.jwatral.biggerbiceps6.greendaoexample.backend.repositories;

import android.content.Context;

import com.example.jwatral.biggerbiceps6.greendaoexample.DaoExampleApplication;

import java.util.List;

import greendao.ExerciseSet;
import greendao.ExerciseSetDao;

/**
 * Created by surecase on 19/03/14.
 */
public class ExerciseSetRepository {

    public static void insertOrUpdate(Context context, ExerciseSet exerciseSet) {
        getExerciseSetDao(context).insertOrReplace(exerciseSet);
    }

    public static void clearBoxes(Context context) {
        getExerciseSetDao(context).deleteAll();
    }

    public static void deleteExerciseWithId(Context context, long id) {
        getExerciseSetDao(context).delete(getExerciseForId(context, id));
    }

    public static ExerciseSet getExerciseForId(Context context, long id) {
        return getExerciseSetDao(context).load(id);
    }

    public static List<ExerciseSet> getAllExercises(Context context) {
        return getExerciseSetDao(context).loadAll();
    }

    private static ExerciseSetDao getExerciseSetDao(Context c) {
        return ((DaoExampleApplication) c.getApplicationContext()).getDaoSession().getExerciseSetDao();
    }
}
