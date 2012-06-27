package com.doyouspeak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;


public class DoYouSpeak extends Activity implements OnItemClickListener {
 
	Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.dashboard);
        
        context = getApplicationContext();
    
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
        actionBar.setHomeAction(new IntentAction(this, DoYouSpeak.createIntent(this), R.drawable.ic_action_home));
        actionBar.setTitle(R.string.app_name);
        final Action my_ListAction = new IntentAction(this, new Intent(this, MyList.class), R.drawable.ic_action_my_list);
        actionBar.addAction(my_ListAction);
        final Action recordAction = new IntentAction(this, new Intent(this, RecordExpression.class), R.drawable.ic_action_record);
        actionBar.addAction(recordAction);
           
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

}
