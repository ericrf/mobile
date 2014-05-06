package com.br.discadorbr.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.br.discador.R;
import com.br.discadorbr.adapter.PrefixosListViewBaseAdapter;
import com.br.discadorbr.dao.PrefixoDao;
import com.br.discadorbr.fragments.listeners.IncluirPrefixoOnClickListener;
import com.br.discadorbr.model.Prefixo;

public class PrefixosFragment extends SherlockFragment {

	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_prefixos, container, false);
		
		Button btnIncluirPrefixo = (Button) view.findViewById(R.id.btnIncluirPrefixo);
		btnIncluirPrefixo.setOnClickListener(new IncluirPrefixoOnClickListener(getSherlockActivity(), view));
		
		List<Prefixo> prefixos = buscarTodos();
		
		ListView prefixosListView = (ListView) view.findViewById(R.id.prefixos);
		prefixosListView.setAdapter(new PrefixosListViewBaseAdapter(getSherlockActivity(), prefixos));
		
		return view;
		
	}
	
	private List<Prefixo> buscarTodos(){
		PrefixoDao dao = null; 
		List<Prefixo> prefixos =  null;
		try{
			dao = new PrefixoDao(getSherlockActivity());
			prefixos = dao.getAll();
		}catch(Exception e){
			//TODO: tratamento de exeption. 
		}finally{
			dao.close();
		}
		return prefixos == null ? new ArrayList<Prefixo>() : prefixos;
	}

}
