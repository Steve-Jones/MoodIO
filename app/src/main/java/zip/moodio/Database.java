package zip.moodio;

/**
 * Created by kdoucette on 4/20/15.
 */

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class Database extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "MoodDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = HomeActivity.class.getSimpleName();
    //SQLiteDatabase db = this.getWritableDatabase();

    public Database(Context context) {
       // db.execSQL("DROP TABLE IF EXISTS moods");
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("myTag","database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE moods "+
                "(time INTEGER PRIMARY KEY, mood TEXT, trigger TEXT, behavior TEXT, belief TEXT, annotations TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS moods");
        this.onCreate(db);
    }

    public boolean addMoodEntry(Event event){
        ContentValues values = new ContentValues();
        values.put("time","SELECT date('now');" );
        String mood = event.getMood().getName();
        values.put("mood", mood);
        List<Input> inputs = event.getInputs();
        for(int i = 0; i < inputs.size(); i++){
            if(inputs.get(i).getType() == Input.trigger()){
                values.put("trigger", inputs.get(i).getName());
            }
            if(inputs.get(i).getType() == Input.trigger()){
                values.put("belief", inputs.get(i).getName());
            }
            if(inputs.get(i).getType() == Input.trigger()){
                values.put("behavior", inputs.get(i).getName());
            }
        }
        String annotation = event.getAnnotation();
        values.put("annotations", annotation);
        return true;
    }


    public String query(){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("time","SELECT date('now');" );
        values.put("mood", "happy");
        values.put("trigger", "day off");
        values.put("behavior", "320 work");
        values.put("belief", "working");
        values.put("annotations", "read this");

        db.insert("moods", null, values);

        //Log.d("myTag","database created");

        Cursor cursor = db.rawQuery("SELECT * FROM moods;", null);
        cursor.moveToFirst();
        Log.d("myTag",cursor.getString(1));
        db.close();
        return cursor.getString(0);


    }

//saa

}
