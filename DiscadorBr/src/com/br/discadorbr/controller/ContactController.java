package com.br.discadorbr.controller;

import java.util.List;

import android.app.Activity;

import com.br.discadorbr.dao.ContactDao;
import com.br.discadorbr.model.Contact;

public class ContactController {

	public static List<Contact> getContacts(Activity activity) {
		ContactDao contactDao = new ContactDao(activity);
		List<Contact> contacts = contactDao.getContacts();
		return contacts;
	}

}
