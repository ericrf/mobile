package com.br.discadorbr.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.br.discador.R;
import com.br.discadorbr.adapter.ContactView;
import com.br.discadorbr.model.Contact;

public class ContatosFragment extends SherlockFragment {
	private View rootView;
	private ContentResolver cr;
	public List<Contact> contactList = new ArrayList<Contact>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_contatos, container,
				false);
		getContatos();

		ListView list = (ListView) rootView.findViewById(R.id.listView1);
		Button addBtt = (Button) rootView.findViewById(R.id.addBtt);
		addBtt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_INSERT,
						ContactsContract.Contacts.CONTENT_URI);
				startActivity(intent);
			}
		});

		// Getting adapter by passing xml data ArrayList
		ContactView adapter = new ContactView(getSherlockActivity(),
				contactList);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String number = contactList.get(position).number;
				if (number != null) {
					callContact(number);
				}

			}
		});

		return rootView;
	}

	public void callContact(String contactNumber) {
		startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: 9090"
				+ contactNumber)));
	}

	// TODO: ficar rico. lol
	public void getContatos() {

		// acesso aos dados
		cr = getActivity().getContentResolver();

		// obtendo contatos do aparelho
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		// verificando se resultado da consulta maior que 0
		if (cur.getCount() > 0) {
			// iterando resultados
			while (cur.moveToNext()) {
				setContactList(cur);

			}
		}
	}

	private void setContactList(Cursor cur){
		
		Contact contact = obtemContact(cur);
		if (verificaSeCursorPossuiNumeros(cur)) {
			
			//criando consulta dos números de telefone de um determinado contato 
			Cursor pCur = buscarNumerosDoContato(cur);
			
			//iterando numeros de telefone e adicionando a lista que irá compor a tela. 
			while (pCur.moveToNext()) {			
				contact.number = obtemNumber(pCur);
				contactList.add(contact);
			}
			pCur.close();
		}
	}

	private Cursor buscarNumerosDoContato(Cursor cur) {
		return cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ " = ?", new String[] { obtemId(cur) }, null);
	}

	private boolean verificaSeCursorPossuiNumeros(Cursor cur) {
		return (Integer.parseInt(cur.getString(cur
				.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0);
	}

	private String obtemId(Cursor cur) {
		return cur.getString(cur.getColumnIndex(BaseColumns._ID));
	}

	private String obtemName(Cursor cur) {
		return cur.getString(cur
				.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	}

	private String obtemThumbnail(Cursor cur) {
		return cur.getString(cur
				.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
	}

	private String obtemNumber(Cursor cur) {
		return cur.getString(cur
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	}

	private Contact obtemContact(Cursor cur) {

		Contact contact = new Contact();
		contact.id = obtemId(cur);
		contact.name = obtemName(cur);
		contact.thumbnail = obtemThumbnail(cur);
		return contact;
	}

}