package com.br.discadorbr.fragments.listeners;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class RealizadorDeChamadas {

	private static final String PREFIXO = "9090";

	private static String formatarNumero(String numero) {
		String n = numero.replaceAll("[^0-9]", "");
		try {
			n = n.substring((n.length() - 8), n.length()); 
				
		} catch (StringIndexOutOfBoundsException e) {
			n = numero;
		}
		return n; 
	}

	public static void callContact(Activity activity, String numero) {
		String s = "tel: " + PREFIXO + formatarNumero(numero);
		activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(s)));
	}
	
	public static void callContact(Activity activity, String numero,String prefixo) {
		String s = "tel: " + prefixo + formatarNumero(numero);
		activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(s)));
	}
}
