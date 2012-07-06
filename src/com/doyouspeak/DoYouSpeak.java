package com.doyouspeak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;


public class DoYouSpeak extends Activity implements OnItemClickListener {
 
	Context context;
	ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        context = getApplicationContext();
           
        actionHome = (ImageButton)findViewById(R.id.actionHome);
        actionList = (ImageButton)findViewById(R.id.actionMyList);
        actionRecord = (ImageButton)findViewById(R.id.actionRecord);
        
        actionHome.setOnClickListener(lHome);
        actionList.setOnClickListener(lList);
        actionRecord.setOnClickListener(lRecord);
        
        GridView gridview = (GridView) findViewById(R.id.dashboard_grid);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(this);
        
        
    }

    public static Intent createIntent(Context context) {
		Intent i = new Intent(context, DoYouSpeak.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent i = null;
		
		switch(position) {
			case 0:
				i = new Intent(this, Localization.class);
				break;
			case 1:
				i = new Intent(this, Expressions.class);
				break;
			case 2:
				i = new Intent(this, MyList.class);
				break;
			case 3:
				i = new Intent(this, Reminder.class);
				break;
			case 4:
				i = new Intent(this, RecordExpression.class);
				break;	
		}
		
		startActivity(i);
		
	/*	int duration = Toast.LENGTH_SHORT;
		Toast toast;
		toast = Toast.makeText(this, ""+position, duration);
		toast.show();*/
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
