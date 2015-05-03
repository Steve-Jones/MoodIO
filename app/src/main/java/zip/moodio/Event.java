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
    private static List<DataPoint> data = new ArrayList<>();
    //private Timestamp timestamp = null;
    public Event(Mood mood, List<Input> inputs, String annotation)
    {
        this.mood = mood;
        this.inputs = inputs;
        this.annotation = annotation;
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

    public void addDataPoint(long time, int intensity)
    {
        DataPoint dataPoint = new DataPoint(time, intensity);
        data.add(dataPoint);
    }

    public static List<DataPoint> getData()
    {
        return data;
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
