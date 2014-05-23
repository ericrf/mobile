package com.br.discadorbr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.br.discador.R;
import com.br.discadorbr.fragments.listeners.AdicionarNumeroParaLigacaoOnClickListener;
import com.br.discadorbr.fragments.listeners.RealizarChamadaOnClickListener;
import com.br.discadorbr.fragments.listeners.RemoverNumeroParaLicagaoOnClickListener;

public class DiscadorFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		View rootView = inflater.inflate(R.layout.fragment_discador, container,
				false);

		int[] buttons = { R.id.btnIncluirPrefixo, R.id.button2, R.id.button3,
				R.id.button4, R.id.button5, R.id.button6, R.id.button7,
				R.id.button8, R.id.button9, R.id.buttonAst, R.id.button0,
				R.id.buttonSus };
		
		for (int i = 0; i < buttons.length; i++) {
			Button buttonNum = (Button) rootView.findViewById(buttons[i]);
			buttonNum.setOnClickListener(new AdicionarNumeroParaLigacaoOnClickListener(rootView));
		}

		ImageButton buttonUndo = (ImageButton) rootView.findViewById(R.id.buttonUndo);
		ImageButton buttonCall = (ImageButton) rootView.findViewById(R.id.buttonCall);
		
		
		buttonUndo.setOnClickListener(new RemoverNumeroParaLicagaoOnClickListener(rootView));
		Button buttonCobrar = (Button) rootView.findViewById(R.id.buttonCobrar);
		buttonCobrar.setOnClickListener(new RealizarChamadaOnClickListener(getActivity(), rootView, false));
		buttonCall.setOnClickListener(new RealizarChamadaOnClickListener(getActivity(), rootView, true));

		return rootView;
	}	
}