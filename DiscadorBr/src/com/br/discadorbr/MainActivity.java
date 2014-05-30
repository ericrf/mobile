package com.br.discadorbr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.br.discador.R;
import com.br.discadorbr.adapter.TabsPagerAdapter;
import com.br.discadorbr.fragments.listeners.BuscaContatosTextWatcher;
import com.br.discadorbr.fragments.listeners.RealizadorDeChamadas;

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
				if (hasFocus) {
					viewPager.setCurrentItem(1);
				}
			}
		});

		// auto explicativo
		searchText.addTextChangedListener(new BuscaContatosTextWatcher(
				MainActivity.this));
		
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
	
	public void createNewPrefixoDefaultDialog(String num) {
		final EditText input = new EditText(getApplicationContext());
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		input.setTextColor(Color.BLACK);
		final String numero = num;
		
		// Show new folder name input dialog
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("Prefixo").setView(input)
				.setMessage("Escolha o prefixo a cobrar padrão da sua cidade. Aperte e segure este botão novamente para mudar.")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						String prefixoDefault = input.getText().toString();
						if (isValidDefaultPrefixo(prefixoDefault)) {
							saveDefaultPrefixo(prefixoDefault, numero);
						}
						else {
							Toast.makeText(MainActivity.this, "Prefixo invalido, insira outro", Toast.LENGTH_SHORT).show();
						}
					}
				}).setNegativeButton("Cancelar", null).show().getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);;
		
		
	}
	
	public void saveDefaultPrefixo(String prefixoDefault, String numero) {
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		Editor editor = pref.edit();
		editor.putString("prefixoSalvo", prefixoDefault);
		editor.commit();
		if (numero != null) {
			RealizadorDeChamadas.callContact(this, numero, getDefaultPrefixo());
		}
		
	}
	
	public String getDefaultPrefixo() {
		SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		String prefixoSalvo = pref.getString("prefixoSalvo", null);
		return prefixoSalvo;
	}
	
    private boolean isValidDefaultPrefixo(String prefixoDefault) {
    	prefixoDefault = prefixoDefault.replaceAll( "[^\\d]", "" ).trim();
    	return prefixoDefault.length() > 0 ? true : false;
    }

}
