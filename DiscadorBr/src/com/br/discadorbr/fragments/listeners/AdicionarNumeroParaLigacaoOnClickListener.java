package com.br.discadorbr.fragments.listeners;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.br.discador.R;

public class AdicionarNumeroParaLigacaoOnClickListener implements
		OnClickListener {

	private View view;
	private Activity activity;

	public AdicionarNumeroParaLigacaoOnClickListener(Activity activity, View view) {
		this.activity = activity;
		this.view = view;
	}

	@Override
	public void onClick(View v) {
		Button button = (Button) v;
		String number = button.getText().toString();
		TextView numberToCall = (TextView) view.findViewById(R.id.numberToCall);
		numberToCall.setText(numberToCall.getText() + number);
		
		Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(50L);
	}

}
