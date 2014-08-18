package com.example.jwatral.biggerbiceps6.greendaoexample.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jwatral.biggerbiceps6.R;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.CrossFitTraining;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.GymTraining;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.StubTraining;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.Training;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.repositories.IRepository;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created by Michał on 2014-08-18.
 */
public class CalendarViewAdapter extends BaseAdapter {
    static final int FIRST_DAY_OF_WEEK = 0; // 0 - sunday, 1 - monday // TODO: revisit

    private TrainingDay[] _trainingDays;
    private int emptyDaysOffset;

    private Context _context;

    private Calendar _currentMonth;

    private IRepository<Training> _trainingRepository; // use DI ?

    private TrainingDayCalendarItemFormatter _formatter; // use DI

    public CalendarViewAdapter(IRepository<Training> trainingsRepository, Context c, Calendar month)
    {
        _trainingRepository = trainingsRepository;
        _context = c;
        _currentMonth = month;
        _formatter = new TrainingDayCalendarItemFormatter();
        refresh();
    }

    @Override
    public int getCount() {
        // return _currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        return _trainingDays.length;
    }

    @Override
    public Object getItem(int position) {
        return null; // TODO: was not in sample app, not sure how it is used
    }

    @Override
    public long getItemId(int position) {
        return 0;  // TODO: was not in sample app, not sure how it is used
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(convertView == null)
        {
            LayoutInflater vi = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.trainingdaycalendaritem, null); // create view
        }

        _formatter.FormatViewItem(_trainingDays[position], v);

        return v;
    }

    public void refresh()
    {
        // TODO: all copied from some example, so need to revisit and see what is needed and what is not
        emptyDaysOffset=-1; // to start from 0 index after adding offset (e.g. to make 0 + 1 = 0 if first day is first day of week
        int lastDay = _currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDay = _currentMonth.get(Calendar.DAY_OF_WEEK);

        // figure size of the array
        if(firstDay==1){
            _trainingDays = new TrainingDay[lastDay+(FIRST_DAY_OF_WEEK*6)];
        }
        else {
            _trainingDays = new TrainingDay[lastDay+firstDay-(FIRST_DAY_OF_WEEK+1)];
        }

        // populate empty days before first real day
        if(firstDay > 1) {
            for(int j=0;j<firstDay-FIRST_DAY_OF_WEEK;j++) {
                _trainingDays[j] = null;
                emptyDaysOffset++;
            }
        }
        else {
            for(int j=0;j<FIRST_DAY_OF_WEEK*6;j++) {
                TrainingDay td = new TrainingDay();
                td.dayOfMonth = j+1;
                td.training = null;
                _trainingDays[j] = td;
            }
        }

        // load items
        LinkedList<Training> trainingsInMonth = _trainingRepository.Get(); // TODO: add filtered get
        Calendar c = Calendar.getInstance();
        for(Training t : trainingsInMonth)
        {
            c.setTime(t.DateOfTraining);
            int day = c.get(Calendar.DAY_OF_MONTH);
            _trainingDays[emptyDaysOffset+day].training = t;
        }
    }

    private class TrainingDay
    {
        public Training training;
        public int dayOfMonth;
    }

    protected class TrainingDayCalendarItemFormatter
    {
        // TODO: Proof of concept only
        public void FormatViewItem(TrainingDay trainingDayEntry, View itemView)
        {
            //if null - disable
            if(trainingDayEntry == null)
            {
                itemView.setClickable(false);
                itemView.setFocusable(false);
            }
            else
            {
                // format day display
                TextView dayView = (TextView)itemView.findViewById(R.id.dayofmonth);
                dayView.setText(""+trainingDayEntry.dayOfMonth);

                if(isThisDayToday(trainingDayEntry)) // mark current day
                {
                    dayView.setTextColor(Color.RED);
                    dayView.setTypeface(null, Typeface.BOLD);
                }

                // mark training day properly
                if(trainingDayEntry.training != null)
                {
                    Training t = trainingDayEntry.training;
                    if(t instanceof GymTraining)
                    {
                        // do gym formatting
                        itemView.setBackgroundColor(Color.BLUE);
                    }
                    else if(t instanceof CrossFitTraining)
                    {
                        // do crossfitFormatting
                        itemView.setBackgroundColor(Color.YELLOW);
                    }
                    else if(t instanceof StubTraining)
                    {
                        // do
                        itemView.setBackgroundColor(Color.GREEN);
                    }

                    // add achievements
                    // TODO:
                }
            }
        }

        private boolean isThisDayToday(TrainingDay trainingDay)
        {
            Calendar dateToTest = Calendar.getInstance();
            dateToTest.setTime(trainingDay.training.DateOfTraining);
            return _currentMonth.get(Calendar.YEAR) == dateToTest.get(Calendar.YEAR) &&
                   _currentMonth.get(Calendar.MONTH) == dateToTest.get(Calendar.MONTH) &&
                   trainingDay.dayOfMonth == _currentMonth.get(Calendar.DAY_OF_MONTH);
        }
    }
}


