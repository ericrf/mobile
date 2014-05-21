package com.br.discadorbr.fragments.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.br.discador.R;

public class RemoverNumeroParaLicagaoOnClickListener implements OnClickListener {

	private View view;

	public RemoverNumeroParaLicagaoOnClickListener(View view) {
		this.view = view;
	}

	@Override
	public void onClick(View v) {
		TextView numberTextView = (TextView) view.findViewById(R.id.numberToCall);
		String number = numberTextView.getText().toString().trim();
		if (number.length() != 0) {
			number = number.substring(0, number.length() - 1);
			numberTextView.setText(number);
		}

	}

}
