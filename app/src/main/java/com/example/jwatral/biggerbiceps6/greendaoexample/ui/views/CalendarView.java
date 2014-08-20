package com.example.jwatral.biggerbiceps6.greendaoexample.ui.views;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;
import android.widget.TextView;

import com.example.jwatral.biggerbiceps6.R;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.model.Training;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.repositories.IRepository;
import com.example.jwatral.biggerbiceps6.greendaoexample.backend.repositories.TrainingRepositoryStub;
import com.example.jwatral.biggerbiceps6.greendaoexample.ui.adapter.CalendarViewAdapter;

import java.util.Calendar;

/**
 * Created by Michał on 2014-08-19.
 */
public class CalendarView extends Activity {
    public Calendar _selected;
    public CalendarViewAdapter _adapter;
    public Handler _handler;
    public IRepository<Training> _repository;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainingcalendar);
        _selected = Calendar.getInstance();  //will return today
        onNewIntent(getIntent());

        _repository = new TrainingRepositoryStub();
        _adapter = new CalendarViewAdapter(_repository,this,_selected);

        _adapter.refresh();
        _adapter.notifyDataSetChanged();

        GridView grid = (GridView) findViewById(R.id.calendar_days);
        grid.setAdapter(_adapter);

        TextView title = (TextView)findViewById(R.id.monthyear);
        title.setText(android.text.format.DateFormat.format("MMM yyyy", _selected));


    }
}
