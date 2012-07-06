package com.doyouspeak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Localization extends Activity {
    TextView locTxtV;//TextView with writen localization
    TextView locWrite;//AutoCompleteTextView
    ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;
	Context context;
	
	private static final String[] yourLocalizations={"Język", "ipsum", "dolor",
		"sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue", "purus"};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localization);

        context = getApplicationContext();
        
        actionHome = (ImageButton)findViewById(R.id.actionHome);
        actionList = (ImageButton)findViewById(R.id.actionMyList);
        actionRecord = (ImageButton)findViewById(R.id.actionRecord);
        
        actionHome.setOnClickListener(lHome);
        actionList.setOnClickListener(lList);
        actionRecord.setOnClickListener(lRecord);
        
        locTxtV = (TextView) findViewById(R.id.your_localization);
        locWrite = (TextView) findViewById(R.id.writing_localization);
        
        Button locButton = (Button) findViewById(R.id.my_localization);
        locButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                locTxtV.setVisibility(View.VISIBLE);
                locWrite.setVisibility(View.INVISIBLE);
            }
        });
        
        Spinner langSpinner = (Spinner) findViewById(R.id.choose_languages);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, yourLocalizations);
        langSpinner.setAdapter(spinnerArrayAdapter);
        
	}
	
	private View.OnClickListener lHome = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, DoYouSpeak.class);
			startActivity(i);
		}
	};

	private View.OnClickListener lList = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, MyList.class);
			startActivity(i);
		}
	};

	private View.OnClickListener lRecord = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			i = new Intent(context, RecordExpression.class);
			startActivity(i);
		}
	};

}
