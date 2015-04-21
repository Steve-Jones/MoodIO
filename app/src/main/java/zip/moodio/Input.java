package zip.moodio;

import zip.moodio.AllInput;

/**
 * Created by Zach on 4/18/2015.
 */
public class Input implements AllInput

{
    private String name = null;
    private enum Type {TRIGGER, BELIEF, BEHAVIOR}
    private Type type = null;

    public void setName(String nameIn)
    {
        name = nameIn;
    }

    public String getName()
    {
        return name;
    }

    public void setType(Type typeIn)
    {
        type = typeIn;
    }

    public Type getType()
    {
        return type;
    }

    public static Type trigger()
    {
        return Type.TRIGGER;
    }

    public static Type belief()
    {
        return Type.BELIEF;
    }

    public static Type behavior()
    {
        return Type.BEHAVIOR;
    }
}
