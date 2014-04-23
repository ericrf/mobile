package com.br.discadorbr.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.discador.util.ConexaoUtil;
import com.br.discadorbr.model.Prefixo;

public class PrefixoDataAccessObject {
	SQLiteDatabase database;
	private static final String TABELA = "prefixo";

	public PrefixoDataAccessObject(Context context) {
		database = ConexaoUtil.getDatabaseFromContext(context);
		createTable();
	}

	private void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + "("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT," + "	label TEXT,"
				+ "	numero INTEGER," + "	descricao TEXT,"
				+ "	is_selected INTEGER" + ");";

		database.execSQL(sql);
	}

	public long insert(Prefixo prefixo) {

		ContentValues values = getContentValues(prefixo);
		database.beginTransaction();
		long insert = database.insert(TABELA, null, values);
		database.endTransaction();
		
		return insert;

	}
	
	public Cursor findAll(){
		return database.rawQuery("SELECT * FROM "+TABELA+"", null);
	}
	
	private ContentValues getContentValues(Prefixo prefixo){
		ContentValues values = new ContentValues();
		if(prefixo.getId() != 0)
			values.put("id", prefixo.getId());
		
		values.put("label", prefixo.getLabel());
		values.put("numero", prefixo.getNumero());
		values.put("descricao", prefixo.getDescricao());
		values.put("is_selected", (prefixo.isDefault() ? 1 : 0));
		
		return values;
	}
}
