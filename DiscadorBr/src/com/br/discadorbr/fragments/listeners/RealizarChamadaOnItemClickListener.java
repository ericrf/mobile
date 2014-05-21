package com.br.discadorbr.fragments.listeners;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.discador.R;
import com.br.discadorbr.model.Contact;

public class RealizarChamadaOnItemClickListener implements OnItemClickListener {

	
	private Activity activity;
	private List<Contact> contacts;
	private AlertDialog contactNumberListAlert;
	
	public RealizarChamadaOnItemClickListener(Activity activity, List<Contact> contacts) {
		this.activity = activity;
		this.contacts = contacts;
	
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (contacts.get(position).numbers.size() == 1) {
			RealizadorDeChamadas.callContact(activity,
					contacts.get(position).numbers.get(0));
		} else {
			showNumberListDialog(contacts.get(position));
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void showNumberListDialog(Contact contato) {

		@SuppressWarnings("rawtypes")
		ArrayAdapter adapter = new ArrayAdapter(activity,
				R.layout.adapter_contact_number, contato.numbers);
		AlertDialog.Builder builder = new AlertDialog.Builder(
				activity);
		builder.setTitle("Escolha um numero:");
		builder.setSingleChoiceItems(adapter, 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						Toast.makeText(activity,
								"posição selecionada=" + arg1,
								Toast.LENGTH_SHORT).show();

						ListView alertDialogList = ((AlertDialog) contactNumberListAlert)
								.getListView(); 
						
						String numberSelected = alertDialogList.getAdapter()
								.getItem(arg1).toString(); 

						RealizadorDeChamadas.callContact(activity,
								numberSelected);
						
						contactNumberListAlert.dismiss(); 
					}
				});
		contactNumberListAlert = builder.create();
		contactNumberListAlert.show();
	}

	
}
