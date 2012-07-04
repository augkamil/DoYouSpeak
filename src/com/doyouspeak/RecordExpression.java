package com.doyouspeak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class RecordExpression extends Activity {
	Expression exp = new Expression();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_exp);
    
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);

        actionBar.setHomeAction(new IntentAction(this, DoYouSpeak.createIntent(this), R.drawable.ic_action_home));
        actionBar.setTitle(R.string.app_name);
        final Action my_ListAction = new IntentAction(this, new Intent(this, MyList.class), R.drawable.ic_action_my_list);
        actionBar.addAction(my_ListAction);
        final Action recordAction = new IntentAction(this, new Intent(this, RecordExpression.class), R.drawable.ic_action_record);
        actionBar.addAction(recordAction);
        
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

}
