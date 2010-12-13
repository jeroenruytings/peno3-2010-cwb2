package cw.kuleuven.be.peno1011.cwb2.database.local;

//NOG IN TESTFASE

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LoginDbAdaptor {
	
	private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
	
    public static final String KEY_ROWID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mobiletoledo";
    private static final String DATABASE_TABLE = "logindata";
	private static final String DATABASE_CREATE = "create table logindata" +
			"(_id integer primary key autoincrement, "
        + "username text not null, password text not null);";
	public static final String TAG = "mobiletoledo";
	
	private final Context mContext;
	private static LoginDbAdaptor loginDbAdaptor;

	private LoginDbAdaptor(Context context){
		this.mContext = context;
		this.open();
    }
	
	public static LoginDbAdaptor getInstance(Context context){
		if (loginDbAdaptor == null) {
			loginDbAdaptor = new LoginDbAdaptor(context);
		}
		return loginDbAdaptor;
	}

	public LoginDbAdaptor open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
    	DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS logindata");
            onCreate(db);
        }
    }
    
    public long addUser(String username, String password) {
     	mDbHelper.onUpgrade(mDb, 1, 1);
    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        long i = mDb.insert(DATABASE_TABLE, null, initialValues);
        return i;
    }
    
    public String getPassword(long rowId) throws SQLException {
//    	if(DatabaseEmpty())
//    		return null;
    	Cursor mCursor =

            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_USERNAME, KEY_PASSWORD}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        if(mCursor.getCount() > 0){
        	String password = mCursor.getString(2);
        	mCursor.close();
        	return password;
        	}
        mCursor.close();
        return null;
    }
    
	public String getUsername(long rowId) throws SQLException {
    	Cursor mCursor =
            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_USERNAME, KEY_PASSWORD}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        if(mCursor.getCount() > 0){
        	String username = mCursor.getString(1);
        	mCursor.close();
        	return username;
        }
        mCursor.close();
        return null;
        }
}
