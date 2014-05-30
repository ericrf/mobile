package com.br.discadorbr.fragments.listeners;

import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;

import com.br.discadorbr.MainActivity;
import com.br.discadorbr.adapter.ContactAdapter;
import com.br.discadorbr.dao.ContactDao;
import com.br.discadorbr.fragments.ContatosFragment;
import com.br.discadorbr.model.Contact;

public class BuscaContatosTextWatcher implements TextWatcher {

	private MainActivity activity;

	public BuscaContatosTextWatcher(MainActivity activity) {
		this.activity = activity;
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		// passei os metodos pra ca por que gasta menos recursos
		List<Contact> contacts = ContactDao.getInstance(activity).findContactsByName(s.toString());
		ContactAdapter adapter = new ContactAdapter(activity, contacts);

		ContatosFragment.list.setAdapter(adapter);

		ContatosFragment.list.setOnItemClickListener(new RealizarChamadaOnItemClickListener(
				activity, contacts));
	}

}
