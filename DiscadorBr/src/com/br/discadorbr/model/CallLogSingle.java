package com.br.discadorbr.model;

public class CallLogSingle {
	public String callType;
	public String number;
	public String date;
	public String duration;

	public CallLogSingle(String callType, String number, String date, String duration) {
		this.callType = callType;
		this.number = number;
		this.date = date;
		this.duration = duration;
	}

}
