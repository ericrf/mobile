package com.br.discadorbr.model;


public class Contact {
	public String thumbnail;
	public String name;
	public String id;
	public String number;
	
	public Contact(String id, String thumbnail, String name, String number) {
		this.id = id;
		this.thumbnail = thumbnail;
		this.name = name;
		this.number = number;
	}
	
	public Contact() {
		
	}
	

}
