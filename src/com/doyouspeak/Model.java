package com.doyouspeak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


class Model extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="venture2.db";
	private static final int SCHEMA_VERSION=1;

	public Model(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE config (_id INTEGER PRIMARY KEY AUTOINCREMENT, key TEXT, value TEXT);");
		db.execSQL("CREATE TABLE category (_id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT);");
		db.execSQL("CREATE TABLE mylist (_id INTEGER PRIMARY KEY AUTOINCREMENT, id_rec INTEGER, path_my_record_1 TEXT, path_my_record_2 TEXT, path_my_record_3 TEXT);");
		db.execSQL("CREATE TABLE record (_id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT, path TEXT, id_cat INTEGER);");	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

    
	//CONFIG TABLE

	public void insertConfiguration(String key, String value) {
		ContentValues cv=new ContentValues();
 
		cv.put("key", key);
		cv.put("value", value);

		getWritableDatabase().insert("config", "key", cv);
	}

	public Cursor getByKey(String k) {
		String[] args={k};
		return(getReadableDatabase().rawQuery("SELECT _id, key, value FROM config WHERE key=?", args));
	}

	public String getKey(Cursor c) {
	    return(c.getString(1));
	}

	public String getValue(Cursor c) {
	    return(c.getString(2));
	}

	public void updateConfiguration(String id, String key, String value) {
		ContentValues cv=new ContentValues();
		String[] args={id};

		cv.put("key", key);
		cv.put("value", value);

		getWritableDatabase().update("config", cv, "_ID=?", args);
	}

	public void deleteConfiguration(String id) {
		String[] args={id};
		getWritableDatabase().delete("config", "_ID=?", args);
	}


	//CATEGORY TABLE

	public long insertCategory(String category) {
		ContentValues cv=new ContentValues();
 
		cv.put("category", category);

		long t = getWritableDatabase().insert("category", "category", cv);
		return t;
	}

	public Cursor getCategoryById(String id_cat) {
		String[] args={id_cat};
		return(getReadableDatabase().rawQuery("SELECT _id, category FROM category WHERE _ID=?", args));
	}
	
	public Cursor getAllCategories() {
		return getReadableDatabase().rawQuery("SELECT * FROM category", null);
	}

	public String getCategory(Cursor c) {
	    return(c.getString(1));
	}

	public void deleteCategory(String id) {
		String[] args={id};
		getWritableDatabase().delete("category", "_ID=?", args);
	}

	public void deleteAllCategories() {
		getWritableDatabase().delete("category", null, null);
	}


	//MYLIST TABLE

	public long insertMyRecord(int id_rec, String path_my_record_1, String path_my_record_2, String path_my_record_3) {
		ContentValues cv=new ContentValues();

		cv.put("id_rec", id_rec);
		cv.put("path_my_record_1", path_my_record_1);
		cv.put("path_my_record_2", path_my_record_2);
		cv.put("path_my_record_3", path_my_record_3);

		long t = getWritableDatabase().insert("mylist", "id_rec", cv);
		return t;
	}

	public Cursor getAllMyRecords() {
	    return(getReadableDatabase().rawQuery("SELECT * FROM mylist", null));
	}

	public Cursor getMyRecordByIdRec(String id) {		
		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT _id, id_rec, path_my_record_1, path_my_record_2, path_my_record_3 FROM mylist WHERE id_rec=?", args));
	}

	public Cursor getMyRecordById(String id) {		
		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT _id, id_rec, path_my_record_1, path_my_record_2, path_my_record_3 FROM mylist WHERE _ID=?", args));
	}

	public int updateMyRecord(String id, int id_rec, String path_my_record_1, String path_my_record_2, String path_my_record_3) {
		ContentValues cv=new ContentValues();
		String[] args={id};

		cv.put("id_rec", id_rec);
		cv.put("path_my_record_1", path_my_record_1);
		cv.put("path_my_record_2", path_my_record_2);
		cv.put("path_my_record_3", path_my_record_3);

		return getWritableDatabase().update("mylist", cv, "_ID=?", args);
	}

	public void deleteMyRecord(String id) {
		String[] args={id};
		getWritableDatabase().delete("mylist", "_ID=?", args);
	}


	//RECORD TABLE

	public long insertRecord(String text, String path, int id_cat) {
		ContentValues cv=new ContentValues();

		cv.put("text", text);
		cv.put("path", path);
		cv.put("id_cat", id_cat);

		long t = getWritableDatabase().insert("record", "text", cv);
		return t;
	}

	public Cursor getAllRecords() {
	    return(getReadableDatabase().rawQuery("SELECT _id, text, path, id_cat FROM record", null));
	}

	public Cursor getRecordById(String id) {		
		String[] args={id};
		return(getReadableDatabase().rawQuery("SELECT _id, text, path, id_cat FROM record WHERE _ID=?", args));
	}

	public String getText(Cursor c) {
	    return(c.getString(1));
	}

	public String getPath(Cursor c) {
	    return(c.getString(2));
	}

	public int getRecordCategory(Cursor c) {
	    return(c.getInt(3));
	}

	public void updateRecord(String id, String text, String path, int id_cat) {
		ContentValues cv=new ContentValues();
		String[] args={id};

		cv.put("text", text);
		cv.put("path", path);
		cv.put("id_cat", id_cat);

		getWritableDatabase().update("record", cv, "_ID=?", args);
	}

	public void deleteRecord(String id) {
		String[] args={id};
		getWritableDatabase().delete("record", "_ID=?", args);
	}

	public void deleteAllRecords() {
		getWritableDatabase().delete("record", null, null);
	}

}