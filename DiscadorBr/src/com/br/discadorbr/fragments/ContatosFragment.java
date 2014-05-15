package com.br.discadorbr.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.br.discador.R;
import com.br.discadorbr.adapter.ContactAdapter;
import com.br.discadorbr.controller.ContactController;
import com.br.discadorbr.model.Contact;

public class ContatosFragment extends SherlockFragment {
	private View rootView;
	public List<Contact> contactList = new ArrayList<Contact>();
	private AlertDialog contactNumberListAlert;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_contatos, container,
				false);
		contactList = ContactController.getContacts(getSherlockActivity());

		ListView list = (ListView) rootView.findViewById(R.id.listView1);

		// Getting adapter by passing xml data ArrayList
		ContactAdapter adapter = new ContactAdapter(getSherlockActivity(),
				contactList);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (contactList.get(position).numbers.size() == 1) {
					callContact(contactList.get(position).numbers.get(0));
				}
				else {
					showNumberListDialog(contactList.get(position));
				}

			}
		});

		return rootView;
	}

	public void callContact(String contactNumber) {
		startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: 9090"
				+ contactNumber)));
	}
	
	private void showNumberListDialog(Contact contato) {
		//Lista de itens 
		ArrayAdapter adapter = new ArrayAdapter(getSherlockActivity(), R.layout.adapter_contact_number, contato.numbers);
		AlertDialog.Builder builder = new AlertDialog.Builder(getSherlockActivity()); 
		builder.setTitle("Escolha um numero:");
		builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(getSherlockActivity(), "posição selecionada=" + arg1, Toast.LENGTH_SHORT).show();
				
				ListView alertDialogList = ((AlertDialog)contactNumberListAlert).getListView(); // get alert list
				String numberSelected = alertDialogList.getAdapter().getItem(arg1).toString(); // get alert list item
				callContact(numberSelected);
				contactNumberListAlert.dismiss(); // dont know where to put this, before or after call
				} 
			}); 
		contactNumberListAlert = builder.create(); 
		contactNumberListAlert.show();
		} 
}