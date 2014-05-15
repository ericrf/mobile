package com.br.discadorbr.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.discador.R;
import com.br.discadorbr.model.Prefixo;

public class PrefixoAdapter extends BaseAdapter {

	private List<Prefixo> prefixos;
	private LayoutInflater inflater;
	
	public PrefixoAdapter(Activity activity, List<Prefixo> prefixos) {
		this.prefixos = prefixos;
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return prefixos.size();
	}

	@Override
	public Object getItem(int position) {
		return prefixos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return prefixos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
        if(convertView==null){
            vi = inflater.inflate(R.layout.adapter_prefixo, null);
        }
        Prefixo prefixo = (Prefixo) getItem(position);
        TextView txtLabel = (TextView)vi.findViewById(R.id.prefixo_label);
        TextView txtNumber = (TextView)vi.findViewById(R.id.prefixo_number);
                
        txtLabel.setText(prefixo.getLabel());
        txtNumber.setText(String.valueOf(prefixo.getNumero()));        
        
		return vi;
	}

}
