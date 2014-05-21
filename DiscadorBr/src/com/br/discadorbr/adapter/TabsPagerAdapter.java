package com.br.discadorbr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.br.discadorbr.fragments.ContatosFragment;
import com.br.discadorbr.fragments.DiscadorFragment;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new DiscadorFragment();
		case 1:
			return new ContatosFragment();
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

}