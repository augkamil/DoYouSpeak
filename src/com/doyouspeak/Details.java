package com.doyouspeak;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Details extends Activity {

	TextView txt;
	ImageButton actionHome;
	ImageButton actionList;
	ImageButton actionRecord;
	
	ToggleButton favouriteButton;
	TextView expression;
	TextView record_text;
	ImageButton listenButton;
	ImageButton recordButton; 
	ImageButton historyButton1;
	ImageButton historyButton2;
	ImageButton historyButton3;
	ImageButton next_img;
	ImageButton previous_img;
	
	Intent i = null;
	int id_rec;
	boolean checked;
	Cursor exp;
	Cursor myExp;
	Model model;
	MediaPlayer mp;
	AudioRecorder recorder;
	
	boolean record = false;
	boolean play = false;
	boolean play1 = false;
	boolean play2 = false;
	boolean play3 = false;
	
	Context context;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        
        context = getApplicationContext();
        model = new Model(context);
        
        actionHome = (ImageButton)findViewById(R.id.actionHome);
        actionList = (ImageButton)findViewById(R.id.actionMyList);
        actionRecord = (ImageButton)findViewById(R.id.actionRecord);
        
        favouriteButton = (ToggleButton)findViewById(R.id.detailsFavouriteButton);
        expression = (TextView)findViewById(R.id.detailsExpression);
        record_text = (TextView)findViewById(R.id.record_text);
        listenButton = (ImageButton)findViewById(R.id.listenButton);
        recordButton = (ImageButton)findViewById(R.id.recordButton);
        historyButton1 = (ImageButton)findViewById(R.id.historyButton1);
        historyButton2 = (ImageButton)findViewById(R.id.historyButton2);
        historyButton3 = (ImageButton)findViewById(R.id.historyButton3);
        next_img = (ImageButton)findViewById(R.id.next_img);
        previous_img = (ImageButton)findViewById(R.id.previous_img);
        
        actionHome.setOnClickListener(lHome);
        actionList.setOnClickListener(lList);
        actionRecord.setOnClickListener(lRecord);
        
        favouriteButton.setOnClickListener(lFavouriteButton);
        listenButton.setOnClickListener(lListenButton);
        recordButton.setOnClickListener(lRecordButton);
        historyButton1.setOnClickListener(lHistoryButton1);
        historyButton2.setOnClickListener(lHistoryButton2);
        historyButton3.setOnClickListener(lHistoryButton3);
        next_img.setOnClickListener(lNext_img);
        previous_img.setOnClickListener(lPrevious_img);
          
        
        id_rec = getIntent().getIntExtra("id_rec", 0);
        checked = getIntent().getBooleanExtra("checked", false);
        
        favouriteButton.setChecked(checked);
        
        exp = model.getRecordById(""+id_rec);
        exp.moveToFirst();
        expression.setText(model.getText(exp));
        
        myExp = model.getMyRecordByIdRec(""+id_rec);
        myExp.moveToFirst();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		model.close();
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
	
	private View.OnClickListener lFavouriteButton = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if(favouriteButton.isChecked()) {
				model.insertMyRecord(id_rec, "", "", "");
			}
			else {
				model.deleteMyRecordIdRec(""+id_rec);
			}
		}
	};
	
	private View.OnClickListener lListenButton = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			play(context, model.getPath(exp));
		}
	};
	
	private View.OnClickListener lRecordButton = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			recordMy(context, id_rec);
			myExp = model.getMyRecordByIdRec(""+id_rec);
	        myExp.moveToFirst();
		}
	};
	
	private View.OnClickListener lHistoryButton1 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			play(context, myExp.getString(2));
		}
	};
	
	private View.OnClickListener lHistoryButton2 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			play(context, myExp.getString(3));
		}
	};
	
	private View.OnClickListener lHistoryButton3 = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			play(context, myExp.getString(4));
		}
	};
	
	private View.OnClickListener lNext_img = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			
		}
	};
	
	private View.OnClickListener lPrevious_img = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			
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

	public void recordMy(Context context, int id_rec) {
		if(!record) {
			record = true;	
			record_text.setText("Nagrywanie...");
			Cursor c = model.getRecordById(""+id_rec);
			c.moveToFirst();
			String path = model.getPath(c);
			Log.d("path", path);

			path = path.substring(0, path.length()-4); //usunięcie .3gp
			Log.d("path", path);

			String newPath1;
			String newPath2;
			String newPath3;
			String newPath;

			c = model.getMyRecordByIdRec(""+id_rec);
			c.moveToFirst();

			if(c.getString(2).isEmpty()) {	
				newPath = newPath1 = path+"_1.3gp";
				newPath2 = "";
				newPath3 = "";
			}
			else if (c.getString(3).isEmpty()) {
				newPath1 = c.getString(2);
				newPath = newPath2 = path+"_2.3gp";
				newPath3 = "";
			}
			else if (c.getString(4).isEmpty()) {
				newPath1 = c.getString(2);
				newPath2 = c.getString(3);
				newPath = newPath3 = path+"_3.3gp";
			}
			else {
				newPath1 = c.getString(3);
				newPath2 = c.getString(4);
				newPath = newPath3 = c.getString(2);
			}

			Log.d("newPath", newPath);
			Log.d("newPath1", newPath1);
			Log.d("newPath2", newPath2);
			Log.d("newPath3", newPath3);
			Log.d("id", ""+c.getInt(0));
			Log.d("id_rec", ""+id_rec);

			recorder = new AudioRecorder(newPath);
			model.updateMyRecord(""+c.getInt(0), id_rec, newPath1, newPath2, newPath3);

			try {
				recorder.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			record = false;
			Log.d("stop", "rec");
			record_text.setText("Nagraj Siebie");
			try {
				recorder.stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
