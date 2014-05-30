package com.br.discadorbr.fragments.listeners;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.br.discador.R;
import com.br.discadorbr.MainActivity;

public class RealizarChamadaOnClickListener implements OnClickListener {

	private View view;
	private MainActivity activity;
	private boolean hasPrefix;
	private String numero;

	public RealizarChamadaOnClickListener(MainActivity activity, View view, boolean hasPrefix) {
		this.activity = activity;
		this.view = view;
		this.hasPrefix = hasPrefix;
	}

	@Override
	public void onClick(View v) {
		Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(50L);
		TextView numeroTextView = (TextView) view
				.findViewById(R.id.numberToCall);
		numero = numeroTextView.getText().toString().trim();
		if (numero.length() != 0 && numero.length() >= 3) {
			if (!hasPrefix) {
				RealizadorDeChamadas.detectPrefixo(activity, numero);
			} else {
				RealizadorDeChamadas.callContact(activity, numero, "");
			}
		}
	}
	


}
