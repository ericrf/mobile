package com.br.discadorbr.fragments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.br.discador.R;
import com.br.discadorbr.adapter.CallLogView;
import com.br.discadorbr.model.CallLogSingle;

public class RegistrosFragment extends SherlockFragment {
	
	private List<CallLogSingle> callLogList = new ArrayList<CallLogSingle>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_registros, container, false);
		//TextView CallLogView = (TextView) rootView.findViewById(R.id.callLog);
		//CallLogView.setText(getCallDetails());
		getCallLog();
		
		 ListView contactListView = (ListView) rootView.findViewById(R.id.listView1);

		 CallLogView callLogAdapter = new CallLogView(getSherlockActivity(),
				 callLogList);
		 
		 contactListView.setAdapter(callLogAdapter);

		
		return rootView;
	}

	private void getCallLog() {

        Cursor managedCursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        while (managedCursor.moveToNext()) {
        	
        	
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
            case CallLog.Calls.OUTGOING_TYPE:
                dir = "OUTGOING";
                break;

            case CallLog.Calls.INCOMING_TYPE:
                dir = "INCOMING";
                break;

            case CallLog.Calls.MISSED_TYPE:
                dir = "MISSED";
                break;
            }
            
            CallLogSingle callLogSingle = new CallLogSingle(dir, phNumber, callDayTime.toString(), callDuration);
            callLogList.add(callLogSingle);
        }
        managedCursor.close();

    }
	
	

}