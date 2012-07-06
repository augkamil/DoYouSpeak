package com.doyouspeak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Reminder extends FragmentActivity {
	private TextView expression;
	TextView txt;
	ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;
	
	Context context;
	
	private static final String[] myExpressions={"lorem", "ipsum", "dolor",
		"sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue", "purus"};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        
        context = getApplicationContext();
        
        actionHome = (ImageButton)findViewById(R.id.actionHome);
        actionList = (ImageButton)findViewById(R.id.actionMyList);
        actionRecord = (ImageButton)findViewById(R.id.actionRecord);
        
        actionHome.setOnClickListener(lHome);
        actionList.setOnClickListener(lList);
        actionRecord.setOnClickListener(lRecord);
        
        //txt = (TextView)findViewById(R.id.autoCompleteTextView1);

        /*
        setListAdapter(new ArrayAdapter<String>(this,
        		R.layout.my_list, R.id.expressionInList_text,
        		myExpressions));
        expression=(TextView)findViewById(R.id.expressionInList_text);*/
	}
	
	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
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
