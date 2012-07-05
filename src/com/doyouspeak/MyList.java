package com.doyouspeak;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class MyList extends ListActivity {
	public final static String ID_EXTRA="";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list);
        
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);

        actionBar.setHomeAction(new IntentAction(this, DoYouSpeak.createIntent(this), R.drawable.ic_action_home));
        actionBar.setTitle(R.string.app_name);
        final Action my_ListAction = new IntentAction(this, new Intent(this, MyList.class), R.drawable.ic_action_my_list);
        actionBar.addAction(my_ListAction);
        final Action recordAction = new IntentAction(this, new Intent(this, RecordExpression.class), R.drawable.ic_action_record);
        actionBar.addAction(recordAction);
        
        String[] myExpressions={"Teraz napiszę bardzo długi tekst, co Ty na to?", "ipsum", "dolor",
        		"sit", "amet",
        		"consectetuer", "adipiscing", "elit", "morbi", "vel",
        		"ligula", "vitae", "arcu", "aliquet", "mollis",
        		"etiam", "vel", "erat", "placerat", "ante",
        		"porttitor", "sodales", "pellentesque", "augue", "purus"};
            
         Context ctx = getApplicationContext(); 
         
         setListAdapter(new expressionsAdapter(ctx, R.layout.my_list_element, myExpressions));
        
         
         ListView lv = getListView();
         // listening to single list item on click
         lv.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
  
               // selected item
               String exp = ((TextView) view).getText().toString();
  
               // Launching new Activity on selecting single List Item
               Intent i = new Intent(MyList.this, MyListElement.class);
               // sending data to new activity
               i.putExtra("exp_item", exp);
               startActivity(i);

           }
         });


         
	}
	
	class expressionsAdapter extends ArrayAdapter<String> {

		private LayoutInflater mInflater;
		
		private String[] mStrings;
		
		private int mViewResourceId;
		
		public expressionsAdapter(Context ctx, int viewResourceId, String[] strings) {
			super(ctx, viewResourceId, strings);
			
			mInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			mStrings = strings;
			
			mViewResourceId = viewResourceId;

		}

		@Override
		public int getCount() {
			return mStrings.length;
		}

		@Override
		public String getItem(int position) {
			return mStrings[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = mInflater.inflate(mViewResourceId, null);
			
			TextView tv = (TextView)convertView.findViewById(R.id.my_list_item);
			tv.setText(mStrings[position]);
			
			return convertView;
		}
	}
	
}




