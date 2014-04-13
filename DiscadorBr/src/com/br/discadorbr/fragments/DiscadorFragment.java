package com.br.discadorbr.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.br.discador.R;

public class DiscadorFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_discador, container,
				false);

		int[] buttons = { R.id.button1, R.id.button2, R.id.button3,
				R.id.button4, R.id.button5, R.id.button6, R.id.button7,
				R.id.button8, R.id.button9, R.id.buttonAst, R.id.button0,
				R.id.buttonSus };
		for (int i = 0; i < buttons.length; i++) {
			Button buttonNum = (Button) rootView.findViewById(buttons[i]);
			buttonNum.setOnClickListener(addNumToDialer);
		}

		ImageButton buttonUndo = (ImageButton) rootView.findViewById(R.id.buttonUndo);
		buttonUndo.setOnClickListener(delNumDialer);
		
		ImageButton buttonCall = (ImageButton) rootView.findViewById(R.id.buttonCall);
		buttonCall.setOnClickListener(makeCall);
		
		Button buttonCobrar = (Button) rootView.findViewById(R.id.buttonCobrar);
		buttonCobrar.setOnClickListener(makeCallCobrar);

		return rootView;
	}

	private OnClickListener addNumToDialer = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Button button = (Button) v;
			String number = button.getText().toString();
			TextView numberToCall = (TextView) getView().findViewById(
					R.id.numberToCall);
			numberToCall.setText(numberToCall.getText() + number);
		}
	};

	private OnClickListener delNumDialer = new OnClickListener() {
		@Override
		public void onClick(View v) {
			TextView numberToCall = (TextView) getView().findViewById(
					R.id.numberToCall);

			String str = numberToCall.getText().toString().trim();

			if (str.length() != 0) {
				str = str.substring(0, str.length() - 1);

				numberToCall.setText(str);
			}
		}
	};
	
	private OnClickListener makeCall = new OnClickListener() {
		@Override
		public void onClick(View v) {
			TextView numberToCall = (TextView) getView().findViewById(
					R.id.numberToCall);

			String telNumber = numberToCall.getText().toString().trim();

			if (telNumber.length() != 0 && telNumber.length() >= 3) {
				startActivity(new Intent(Intent.ACTION_CALL, 
	                       Uri.parse("tel: " + telNumber)));
			}
		}
	};
	

	private OnClickListener makeCallCobrar = new OnClickListener() {
		@Override
		public void onClick(View v) {
			TextView numberToCall = (TextView) getView().findViewById(
					R.id.numberToCall);

			String telNumber = numberToCall.getText().toString().trim();

			if (telNumber.length() != 0 && telNumber.length() >= 3) {
				startActivity(new Intent(Intent.ACTION_CALL, 
	                       Uri.parse("tel: 9090" + telNumber)));
			}
		}
	};
}