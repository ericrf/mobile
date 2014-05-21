package com.br.discadorbr.fragments.listeners;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.br.discador.R;

public class RealizarChamadaOnClickListener implements OnClickListener {

	private View view;
	private Activity activity;

	public RealizarChamadaOnClickListener(Activity activity, View view) {
		this.activity = activity;
		this.view = view;
	}

	@Override
	public void onClick(View v) {
		TextView numeroTextView = (TextView) view
				.findViewById(R.id.numberToCall);

		String numero = numeroTextView.getText().toString().trim();
		if (numero.length() != 0 && numero.length() >= 3) {
			RealizadorDeChamadas.callContact(activity, numero);
		}
	}

}
