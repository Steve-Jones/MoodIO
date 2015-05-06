package zip.moodio;

//import java.sql.Timestamp;
//import java.util.Date;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach on 4/18/2015.
 */
public class Event
{
    private Mood mood = null;
    private List<Input> inputs = null;
    private String annotation = null;
    private static List<Event> events = new ArrayList<>();
    private DataPoint dataPoint = null;
    //private Timestamp timestamp = null;
    public Event(Mood mood, List<Input> inputs, String annotation, DataPoint dataPoint)
    {
        this.mood = mood;
        this.inputs = inputs;
        this.annotation = annotation;
        this.dataPoint = dataPoint;
    }

    public boolean haveMood()
    {
        if(mood == null)
        {
            return false;
        }

        else
            return true;
    }

    public void setMood(Mood moodIn)
    {
        mood = moodIn;
    }

    public Mood getMood()
    {
        return mood;
    }

    public List<Input> getInputs()
    {
        return inputs;
    }

    public void setAnnotation(String annotationIn)
    {
        annotation = annotationIn;
    }

    public String getAnnotation()
    {
        return annotation;
    }

    public void setDataPoint(DataPoint dataPoint)
    {
        this.dataPoint = dataPoint;
    }

    public DataPoint getDataPoint()
    {
        return dataPoint;
    }


    public static List<Event> getEvents()
    {
        return events;
    }

    public static List<DataPoint> getData()
    {
        List<DataPoint> dataPoints = new ArrayList<>();
        for(int i=0;i<events.size();i++)
        {
            dataPoints.add(events.get(i).dataPoint);
        }

        return dataPoints;
    }



//    public void setTimeStamp()
//    {
//        Date date = new Date();
//        timestamp = new Timestamp(date.getTime());
//    }
//
//
//    public long getTimestamp()
//    {
//        return timestamp.getTime();
//    }

}
