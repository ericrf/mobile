package com.br.discadorbr.fragments.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.br.discador.R;

public class AdicionarNumeroParaLigacaoOnClickListener implements
		OnClickListener {

	private View view;

	public AdicionarNumeroParaLigacaoOnClickListener(View view) {
		this.view = view;
	}

	@Override
	public void onClick(View v) {
		Button button = (Button) v;
		String number = button.getText().toString();
		TextView numberToCall = (TextView) view.findViewById(R.id.numberToCall);
		numberToCall.setText(numberToCall.getText() + number);
	}

}
