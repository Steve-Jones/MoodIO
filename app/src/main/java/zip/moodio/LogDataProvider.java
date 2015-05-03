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
        moods.add("Calm");
        moods.add("Stressed");
        List<String> triggers = new ArrayList<String>();
        triggers.add("Important deadline approaching");
        triggers.add("Fought with a friend");
        triggers.add("Achieved a goal");
        triggers.add("Failed to achieve a goal");
        triggers.add("No pending work");
        List<String> beliefs = new ArrayList<String>();
        beliefs.add("I won't be able to finish my work on time");
        beliefs.add("I'm lonely. I don't have any friends");
        beliefs.add("Everything is going to be fine!");
        beliefs.add("Life is meant to relax!");
        beliefs.add("I won't be able to pay my bills");
        List<String> behaviors = new ArrayList<String>();
        behaviors.add("Donate money to a charity");
        behaviors.add("Think negatively");
        behaviors.add("Watch TV");
        behaviors.add("Yell at people around me");
        behaviors.add("Go to the bars");
        logs.put("Behaviors", behaviors);
        logs.put("Triggers", triggers);
        logs.put("Moods", moods);
        logs.put("Beliefs", beliefs);
        return logs;
    }
}
