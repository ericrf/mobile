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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.br.discadorbr.fragments.listeners.BuscaContatosTextWatcher;
import com.br.discadorbr.fragments.listeners.RealizarChamadaOnItemClickListener;
import com.br.discadorbr.model.Contact;

public class MainActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {
	private ViewPager viewPager;
	public static TabsPagerAdapter mAdapter;
	// Tab titles
	private String[] tabs = { "Discador", "Contatos" };
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add("Search")
		.setIcon(R.drawable.abs__ic_search)
		.setActionView(R.layout.collapsible_edittext)
		.setShowAsAction(
				MenuItem.SHOW_AS_ACTION_ALWAYS
						| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW); // setActionView
		
		 // pega a edit text do item 0 da action bar
		 final EditText searchText = (EditText) menu.findItem(0).getActionView();
		 
		 // muda p/ contatosFragment no onFocus
		 searchText.setOnFocusChangeListener(new OnFocusChangeListener() {
			 @Override
			 public void onFocusChange(View v, boolean hasFocus) {
			     if(hasFocus){
			        viewPager.setCurrentItem(1);
			     }
			    }
			 });
		 
		 // auto explicativo
		 searchText.addTextChangedListener(new BuscaContatosTextWatcher(MainActivity.this));

		return true;
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
