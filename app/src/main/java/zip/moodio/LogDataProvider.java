package zip.moodio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zach on 4/30/2015.
 */
public class LogDataProvider
{
    public static HashMap<String, List<String>> getLogInfo()
    {
        HashMap<String, List<String>> logs = new HashMap<String, List<String>>();
        List<String> moods = new ArrayList<String>();
        moods.add("Happy");
        moods.add("Sad");
        moods.add("Angry");
        moods.add("Afraid");
        moods.add("Calm");
        List<String> triggers = new ArrayList<String>();
        triggers.add("Finals Week");
        List<String> beliefs = new ArrayList<String>();
        beliefs.add("I will fail.");
        List<String> behaviors = new ArrayList<String>();
        behaviors.add("I will not study.");
        logs.put("Moods", moods);
        logs.put("Triggers", triggers);
        logs.put("Beliefs", beliefs);
        logs.put("Behaviors", behaviors);
        return logs;
    }
}
