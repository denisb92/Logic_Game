package com.example.denis.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import static com.example.denis.assignment3.Utils.Utils.LEVELFIVEATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELFOURATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELONEATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELSIXATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELTHREEATTEMPTS;
import static com.example.denis.assignment3.Utils.Utils.LEVELTWOATTEMPTS;
//This class shows how many times the player has failed a level and represents it as a pie chart
//The pie chart does not show up unless there are failed attempts
public class Stats extends AppCompatActivity {

    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        back = (Button)findViewById(R.id.Back);
        back.setOnClickListener(new backListr());


        pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);
        //Animates the pie chart

    }

    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(LEVELONEATTEMPTS, 0));
        entries.add(new BarEntry(LEVELTWOATTEMPTS, 1));
        entries.add(new BarEntry(LEVELTHREEATTEMPTS, 2));
        entries.add(new BarEntry(LEVELFOURATTEMPTS,3));
        entries.add(new BarEntry(LEVELFIVEATTEMPTS,4));
        entries.add(new BarEntry(LEVELSIXATTEMPTS,5));
        //Adds all the failed attempts as the data for the pie chart



    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Level 1");
        PieEntryLabels.add("Level 2");
        PieEntryLabels.add("Level 3");
        PieEntryLabels.add("Level 4");
        PieEntryLabels.add("Level 5");
        PieEntryLabels.add("Level 6");
        //Adds all the labels for the levels into the pie chart



    }
    class backListr implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Stats.this,PickStage.class);
            startActivity(intent);
            //Goes back to the Pick Stage class

        }
    }
}


