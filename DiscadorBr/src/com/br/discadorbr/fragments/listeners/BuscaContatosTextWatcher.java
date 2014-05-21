package com.br.discadorbr.fragments.listeners;

import java.util.List;

import com.br.discador.R;
import com.br.discadorbr.adapter.ContactAdapter;
import com.br.discadorbr.dao.ContactDao;
import com.br.discadorbr.model.Contact;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;

public class BuscaContatosTextWatcher implements TextWatcher {

	private Activity activity;
	private View rootView;

	public BuscaContatosTextWatcher(Activity activity, View rootView) {
		this.activity = activity;
		this.rootView = rootView;
	
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		List<Contact> contacts = ContactDao.getInstance(activity).getContacts();
		ListView list = (ListView) rootView.findViewById(R.id.listView1);
		
		ContactAdapter adapter = new ContactAdapter(activity, contacts);

		list.setAdapter(adapter);
		list.setOnItemClickListener(new RealizarChamadaOnItemClickListener(
				activity, contacts));
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

}
