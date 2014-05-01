package com.br.discador.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ConexaoUtil {

	public static final String DATABASE_NAME = "discador_br";
	
	public static SQLiteDatabase getDatabaseFromContext(Context context){
		return context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
	}
}
