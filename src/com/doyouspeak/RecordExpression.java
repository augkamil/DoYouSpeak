package com.doyouspeak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RecordExpression extends Activity {
	Expression exp = new Expression();
	ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;
	
	Context context;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_exp);
        
        context = getApplicationContext();
        
        actionHome = (ImageButton)findViewById(R.id.actionHome);
        actionList = (ImageButton)findViewById(R.id.actionMyList);
        actionRecord = (ImageButton)findViewById(R.id.actionRecord);
        
        actionHome.setOnClickListener(lHome);
        actionList.setOnClickListener(lList);
        actionRecord.setOnClickListener(lRecord);

        Button save=(Button)findViewById(R.id.setButton);  
        save.setOnClickListener(onSave);
        
	}
	
	private View.OnClickListener onSave=new View.OnClickListener() {
			public void onClick(View v) {
				EditText name=(EditText)findViewById(R.id.editExpression);
				exp.setExpression(name.getText().toString());
				
				//String check = name.getText().toString();
				
				//Toast.makeText(RecordExpression.this, check, Toast.LENGTH_SHORT).show();
			}
	};
	
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
