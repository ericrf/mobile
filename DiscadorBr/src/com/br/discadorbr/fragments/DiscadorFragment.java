package com.br.discadorbr.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.br.discador.R;
import com.br.discadorbr.controller.PrefixoController;
import com.br.discadorbr.model.Prefixo;

public class DiscadorFragment extends Fragment {
	private int prefix;
	private TextView numberToCall;
	private Prefixo prefixo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_discador, container,
				false);

		numberToCall = (TextView) getView().findViewById(R.id.numberToCall);

		int[] buttons = { R.id.button1, R.id.button2, R.id.button3,
				R.id.button4, R.id.button5, R.id.button6, R.id.button7,
				R.id.button8, R.id.button9, R.id.buttonAst, R.id.button0,
				R.id.buttonSus };
		for (int i = 0; i < buttons.length; i++) {
			Button buttonNum = (Button) rootView.findViewById(buttons[i]);
			buttonNum.setOnClickListener(addNumToDialer);
		}

		ImageButton buttonUndo = (ImageButton) rootView
				.findViewById(R.id.buttonUndo);
		buttonUndo.setOnClickListener(delNumDialer);

		ImageButton buttonCall = (ImageButton) rootView
				.findViewById(R.id.buttonCall);
		buttonCall.setOnClickListener(makeCall);

		Button buttonCobrar = (Button) rootView.findViewById(R.id.buttonCobrar);
		buttonCobrar.setOnClickListener(makeCallCobrarListener);
		buttonCobrar.setOnLongClickListener(choosePrefixoListener);

		return rootView;
	}

	private OnClickListener addNumToDialer = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Button button = (Button) v;
			String number = button.getText().toString();

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
				startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "
						+ telNumber)));
			}
		}
	};

	private void createPrefixo() {

		final EditText input = new EditText(getActivity()
				.getApplicationContext());

		// Show new prefixo number input dialog
		new AlertDialog.Builder(getActivity()).setTitle("Nome do arquivo")
				.setView(input)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() { // button ok
					public void onClick(DialogInterface dialog, int whichButton) { 
						Editable newDir = input.getText();
						try { // try add new prefixo
							prefixo = new Prefixo(Integer.parseInt(newDir
									.toString()));
							PrefixoController.add(getActivity()
									.getApplicationContext(), prefixo);
							makeCallPrefixo(); // make call with new prefixo

						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				}).setNegativeButton("Cancel", null).show(); // button cancel

	}

	private OnClickListener makeCallCobrarListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			prefixo = PrefixoController.getDefault(getActivity().getApplicationContext());
			if (prefixo == null) {
				createPrefixo();
			}
			else {
				makeCallPrefixo();
			}

		}
	};
	
	private OnLongClickListener choosePrefixoListener = new OnLongClickListener() {
	@Override
    public boolean onLongClick(View v) {

        Toast.makeText(getActivity(), "Long preess", Toast.LENGTH_LONG).show();

        return true;
    }
	};

	private void makeCallPrefixo() {
		String telNumber = numberToCall.getText().toString().trim();

		if (telNumber.length() != 0 && telNumber.length() >= 3) {
			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+prefixo.getNumero()
					+ telNumber)));
		}
	}
}