package com.br.discadorbr.adapter;

import java.util.List;

import com.br.discador.R;
import com.br.discadorbr.model.Contact;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactView extends BaseAdapter {

	private Activity activity;
	private List<Contact> list;
	private static LayoutInflater inflater = null;

	public ContactView(Activity a, List<Contact> contactList) {
		activity = a;
		list = contactList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) 
			view = inflater.inflate(R.layout.contact_list, null);
		
		TextView contactTextView = (TextView) view.findViewById(R.id.title);
		TextView numberTextView = (TextView) view.findViewById(R.id.artist); 
		ImageView thumbImageView = (ImageView) view.findViewById(R.id.list_image);

		Contact contact = list.get(position);

		// Setting all values in listview
		contactTextView.setText(contact.name);
		numberTextView.setText(contact.number);

		if (contact.thumbnail != null) {
			thumbImageView.setImageURI(Uri.parse(contact.thumbnail));
		} else {
			thumbImageView.setImageResource(R.drawable.contact_icon);
		}
		return view;
	}
}
