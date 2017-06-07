package com.example.appdeveloper.notify;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class NotifyMainActivity extends AppCompatActivity implements OnClickListener {

    public DB_Helper db;
    private Cursor notesCursor;
    private Button btnRead;
    private Button btnWrite;
    private Button btnDownload;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_main);

        db = DB_Helper.getInstance( this );
        notesCursor = db.tblNotes.queryAll();


        btnRead = (Button) findViewById(R.id.btnRead);
        btnWrite = (Button)findViewById(R.id.btnWrite);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        btnStart = (Button)findViewById(R.id.btnStart);

        //Hide buttons until a database name is changed from default
        if (DB_Helper.newTableName =="default.db"){
            btnRead.setVisibility(View.INVISIBLE);
            btnWrite.setVisibility(View.INVISIBLE);
            btnDownload.setVisibility(View.INVISIBLE);
         //   btnStart.setVisibility(View.INVISIBLE);
        }

        //check if the database is empty and insert a welcome message if it is
        insertFirstRow();

    } //End of OnCreate Class

    //show extra buttons after the group name has been changed
    protected void onRestart() {
        super.onRestart();

        if (DB_Helper.newTableName !="default.db") {
            btnRead.setVisibility(View.VISIBLE);
            btnWrite.setVisibility(View.VISIBLE);
            btnDownload.setVisibility(View.VISIBLE);
        }

    }


    public void insertFirstRow() {
        //create first 2 notes and insert them...
       //notesCursor.moveToFirst();
       //     if (notesCursor.getCount() < 1)
                if (notesCursor==null){
                db.tblNotes.insert(new Note("Notify can be used to store text on your phone.\n\n The text is editable and can be changed at any time."));
                db.tblNotes.insert(new Note("You can also change these first notes and re-use them."));

                    if (notesCursor==null){
                        Log.d("NOTE CHECK","Cursor is null");
                    }
            }

    }


    public void onClickReadNotes(View v) {
        startActivity(new Intent("android.intent.action.ReadNotes"));
    }

    public void onClickMakeNote(View v) {
        startActivity(new Intent("android.intent.action.WriteNotes"));
    }

    public void onClickDownloadNotes(View v) {
        startActivity(new Intent("android.intent.action.JoinGroup"));
    }

    public void onClickStartGroup(View v) {
        startActivity(new Intent("android.intent.action.MakeGroup"));
    }

    public void onClick(View v) {
    }


}
