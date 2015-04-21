package zip.moodio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Zach on 4/18/2015.
 */
public class Event
{
    private Mood mood = null;
    private List<Input> inputs = null;
    private String annotation = null;
    private Timestamp timestamp = null;
    public Event(Mood mood, List<Input> inputs)
    {
        this.mood = mood;
        this.inputs = inputs;
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

    public String getAnnotation()
    {
        return annotation;
    }

    public void setTimeStamp()
    {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }


    public Timestamp getTimestamp()
    {
        return timestamp;
    }



}
