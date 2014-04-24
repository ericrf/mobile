package com.br.discadorbr.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.discador.R;
import com.br.discadorbr.model.CallLogSingle;
 
public class CallLogView extends BaseAdapter {
    private Activity activity;
    private List<CallLogSingle> callLogList;
    private static LayoutInflater inflater=null; 
 
    public CallLogView(Activity a, List<CallLogSingle> callLogList) {
        activity = a;
        this.callLogList = callLogList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public int getCount() {
        return callLogList.size();
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
            vi = inflater.inflate(R.layout.call_log, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title);
        TextView date = (TextView)vi.findViewById(R.id.date);
        TextView type = (TextView)vi.findViewById(R.id.type); 
        TextView duration = (TextView)vi.findViewById(R.id.duration);
        ImageView photo=(ImageView)vi.findViewById(R.id.list_image);
 
        
        CallLogSingle callLogSingle = callLogList.get(position);
 
        // Setting all values in listview
        title.setText(callLogSingle.number);
        date.setText(callLogSingle.date);
        type.setText(callLogSingle.callType);
        duration.setText(callLogSingle.duration);
        
        photo.setImageResource(R.drawable.contact_icon);

        return vi;
    }
}
