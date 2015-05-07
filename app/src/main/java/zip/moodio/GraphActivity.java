package zip.moodio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class GraphActivity extends ActionBarActivity {

    private DataPoint[] data = null;
    private PointsGraphSeries<DataPoint> series = null;
    private LineGraphSeries<DataPoint> series2 = null;
    private GraphView graph;
    //private List<DataPoint> dataPoints = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runOnUiThread(new Runnable() {
            public void run() {
                setContentView(R.layout.activity_graph);
                createGraph();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void generateData()
    {
        Calendar c1 = GregorianCalendar.getInstance();
        c1.set(2015, 3, 21);           //months start at zero
        Date d1 = c1.getTime();
        c1.set(2015, 3, 23);
        Date d2 = c1.getTime();
        c1.set(2015, 3, 26);
        Date d3 = c1.getTime();
        c1.set(2015, 3, 28);
        Date d4 = c1.getTime();
        c1.set(2015, 4, 1);
        Date d5 = c1.getTime();
        c1.set(2015, 4, 3);
        Date d6 = c1.getTime();

        data = new DataPoint[]
                {
                        new DataPoint(d1, 7),
                        new DataPoint(d2, 2),
                        new DataPoint(d3, 5),
                        new DataPoint(d4, 1),
                        new DataPoint(d5, 9),
                        new DataPoint(d6, 3),
                };

        if(!Event.getEvents().isEmpty())
        {
                for(int i=0;i<data.length;i++)
                {
                    //System.out.println("Loop: " + Event.getEvents().get(0).getMood().getIntensity());
                    Event.getEvents().remove(0);
                }
        }

        Event.getEvents().add(0, new Event(new Mood("Happy", 7), null, null, data[0]));
        Event.getEvents().add(1, new Event(new Mood("Sad", 2), null, null, data[1]));
        Event.getEvents().add(2, new Event(new Mood("Stressed", 5), null, null, data[2]));
        Event.getEvents().add(3, new Event(new Mood("Angry", 1), null, null, data[3]));
        Event.getEvents().add(4, new Event(new Mood("Calm", 9), null, null, data[4]));
        Event.getEvents().add(5, new Event(new Mood("Happy", 3), null, null, data[5]));

    }

    private String createDate(long timeInMillis) //manually converting millis into a date
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getDefault());
        calendar.setTimeInMillis(timeInMillis);

        return sdf.format(calendar.getTime());
    }

//    public static void addDataToGraph(long timeInMillis, int moodIntensity)
//    {
//        DataPoint nextData = new DataPoint(timeInMillis, moodIntensity);
//        DataPoint[] tempData = new DataPoint[data.length+1];
//
//        for(int i=0;i<data.length;i++)
//        {
//            tempData[i] = data[i];
//        }
//        tempData[data.length] = nextData;
//        data = tempData;
//    }

    protected void onResume()
    {
        super.onResume();
        runOnUiThread(new Runnable() {
            public void run() {
                DataPoint[] oldData = data;
                List<DataPoint> temp = Event.getData();
                int tempDataSize = temp.size();
                DataPoint[] tempData = new DataPoint[tempDataSize];
                for(int i=0;i<tempDataSize;i++)
                {
                    tempData[i] = temp.get(i);
                    //System.out.println(tempData[i].getY());
                }
//                if(!temp.isEmpty())
//                {
//                    for(int i=0;i<data.length;i++)
//                    {
//                        tempData[i] = data[i];
//                    }
//
//                    data = tempData;
//                }
                data = tempData;
                if(oldData != data)
                {
                    series.resetData(data);
                    series2.resetData(data);
                }
            }
        });


    }


    private void createGraph()
    {
        graph = (GraphView) findViewById(R.id.graph);
        generateData();
        series = new PointsGraphSeries<DataPoint>(data);
        series2 = new LineGraphSeries<DataPoint>(data);
        series.setShape(PointsGraphSeries.Shape.POINT);
        graph.addSeries(series);
        graph.addSeries(series2);


        series.setOnDataPointTapListener(new OnDataPointTapListener() {

            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getBaseContext(),"Mood: " + Event.getEvents().get(Event.getData().indexOf(dataPoint)).getMood().getName() + "\nMood Intensity: " + (short)dataPoint.getY() + "\nLog Date: " + createDate((long)dataPoint.getX()), Toast.LENGTH_SHORT).show();
            }
        });
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(data[0].getX());
        graph.getViewport().setMaxX(System.currentTimeMillis() + (3*(24*60*60*1000))); //3 days from "today"
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);
        graph.setTitle("Mood Graph");
        graph.setTitleTextSize(90);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this)); //convert mills times to actual dates
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); //shows how many dates can be displayed on the screen
    }

    public void goToHomeScreen(View view)
    {
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(GraphActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }


}
