package zip.moodio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;



public class HomeActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runOnUiThread(new Runnable() {
            public void run() {
                setContentView(R.layout.activity_home);
            }
        });


//        //TABHOST
//        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
//        tabHost.setup();
//        //LOG TAB
//        TabHost.TabSpec tabSpec = tabHost.newTabSpec("log");
//        tabSpec.setContent(R.id.logTab);
//        tabSpec.setIndicator("Log");
//        tabHost.addTab(tabSpec);
//        //DATA TAB
//        tabSpec = tabHost.newTabSpec("data");
//        tabSpec.setContent(R.id.dataTab);
//        tabSpec.setIndicator("Data");
//        tabHost.addTab(tabSpec);
//        //HELP TAB
//        tabSpec = tabHost.newTabSpec("help");
//        tabSpec.setContent(R.id.helpTab);
//        tabSpec.setIndicator("Help");
//        tabHost.addTab(tabSpec);
//        //SETTINGS TAB
//        tabSpec = tabHost.newTabSpec("settings");
//        tabSpec.setContent(R.id.settingsTab);
//        tabSpec.setIndicator("Settings");
//        tabHost.addTab(tabSpec);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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

    public void goToLogScreen(View view)
    {
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(HomeActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goToGraphScreen(View view)
    {
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(HomeActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        });
    }

    public void goToHelpScreen(View view)
    {
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

    }

    public void goToSettingsScreen(View view)
    {
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
