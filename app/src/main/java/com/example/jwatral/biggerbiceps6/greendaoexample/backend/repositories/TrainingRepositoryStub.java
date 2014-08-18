package com.example.jwatral.biggerbiceps6.greendaoexample.backend.repositories;

import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.AchievementStub;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.CrossFitTraining;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.GymTraining;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.StubTraining;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.Training;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Michał on 2014-08-18.
 */
public class TrainingRepositoryStub implements IRepository<Training> {
    private LinkedList<Training> _trainings = null;

    @Override
    public LinkedList<Training> Get() {
        if (_trainings == null) {
            //fake list of trainings for current month.
            Calendar calendar = Calendar.getInstance();
            // get number of days in month
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            // place some trainings before today, one today and two in the future
            // to simplify - every third day ;)
            int typeCounter = 0;
            for (int i = 1; i <= lastDay; i++) {
                if ((i % 3) == 0) {
                    calendar.set(Calendar.DAY_OF_MONTH, i);
                    Date d = calendar.getTime();
                    Training t = null;
                    if (typeCounter < 2) {
                        typeCounter++;
                        t = new GymTraining(d);
                    } else if (typeCounter == 3) {
                        typeCounter++;
                        t = new CrossFitTraining(d);
                    } else {
                        t = new StubTraining(d);
                        typeCounter = 0;
                    }

                    //set some achievements
                    if ((i % 6) == 0) {
                        if (i % 2 == 0) {
                            // add two
                            t._achievements.add(new AchievementStub(150));
                            t._achievements.add(new AchievementStub(34.5));
                        } else {
                            //add one
                            t._achievements.add(new AchievementStub(10));
                        }
                    }

                    _trainings.add(t);

                }
            }
        }

        return _trainings;
    }
}
