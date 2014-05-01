package com.br.discadorbr.controller;

import android.content.Context;

import com.br.discadorbr.dao.PrefixoDao;
import com.br.discadorbr.model.Prefixo;

public class PrefixoController {

	public PrefixoController() {
		// TODO Auto-generated constructor stub
	}

	public static boolean add(Context context, Prefixo prefixo) {
		PrefixoDao dao = new PrefixoDao(context);
		try {
			dao.add(prefixo);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static Prefixo getDefault(Context context) {
		PrefixoDao dao = new PrefixoDao(context);
		Prefixo prefixo = dao.getDefault();
		return prefixo;
	}

}