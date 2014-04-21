package com.br.discadorbr.adapter;

import com.br.discadorbr.fragments.ContatosFragment;
import com.br.discadorbr.fragments.DiscadorFragment;
import com.br.discadorbr.fragments.RegistrosFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new DiscadorFragment();
		case 1:
			// Games fragment activity
			return new RegistrosFragment();
		case 2:
			// Movies fragment activity
			return new ContatosFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}