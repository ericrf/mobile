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
import android.provider.ContactsContract.CommonDataKinds.Phone;
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
	private List<String> contatos = new ArrayList<String>();
	private View rootView;
	public List<Contact> contactList = new ArrayList<Contact>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_contatos, container,
				false);
		getContatos();

		/*
		 * for (Contact contato : contactList) { ImageView thumbnailView = new
		 * ImageView(getActivity()); TextView nameView = new
		 * TextView(getActivity()); LinearLayout.LayoutParams vp = new
		 * LinearLayout.LayoutParams(20, 20); thumbnailView.setLayoutParams(vp);
		 * if (contato.thumbnail != null) {
		 * thumbnailView.setImageURI(Uri.parse(contato.thumbnail));
		 * 
		 * } else { thumbnailView.setImageResource(R.drawable.lion); }
		 * nameView.setText(contato.name); LinearLayout linearLayout =
		 * (LinearLayout) rootView.findViewById(R.id.ContactList);
		 * linearLayout.addView(thumbnailView); linearLayout.addView(nameView);
		 * }
		 */

		ListView list = (ListView) rootView.findViewById(R.id.listView1);
		Button addBtt = (Button) rootView.findViewById(R.id.addBtt);
		addBtt.setOnClickListener(addContact);

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

		// ListView contactListView = (ListView)
		// rootView.findViewById(R.id.listView1);

		// ArrayAdapter<ContactView> contatosList = new
		// ArrayAdapter<ContactView>(getSherlockActivity(),
		// android.R.layout.simple_list_item_1, contactViewList);

		/*
		 * ArrayAdapter<String> contatosList = new
		 * ArrayAdapter<String>(getSherlockActivity(),
		 * android.R.layout.simple_list_item_1, contatos);
		 */
		// contactList.setAdapter(contatosList);
		// l.setOnItemClickListener(this);

		return rootView;
	}
	
	public void callContact(String contactNumber) {
		startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: 9090"
				+ contactNumber)));
	}

	// TODO: ficar rico. lol
	public void getContatos() {

		//acesso aos dados 
		ContentResolver cr = getActivity().getContentResolver();
		
		//obtendo contatos do aparelho 
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);
		
		//verificando se resultado da consulta maior que 0
		if (cur.getCount() > 0) {
			//iterando resultados
			while (cur.moveToNext()) {
				
				//obtendo identificação, nome e foto
				String photo = cur
						.getString(cur
								.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));

				String id = cur.getString(cur.getColumnIndex(BaseColumns._ID));
				String name = cur
						.getString(cur
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				//verificando se possui numero de telefone 
				if (Integer
						.parseInt(cur.getString(cur
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					
					//criando consulta dos números de telefone de um determinado contato 
					Cursor pCur = cr.query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = ?", new String[] { id }, null);
					//iterando numeros de telefone 
					while (pCur.moveToNext()) {
						//obtendo tipo e numero do telefone 
						int phoneType = pCur
								.getInt(pCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
						String phoneNumber = pCur
								.getString(pCur
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						
						//verificando tipo de contato para inserção de um novo dado à lista contatos
						switch (phoneType) {
						case Phone.TYPE_MOBILE:
							contatos.add(name + "(mobile number)" + phoneNumber
									+ "photo: " + photo);
							break;
						case Phone.TYPE_HOME:
							contatos.add(name + "(home number)" + phoneNumber
									+ "photo: " + photo);
							break;
						case Phone.TYPE_WORK:
							contatos.add(name + "(work number)" + phoneNumber
									+ "photo: " + photo);
							break;
						case Phone.TYPE_OTHER:
							contatos.add(name + "(other number)" + phoneNumber
									+ "photo: " + photo);
							break;
						default:
							break;
						}
						
						//criando novo objeto contact do discadorbr
						Contact contact = new Contact(id, photo, name,
								phoneNumber);
						
						//adicionando contatos a contactList 
						contactList.add(contact);

					}
					pCur.close();
				}
			}
		}

	}

	/*
	 * public void getContatos() {
	 * 
	 * ContentResolver cr = getActivity().getContentResolver(); Cursor cur =
	 * cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	 * if (cur.getCount() > 0) { while (cur.moveToNext()) { String photo =
	 * cur.getString
	 * (cur.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
	 * 
	 * ImageView thumbnail = (ImageView)
	 * rootView.findViewById(R.id.contactImage); if (photo != null) {
	 * thumbnail.setImageURI(Uri.parse(photo)); }
	 * 
	 * 
	 * String id = cur.getString(cur.getColumnIndex(BaseColumns._ID)); String
	 * name =
	 * cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME
	 * )); if
	 * (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract
	 * .Contacts.HAS_PHONE_NUMBER))) > 0) { Cursor pCur =
	 * cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
	 * ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new
	 * String[]{id}, null); while (pCur.moveToNext()) { int phoneType =
	 * pCur.getInt
	 * (pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
	 * String phoneNumber =
	 * pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds
	 * .Phone.NUMBER)); switch (phoneType) { case Phone.TYPE_MOBILE:
	 * contatos.add(name + "(mobile number)"+ phoneNumber + "photo: "+photo);
	 * break; case Phone.TYPE_HOME: contatos.add(name + "(home number)"+
	 * phoneNumber + "photo: "+photo); break; case Phone.TYPE_WORK:
	 * contatos.add(name + "(work number)"+ phoneNumber + "photo: "+photo);
	 * break; case Phone.TYPE_OTHER: contatos.add(name + "(other number)"+
	 * phoneNumber + "photo: "+photo); break; default: break; } } pCur.close();
	 * } } }
	 * 
	 * }
	 */
	
	private OnClickListener addContact = new OnClickListener() {
		@Override
		public void onClick(View v) {
			 Intent intent = new Intent(Intent.ACTION_INSERT, 
                     ContactsContract.Contacts.CONTENT_URI);
			 startActivity(intent);
		}
	};

}