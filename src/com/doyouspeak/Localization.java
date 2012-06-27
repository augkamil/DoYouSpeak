package com.doyouspeak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class Localization extends Activity {
	private TextView expression;
	TextView txt;
    TextView locTxtV;//TextView with writen localization
    TextView locWrite;//AutoCompleteTextView
	
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

        locTxtV = (TextView) findViewById(R.id.your_localization);
        locWrite = (TextView) findViewById(R.id.writing_localization);
        //txt = (TextView)findViewById(R.id.autoCompleteTextView1);
    
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);

        actionBar.setHomeAction(new IntentAction(this, DoYouSpeak.createIntent(this), R.drawable.ic_action_home));
        actionBar.setTitle(R.string.app_name);
        final Action my_ListAction = new IntentAction(this, new Intent(this, MyList.class), R.drawable.ic_action_my_list);
        actionBar.addAction(my_ListAction);
        final Action recordAction = new IntentAction(this, new Intent(this, RecordExpression.class), R.drawable.ic_action_record);
        actionBar.addAction(recordAction);
        
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
        
        /*
        setListAdapter(new ArrayAdapter<String>(this,
        		R.layout.my_list, R.id.expressionInList_text,
        		myExpressions));
        expression=(TextView)findViewById(R.id.expressionInList_text);*/
	}

}
