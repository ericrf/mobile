package com.br.discadorbr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.br.discador.R;
import com.br.discadorbr.fragments.listeners.BtnIncluirPrefixoOnClickListener;

public class PrefixosFragment extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_prefixos, container, false);
		
		Button btnIncluirPrefixo = (Button) view.findViewById(R.id.btnIncluirPrefixo);
		btnIncluirPrefixo.setOnClickListener(new BtnIncluirPrefixoOnClickListener(getSherlockActivity(), view));
		
		return view;
		
	}

}
