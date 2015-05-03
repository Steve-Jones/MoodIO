package zip.moodio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LogActivity extends ActionBarActivity {

    private HashMap<String, List<String>> logCategories;
    private List<String> logTypesList;
    private ExpandableListView expList;
    private LogsAdapter logsAdapter;
    private String moodName = null;
    private int moodIntensity = -1;
    private String triggerName = null;
    private String beliefName = null;
    private String behaviorName = null;
    private String annotation = null;
    private NumberPicker numPicker = null;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        numPicker = (NumberPicker) findViewById(R.id.moodIntensity);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(10);
        numPicker.setWrapSelectorWheel(false); //makes it so you can't keep spinning
        expList = (ExpandableListView)findViewById(R.id.expList);
        logCategories = LogDataProvider.getLogInfo();
        logTypesList = new ArrayList<String>(logCategories.keySet());
        logsAdapter = new LogsAdapter(this, logCategories, logTypesList);
        expList.setAdapter(logsAdapter);

//        expList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getBaseContext(), logTypesList.get(groupPosition) + " is expanded", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        expList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getBaseContext(), logTypesList.get(groupPosition) + " is collapsed", Toast.LENGTH_LONG).show();
//            }
//        });
        expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                String itemInCategory = logCategories.get(logTypesList.get(groupPosition)).get(childPosition);
                String category = logTypesList.get(groupPosition);
                Toast.makeText(getBaseContext(), itemInCategory + " from category " + category + " is selected", Toast.LENGTH_LONG).show();

                if(category.equals("Moods"))
                {
                    moodName = itemInCategory;
                }

                else if(category.equals("Triggers"))
                {
                    triggerName = itemInCategory;
                }

                else if(category.equals("Beliefs"))
                {
                    beliefName = itemInCategory;
                }

                else if(category.equals("Behaviors"))
                {
                    behaviorName = itemInCategory;
                }

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log, menu);
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

    public void submit(View view)
    {
        moodIntensity = numPicker.getValue();
        if(moodName != null && moodIntensity != -1)
        {
            Mood mood = new Mood(moodName, moodIntensity);
            List<Input> inputs = new ArrayList<>();
            EditText annotationText = (EditText) findViewById(R.id.annotation);
            annotation = annotationText.getText().toString();
            if(triggerName != null)
            {
                Input inputTrigger = new Input(triggerName, Input.trigger());
                inputs.add(inputTrigger);
            }

            if(beliefName != null)
            {
                Input inputBelief = new Input(beliefName, Input.belief());
                inputs.add(inputBelief);
            }

            if(behaviorName != null)
            {
                Input inputBehavior = new Input(behaviorName, Input.behavior());
                inputs.add(inputBehavior);
            }

            if(annotation.equals("Enter annotation here..."))
            {
                annotation = null;
            }

            Event event = new Event(mood, inputs, annotation);

            Database database = new Database(this);
            database.addMoodEntry(event);

            Toast.makeText(getBaseContext(), "You have successfully logged an entry", Toast.LENGTH_LONG).show();
            //ZJB add event to database here
        }

        else if(moodName == null)
        {
            Toast.makeText(getBaseContext(), "You must enter a mood before submitting", Toast.LENGTH_LONG).show();
            return;
        }

        else if(moodIntensity == -1)
        {
            Toast.makeText(getBaseContext(), "You must enter a mood intensity before submitting", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(LogActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void goToHomeScreen(View view)
    {
        Intent intent = new Intent(LogActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
