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
    private static LayoutInflater inflater=null; 
 
    public ContactView(Activity a, List<Contact> contactList) {
        activity = a;
        list = contactList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.contact_list, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        
        Contact contato = list.get(position);
 
        // Setting all values in listview
        title.setText(contato.name);
        artist.setText(contato.name);
        
        if (contato.thumbnail != null) {
        	thumb_image.setImageURI(Uri.parse(contato.thumbnail));
        }
        else {
        	thumb_image.setImageResource(R.drawable.contact_icon);
        }
        return vi;
    }
}

