package zip.moodio;

import zip.moodio.AllInput;

/**
 * Created by Zach on 4/18/2015.
 */
public class Mood implements AllInput
{
    private String name = null;

    private int intensity = 0;

    public void setName(String nameIn)
    {
        name = nameIn;
    }

    public String getName()
    {
        return name;
    }

    public void setIntensity(int intensityIn)
    {
        intensity = intensityIn;
    }

    public int getIntensity()
    {
        return intensity;
    }

}
