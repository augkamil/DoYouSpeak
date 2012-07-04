package com.doyouspeak;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class Expressions extends ListActivity {
	private TextView expression;
	TextView txt;
	
	private static final String[] myExpressions={"lorem", "ipsum", "dolor",
		"sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue", "purus"};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expressions);
        
        //txt = (TextView)findViewById(R.id.autoCompleteTextView1);
    
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);

        actionBar.setHomeAction(new IntentAction(this, DoYouSpeak.createIntent(this), R.drawable.ic_action_home));
        actionBar.setTitle(R.string.app_name);  
        final Action my_ListAction = new IntentAction(this, new Intent(this, MyList.class), R.drawable.ic_action_my_list);
        actionBar.addAction(my_ListAction);
        final Action recordAction = new IntentAction(this, new Intent(this, RecordExpression.class), R.drawable.ic_action_record);
        actionBar.addAction(recordAction);
       
        //setListAdapter(new SimpleCursorAdapter(this, R.layout.expressions, null, myExpressions));
	}
	

}
