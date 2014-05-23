package com.br.discadorbr.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.br.discador.R;
import com.br.discadorbr.adapter.ContactAdapter;
import com.br.discadorbr.dao.ContactDao;
import com.br.discadorbr.fragments.listeners.RealizarChamadaOnItemClickListener;
import com.br.discadorbr.model.Contact;

public class ContatosFragment extends SherlockFragment {
	private View rootView;
	public static ListView list;
	private List<Contact> contacts;
	public static ContactAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Activity activity = getSherlockActivity();
		rootView = inflater.inflate(R.layout.fragment_contatos, container,
				false);

		contacts = ContactDao.getInstance(activity).getContacts();
		list = (ListView) rootView.findViewById(R.id.listView1);

		adapter = new ContactAdapter(activity, contacts);

		list.setAdapter(adapter);
		list.setOnItemClickListener(new RealizarChamadaOnItemClickListener(
				activity, contacts));

		return rootView;
	}
}