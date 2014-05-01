package com.br.discadorbr.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.discadorbr.model.Prefixo;

public class PrefixoDao extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "com.br.discadorbr";

	// Clients table name
	private static final String TABLE_PREFIXO = "prefixo";

	// Clients Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_LABEL = "label";
	private static final String KEY_NUMERO = "numero";
	private static final String KEY_DESCRICAO = "descrição";
	private static final String KEY_ISDEFAULT = "is_default";

	public PrefixoDao(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ClientS_TABLE = "CREATE TABLE " + TABLE_PREFIXO + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_LABEL + " TEXT,"
				+ KEY_NUMERO + " INTEGER, " + KEY_DESCRICAO +  KEY_ISDEFAULT + " INTEGER" + ")";
		db.execSQL(CREATE_ClientS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREFIXO);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	public void add(Prefixo prefixo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = defaultContentValues(prefixo);

		// Inserting Row
		db.insert(TABLE_PREFIXO, null, values);
		db.close(); 
	}

	public Prefixo getById(int id) {
		//SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_PREFIXO + " WHERE " + KEY_ID +" = "+ id + "ORDER BY " + KEY_ID +" ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();

		Prefixo prefixo = new Prefixo();
		prefixo.setId(Integer.parseInt(cursor.getString(0)));
		prefixo.setLabel(cursor.getString(1));
		prefixo.setNumero(cursor.getInt(2));
		prefixo.setDescricao(cursor.getString(3));
		prefixo.setIsDefault(Boolean.valueOf(String.valueOf(cursor.getInt(4))));

		return prefixo;
	}
	
	public Prefixo getDefault() {
		//SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_PREFIXO + " WHERE " + KEY_ISDEFAULT +" = 1 LIMIT 1";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor != null)
			cursor.moveToFirst();

		Prefixo prefixo = new Prefixo();
		prefixo.setId(Integer.parseInt(cursor.getString(0)));
		prefixo.setLabel(cursor.getString(1));
		prefixo.setNumero(cursor.getInt(2));
		prefixo.setDescricao(cursor.getString(3));
		prefixo.setIsDefault(Boolean.valueOf(String.valueOf(cursor.getInt(4))));

		return prefixo;
	}


	public List<Prefixo> getAll() {
		List<Prefixo> prefixoList = new ArrayList<Prefixo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PREFIXO + " ORDER BY " + KEY_ID +" ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Prefixo prefixo = new Prefixo();
				prefixo.setId(Integer.parseInt(cursor.getString(0)));
				prefixo.setLabel(cursor.getString(1));
				prefixo.setNumero(cursor.getInt(2));
				prefixo.setDescricao(cursor.getString(3));
				prefixo.setIsDefault(Boolean.valueOf(String.valueOf(cursor.getInt(4))));

				// Adding Prefixo to list
				prefixoList.add(prefixo);
			} while (cursor.moveToNext());
		}


		return prefixoList;
	}


	public int update(Prefixo prefixo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = defaultContentValues(prefixo);

		// updating row
		return db.update(TABLE_PREFIXO, values, KEY_ID + " = ?",
				new String[] { String.valueOf(prefixo.getId()) });
	}


	public void deleteClient(Prefixo prefixo) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PREFIXO, KEY_ID + " = ?",
				new String[] { String.valueOf(prefixo.getId()) });
		db.close();
	}

	// Getting Clients Count
	public int getCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PREFIXO;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public List<Prefixo> searchClients(String term) {
		List<Prefixo> prefixoList = new ArrayList<Prefixo>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_PREFIXO + " WHERE "
				+ KEY_LABEL + " LIKE '%" + term + "%'" + " OR " + KEY_DESCRICAO
				+ " LIKE '%" + term + "%'" + " OR " + KEY_NUMERO + " LIKE '%"
				+ term + "%'" + " ORDER BY " + KEY_LABEL +" ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Prefixo prefixo = new Prefixo();
				//Prefixo prefixo = new Prefixo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
				prefixo.setId(Integer.parseInt(cursor.getString(0)));
				prefixo.setLabel(cursor.getString(1));
				prefixo.setNumero(cursor.getInt(2));
				prefixo.setDescricao(cursor.getString(3));
				prefixo.setIsDefault(Boolean.valueOf(String.valueOf(cursor.getInt(4))));

				prefixoList.add(prefixo);
			} while (cursor.moveToNext());
		}


		return prefixoList;
	}
	
	
	// DEFAULT PRIVATE OPERATIONS
	
	private ContentValues defaultContentValues(Prefixo prefixo) {
		ContentValues values = new ContentValues();
		values.put(KEY_LABEL, prefixo.getLabel());
		values.put(KEY_NUMERO, prefixo.getNumero());
		values.put(KEY_LABEL, prefixo.getLabel());
		values.put(KEY_DESCRICAO, prefixo.getDescricao());
		values.put(KEY_ISDEFAULT, String.valueOf(prefixo.isDefault()));
		
		return values;
	}

}
