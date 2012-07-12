package com.doyouspeak;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Expressions extends ListActivity {

	Context context;
	ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	Intent i = null;
	Model model;
	Cursor expressions;
	Cursor myExpressions;
	int myExpressionsCount;
	ArrayList<RowModel> list;
	
	int[] myIds;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expressions);
		
		context = getApplicationContext();
        model = new Model(context);
      
        actionHome = (ImageButton)findViewById(R.id.actionHome);
	    actionList = (ImageButton)findViewById(R.id.actionMyList);
	    actionRecord = (ImageButton)findViewById(R.id.actionRecord);
	     
	    actionHome.setOnClickListener(lHome);
	    actionList.setOnClickListener(lList);
	    actionRecord.setOnClickListener(lRecord);
	    
	    list = new ArrayList<RowModel>();
	    
	    expressions = model.getAllRecords();
	    myExpressions = model.getAllMyRecords();
	    
	    loadFromDatabase();
	    
	    setListAdapter(new ExpressionsAdapter(list));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		model.close();
	}

	public void loadFromDatabase() {
		myExpressionsCount = myExpressions.getCount();
		myIds = new int[myExpressionsCount];
		    
		int i = 0;
		myExpressions.moveToFirst();
		while(!myExpressions.isAfterLast()) {
			myIds[i] = myExpressions.getInt(1);
		    myExpressions.moveToNext();
			i++;
		}    
		    
		i = 0;
		expressions.moveToFirst();
		while(!expressions.isAfterLast()) {
			String e = model.getText(expressions);
			boolean c = isFavourite(expressions.getInt(0));
			int id = expressions.getInt(0);
			Log.d("exp", e);
			Log.d("checked", ""+c);
			Log.d("id", ""+id);
			list.add(new RowModel(e, c, id));
			expressions.moveToNext();
			i++;
		}
	}
	
	public boolean isFavourite(int id_rec) {
		for(int i=0;i<myExpressionsCount;i++) {
			if(myIds[i]==id_rec)
				return true;
		}
		return false;
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
	
	class RowModel {
		String expression;
		boolean checked;
		int id_rec;
		
		RowModel(String expression, boolean checked, int id_rec) {
			this.expression = expression;
			this.checked = checked;
			this.id_rec = id_rec;
		}
	}
	
	class ViewHolder {
		ToggleButton btChecked = null;
		
		ViewHolder(View base) {
			this.btChecked = (ToggleButton)base.findViewById(R.id.favouriteButton);
		}
	}
	
	private RowModel getModel(int position) {
		return (((ExpressionsAdapter)getListAdapter()).getItem(position));
	}
	
	class ExpressionsAdapter extends ArrayAdapter<RowModel> {

		public ExpressionsAdapter(ArrayList<RowModel> list) {
			super(Expressions.this, R.layout.expressions_element, R.id.expressionsElement, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = super.getView(position, convertView, parent);
			ViewHolder holder = (ViewHolder)row.getTag();
			final int pos = position;
			
			final RowModel rModel = getModel(pos);
			TextView expressionsElement = (TextView)row.findViewById(R.id.expressionsElement);
			expressionsElement.setText(rModel.expression);
			
			if(holder==null) {
				holder = new ViewHolder(row);
				row.setTag(holder);
				
				row.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						i = new Intent(Expressions.this, Details.class);
						i.putExtra("id_rec", rModel.id_rec);
						i.putExtra("checked", rModel.checked);
						i.putExtra("parentActivity", "Expressions");
						startActivity(i);
					}
					
				});

				holder.btChecked.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Integer myPosition = (Integer)v.getTag();
						RowModel rModel = getModel(myPosition);
						
						if (((ToggleButton)v).isChecked()) {
							model.insertMyRecord(rModel.id_rec, "", "", "");
						}
						else {
							model.deleteMyRecordIdRec(""+rModel.id_rec);
						}
						
						rModel.checked = ((ToggleButton)v).isChecked();
						
					}
					
				});
				
			}
			
			RowModel model = getModel(position);
			holder.btChecked.setTag(new Integer(position));
			holder.btChecked.setChecked(model.checked);
			
			
			return row;
		}
		
		
		
	}

}