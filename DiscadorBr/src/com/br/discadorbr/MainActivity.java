package com.br.discadorbr;

import java.util.List;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.br.discador.R;
import com.br.discadorbr.adapter.ContactAdapter;
import com.br.discadorbr.adapter.TabsPagerAdapter;
import com.br.discadorbr.dao.ContactDao;
import com.br.discadorbr.fragments.listeners.RealizarChamadaOnItemClickListener;
import com.br.discadorbr.model.Contact;

public class MainActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener, SearchView.OnQueryTextListener,
		SearchView.OnSuggestionListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	// Tab titles
	private String[] tabs = { "Discador", "Contatos" };
	
	private SuggestionsAdapter mSuggestionsAdapter;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Used to put dark icons on light action bar

		// Create the search view
		SearchView searchView = new SearchView(getSupportActionBar()
				.getThemedContext());
		searchView.setQueryHint("Buscar");
		searchView.setOnQueryTextListener(this);
		searchView.setOnSuggestionListener(this);

		//Sugestões de pesquisa
//		if (mSuggestionsAdapter == null) {
//			MatrixCursor cursor = new MatrixCursor(COLUMNS);
//			cursor.addRow(new String[] { "1", "'Murica" });
//			cursor.addRow(new String[] { "2", "Canada" });
//			cursor.addRow(new String[] { "3", "Denmark" });
//			mSuggestionsAdapter = new SuggestionsAdapter(getSupportActionBar()
//					.getThemedContext(), cursor);
//		}

		searchView.setSuggestionsAdapter(mSuggestionsAdapter);

		menu.add("Search")
				.setIcon(R.drawable.abs__ic_search)
				.setActionView(searchView)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		Toast.makeText(this, "You searched for: " + query, Toast.LENGTH_LONG)
				.show();
		viewPager.setCurrentItem(1);
		return true;
	}
	
	@Override
	public boolean onSuggestionSelect(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuggestionClick(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
		List<Contact> contacts = ContactDao.getInstance(this).findContactsByName(newText);
		ListView list = (ListView) rootView.findViewById(R.id.listView1);

		ContactAdapter adapter = new ContactAdapter(this, contacts);

		list.setAdapter(adapter);
		list.setOnItemClickListener(new RealizarChamadaOnItemClickListener(
				this, contacts));
		return false;
	}
	
	
	
	private class SuggestionsAdapter extends CursorAdapter {

        public SuggestionsAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv = (TextView) view;
            final int textIndex = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
            tv.setText(cursor.getString(textIndex));
        }
    }
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar actionBar = getSupportActionBar();

		viewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		viewPager.setOffscreenPageLimit(3);

		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				ActionBar actionBar = getSupportActionBar();
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction transaction) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction transaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction transaction) {

	}

	
}
