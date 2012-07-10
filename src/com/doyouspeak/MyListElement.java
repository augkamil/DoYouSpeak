package com.doyouspeak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyListElement extends Activity {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.details);
	    
	    //TextView txtExp = (TextView) findViewById(R.id.my_list_item);
	 
	    //Intent i = getIntent();
	    // getting attached intent data
	   // String exp = i.getStringExtra("exp_item");
	    // displaying selected product name
	    //txtExp.setText(exp);
	  
	}
		
		
		
}




