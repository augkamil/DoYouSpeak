package com.doyouspeak;

import java.io.IOException;
import java.util.ArrayList;

import com.doyouspeak.Expressions.ExpressionsAdapter;
import com.doyouspeak.Expressions.RowModel;
import com.doyouspeak.Expressions.ViewHolder;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MyList extends ListActivity {

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
	MediaPlayer mp;
	int[] myIds;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_list);
		
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
			
			if(isFavourite(expressions.getInt(0))) {
				list.add(new RowModel(model.getText(expressions), true, expressions.getInt(0)));
			}
			
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
	
	public void play(Context context, String path) {

		String newPath = "file://"+path;
		Log.d("PATH", newPath);
		mp = MediaPlayer.create(context, Uri.parse(newPath));
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				if (mp.isPlaying()) {
					mp.stop();
				}
			}
	   });
	}
	
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
		ImageButton btChecked = null;
		ImageButton playButton = null;
		
		ViewHolder(View base) {
			this.btChecked = (ImageButton)base.findViewById(R.id.star_on_button);
			this.playButton = (ImageButton)base.findViewById(R.id.play_button);
		}
	}
	
	private RowModel getModel(int position) {
		return (((ExpressionsAdapter)getListAdapter()).getItem(position));
	}
	
	class ExpressionsAdapter extends ArrayAdapter<RowModel> {

		public ExpressionsAdapter(ArrayList<RowModel> list) {
			super(MyList.this, R.layout.my_list_element, R.id.my_list_item, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = super.getView(position, convertView, parent);
			ViewHolder holder = (ViewHolder)row.getTag();
			final int pos = position;
			
			final RowModel rModel = getModel(pos);
			TextView expressionsElement = (TextView)row.findViewById(R.id.my_list_item);
			expressionsElement.setText(rModel.expression);
			
			if(holder==null) {
				holder = new ViewHolder(row);
				row.setTag(holder);
				
				row.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						i = new Intent(MyList.this, Details.class);
						i.putExtra("id_rec", rModel.id_rec);
						i.putExtra("checked", rModel.checked);
						startActivity(i);
					}
					
				});
				
				holder.btChecked.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Integer myPosition = (Integer)v.getTag();
						RowModel rModel = getModel(myPosition);
						
						model.deleteMyRecordIdRec(""+rModel.id_rec);	
						onCreate(null);
					}
					
				});
				
				holder.playButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Integer myPosition = (Integer)v.getTag();
						RowModel rModel = getModel(myPosition);
						
						Cursor c = model.getRecordById(""+rModel.id_rec);
						c.moveToFirst();
						play(context, model.getPath(c));		
					}
					
				});
				
			}
			
			RowModel model = getModel(position);
			holder.btChecked.setTag(new Integer(position));
			holder.playButton.setTag(new Integer(position));
			
			return row;
		}
		
		
		
	}

}

