package com.doyouspeak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class ChooseCategory extends Activity {
	public final static String ID_EXTRA="";
	ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;
	
	RadioButton category1;
	RadioButton category2;
	RadioButton category3;
	RadioButton category4;
	RadioButton category5;
	RadioButton category6;
	RadioButton category7;
	RadioButton category8;
	RadioButton category9;
	RadioButton category10;

	Context context;
	Model model;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_category);
        
        String[] myExpressions={"Teraz napiszę bardzo długi tekst, co Ty na to?", "ipsum", "dolor",
        		"sit", "amet",
        		"consectetuer", "adipiscing", "elit", "morbi", "vel",
        		"ligula", "vitae", "arcu", "aliquet", "mollis",
        		"etiam", "vel", "erat", "placerat", "ante",
        		"porttitor", "sodales", "pellentesque", "augue", "purus"};
            
         Context ctx = getApplicationContext(); 
         context = getApplicationContext();
         model = new Model(context);
         
         actionHome = (ImageButton)findViewById(R.id.actionHome);
         actionList = (ImageButton)findViewById(R.id.actionMyList);
         actionRecord = (ImageButton)findViewById(R.id.actionRecord);
         
         category1 = (RadioButton)findViewById(R.id.category1);
         category2 = (RadioButton)findViewById(R.id.category2);
         category3 = (RadioButton)findViewById(R.id.category3);
         category4 = (RadioButton)findViewById(R.id.category4);
         category5 = (RadioButton)findViewById(R.id.category5);
         category6 = (RadioButton)findViewById(R.id.category6);
         category7 = (RadioButton)findViewById(R.id.category7);
         category8 = (RadioButton)findViewById(R.id.category8);
         category9 = (RadioButton)findViewById(R.id.category9);
         category10 = (RadioButton)findViewById(R.id.category10);
         
         actionHome.setOnClickListener(lHome);
         actionList.setOnClickListener(lList);
         actionRecord.setOnClickListener(lRecord);



         
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
			i = new Intent(context, ChooseCategory.class);
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
	
	public void load() {
		Cursor c = model.getAllCategories();
		Log.d("xx", "qw");
		String [] cat = null;
		int i = 0;
		c.moveToFirst();
		Log.d("cat1", model.getCategory(c));
		while(!c.isAfterLast()) {
			//cat[i++] = model.getCategory(c);
			c.moveToNext();
		}
		
	}
	
}




