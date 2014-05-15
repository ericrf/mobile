package com.br.discadorbr.dao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.ContactsContract;

import com.br.discadorbr.model.Contact;

public class ContactDao {
	private Activity activity;
	private ContentResolver cr;
	private List<Contact> contactList = new ArrayList<Contact>();

	public ContactDao(Activity activity) {
		this.activity = activity;
	}
	
	public List<Contact> getContacts() {
		cr = activity.getContentResolver();

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
		return contactList;
	}
	
	private void setContactList(Cursor cur) {

		Contact contact = obtemContact(cur);
		if (verificaSeCursorPossuiNumeros(cur)) {

			// criando consulta dos números de telefone de um determinado
			// contato
			Cursor pCur = buscarNumerosDoContato(cur);

			// iterando numeros de telefone e adicionando a lista de numeros do contato
			while (pCur.moveToNext()) {
				contact.numbers.add(obtemNumber(pCur));
			}
			// add contato a lista
			contactList.add(contact);
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
