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

	/** Singleton INSTANCE **/ 
	private static ContactDao INSTANCE;
	private Activity activity;
	private ContentResolver cr;
	private List<Contact> contactList = new ArrayList<Contact>();

	/**
	 * construtor privado 
	 * @param activity
	 */
	private ContactDao(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 
	 * @param activity
	 * @return
	 */
	public static ContactDao getInstance(Activity activity) {
		if (INSTANCE == null)
			INSTANCE = new ContactDao(activity);
		return INSTANCE;
	}

	/**
	 * getContacts
	 * 	busca todos os contatos na base de dados de contatos do android
	 * 	cria uma lista de contatos para ser adaptada a tela
	 * 
	 * @return lista de contatos do usuário 
	 */
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

	/**
	 * findContactsByName 
	 * 	Realiza uma consulta na base de contacts do próprio
	 * 	android utilizando a base: ContactsContract.Contacts.CONTENT_URI 
	 * 	usando o nome como filtro de pesquisa em cima do campo:
	 * 	ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
	 * 
	 * @param name
	 *            nome que será pesquisado
	 * @return Lista de Contatos
	 */
	public List<Contact> findContactsByName(String name) {
		cr = activity.getContentResolver();

		// obtendo contatos do aparelho
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
						+ " LIKE ? COLLATE NOCASE", new String[] { "%" + name
						+ "%" }, null);
		// verificando se resultado da consulta maior que 0
		if (cur.getCount() > 0) {
			contactList = new ArrayList<Contact>();
			// iterando resultados
			while (cur.moveToNext()) {
				setContactList(cur);
			}
		}
		return contactList;
	}

	/**
	 * setContactList
	 * 	cria a lista de contatos "this.contactList"
	 * @param cur
	 */
	private void setContactList(Cursor cur) {
		Contact contact = obtemContact(cur);
		if (verificaSeCursorPossuiNumeros(cur)) {
			Cursor pCur = buscarNumerosDoContato(cur);
			while (pCur.moveToNext()) {
				contact.numbers.add(obtemNumber(pCur));
			}
			contactList.add(contact);
			pCur.close();
		}
	}

	/**
	 * buscarNumerosDoContato
	 * 	realiza consulta na base buscando os números de um contato
	 * @param cur
	 * @return	cursor de lista de números.
	 */
	private Cursor buscarNumerosDoContato(Cursor cur) {
		return cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
						+ " = ?", new String[] { obtemId(cur) }, null);
	}

	/**
	 * verificaSeCursorPossuiNumeros
	 * 	verifica se cursor possui numeros de telefone associados a ele.
	 * @param cur
	 * @return
	 */
	private boolean verificaSeCursorPossuiNumeros(Cursor cur) {
		return (Integer.parseInt(cur.getString(cur
				.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0);
	}

	/**
	 * obtemId
	 * 	obtem o id de um cursor
	 * @param cur
	 * @return id obtido a partir de um cursor
	 */
	private String obtemId(Cursor cur) {
		return cur.getString(cur.getColumnIndex(BaseColumns._ID));
	}

	/**
	 * obtemName
	 * 	obtem o Nome de um cursor
	 * @param cur
	 * @return nome obtido a partir de um cursor
	 */
	private String obtemName(Cursor cur) {
		return cur.getString(cur
				.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	}

	/**
	 * obtemThumbnail
	 * 	obtem o thumbnail de um cursor
	 * @param cur
	 * @return thumbnail obtido a partir de um cursor
	 */
	private String obtemThumbnail(Cursor cur) {
		return cur.getString(cur
				.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
	}

	/**
	 * obtemNumber
	 * 	obtem o número de um cursor
	 * @param cur
	 * @return numero obtido a partir de um cursor
	 */
	private String obtemNumber(Cursor cur) {
		return cur.getString(cur
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	}

	/**
	 * obtemContact
	 * 	obtem um contato a partir de um cursor
	 * @param cur
	 * @return Contato criado a partir do cursor
	 */
	private Contact obtemContact(Cursor cur) {
		Contact contact = new Contact();
		contact.id = obtemId(cur);
		contact.name = obtemName(cur);
		contact.thumbnail = obtemThumbnail(cur);
		return contact;
	}

}
