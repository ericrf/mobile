package com.br.discadorbr.fragments.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.br.discador.R;
import com.br.discadorbr.dao.PrefixoDao;
import com.br.discadorbr.model.Prefixo;

public class BtnIncluirPrefixoOnClickListener implements OnClickListener {

	private SherlockFragmentActivity activity;
	private View rootView;

	public BtnIncluirPrefixoOnClickListener(SherlockFragmentActivity activity, View rootView) {
		this.activity = activity;
		this.rootView = rootView;
	}

	@Override
	public void onClick(View v) {
		
		Prefixo prefixo = getPrefixoFromView(rootView);
		PrefixoDao dao = new PrefixoDao(activity);
		dao.add(prefixo);
		dao.close();
	
	}

	private Prefixo getPrefixoFromView(View v){
		TextView label = (TextView) v.findViewById(R.id.txtLabel);
		TextView numero = (TextView) v.findViewById(R.id.txtNumber);
		TextView descricao = (TextView) v.findViewById(R.id.txtDescricao);
		CheckBox isDefault = (CheckBox) v.findViewById(R.id.checkIsDefault);
		
		Prefixo prefixo = new Prefixo();
		prefixo.setLabel(label.getText().toString());
		prefixo.setNumero(Integer.valueOf(numero.getText().toString()));
		prefixo.setDescricao(descricao.getText().toString());
		prefixo.setDefault(isDefault.isChecked());

		return prefixo;
	}
}
