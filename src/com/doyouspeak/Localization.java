package com.doyouspeak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

public class Localization extends Activity {
    TextView locTxtV;//TextView with writen localization
    TextView locWrite;//AutoCompleteTextView
    CheckedTextView spinnerText;
    ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;
	Context context;
	
	Resources res;
	String[] languages;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localization);
        
        //pobieranie listy języków z pliku xml
        res = getResources();
    	languages = res.getStringArray(R.array.languages);

        context = getApplicationContext();
        
        actionHome = (ImageButton)findViewById(R.id.actionHome);
        actionList = (ImageButton)findViewById(R.id.actionMyList);
        actionRecord = (ImageButton)findViewById(R.id.actionRecord);
        
        actionHome.setOnClickListener(lHome);
        actionList.setOnClickListener(lList);
        actionRecord.setOnClickListener(lRecord);
        
        locTxtV = (TextView) findViewById(R.id.your_localization);
        locWrite = (TextView) findViewById(R.id.writing_localization);
        spinnerText = (CheckedTextView) findViewById(R.id.checkedTextView1);
        //R.string.choose_languages
        spinnerText.setText("TEST");
        
        Button locButton = (Button) findViewById(R.id.my_localization);
        locButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                locTxtV.setVisibility(View.VISIBLE);
                locWrite.setVisibility(View.INVISIBLE);
            }
        });
        
        Spinner langSpinner = (Spinner) findViewById(R.id.choose_languages);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_first_item, languages);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
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
