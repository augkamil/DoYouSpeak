package com.doyouspeak;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class Expressions extends ExpandableListActivity {
	private ExpandableListAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expressions);    
        
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);

        actionBar.setHomeAction(new IntentAction(this, DoYouSpeak.createIntent(this), R.drawable.ic_action_home));
        actionBar.setTitle(R.string.app_name);  
        final Action my_ListAction = new IntentAction(this, new Intent(this, MyList.class), R.drawable.ic_action_my_list);
        actionBar.addAction(my_ListAction);
        final Action recordAction = new IntentAction(this, new Intent(this, RecordExpression.class), R.drawable.ic_action_record);
        actionBar.addAction(recordAction);
        
        String[] myExpressions={"lorem", "ipsum", "dolor",
        		"sit", "amet",
        		"consectetuer", "adipiscing", "elit", "morbi", "vel",
        		"ligula", "vitae", "arcu", "aliquet", "mollis",
        		"etiam", "vel", "erat", "placerat", "ante",
        		"porttitor", "sodales", "pellentesque", "augue", "purus"};
            
         Context ctx = getApplicationContext(); 
         
         //setListAdapter(new expressionsAdapter(ctx, R.layout.expressions_element, myExpressions));
      // Retrive the ExpandableListView from the layout
         ExpandableListView listView = (ExpandableListView) findViewById(R.id.expListView);
         
         listView.setOnChildClickListener(new OnChildClickListener()
         {
             
             @Override
             public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
             {
                 Toast.makeText(getBaseContext(), "Child clicked", Toast.LENGTH_LONG).show();
                 return false;
             }
         });
         
         listView.setOnGroupClickListener(new OnGroupClickListener()
         {
             
             @Override
             public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3)
             {
                 Toast.makeText(getBaseContext(), "Group clicked", Toast.LENGTH_LONG).show();
                 return false;
             }
         });

         // Initialize the adapter with blank groups and children
         // We will be adding children on a thread, and then update the ListView
         adapter = new ExpandableListAdapter(this, new ArrayList<String>(),
                 new ArrayList<ArrayList<Expression>>());

         // Set this blank adapter to the list view
         listView.setAdapter(adapter);
       
   	}
	
}

class ExpandableListAdapter extends BaseExpandableListAdapter {

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    private Context context;

    private ArrayList<String> groups;

    private ArrayList<ArrayList<Expression>> children;

    public ExpandableListAdapter(Context context, ArrayList<String> groups,
            ArrayList<ArrayList<Expression>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }

    /**
     * A general add method, that allows you to add a Vehicle to this list
     * 
     * Depending on if the category opf the vehicle is present or not,
     * the corresponding item will either be added to an existing group if it 
     * exists, else the group will be created and then the item will be added
     * @param vehicle
     */
    public void addItem(Expression expression) {
        if (!groups.contains(expression.getGroup())) {
            groups.add(expression.getGroup().toString());
        }
        int index = groups.indexOf(expression.getGroup());
        if (children.size() < index + 1) {
            children.add(new ArrayList<Expression>());
        }
        children.get(index).add(expression);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    // Return a child view. You can load your custom layout here.
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
    	Expression exp = (Expression) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expressions_element, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.expressionsElement);
        tv.setText("   " + exp.getExpression());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // Return a group view. You can load your custom layout here.
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expressions_group, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.expressionsGroupElement);
        tv.setText(group);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}

/*
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
		
		TextView tv = (TextView)convertView.findViewById(R.id.expressionsElement);
		tv.setText(mStrings[position]);
		
		return convertView;
	}
}
*/