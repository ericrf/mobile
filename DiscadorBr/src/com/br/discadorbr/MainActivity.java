package com.br.discadorbr;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.br.discador.R;
import com.br.discadorbr.adapter.TabsPagerAdapter;

public class MainActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	// Tab titles
	private String[] tabs = { "Discador", "Contatos" };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Search")
				.setActionView(R.layout.collapsible_edittext)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_ALWAYS
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// setTheme(SampleList.THEME); //Used for theme switching in samples
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
				// on changing the page
				// make respected tab selected
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
		// mSelected.setText("Selected: " + tab.getText());
		viewPager.setCurrentItem(tab.getPosition());
		int position = tab.getPosition();
		if(position == 2){
			startActionMode(new ContactsActionMode());
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
		
	}
	
	
	
    public final class ContactsActionMode implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            menu.add("Novo")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            String title = item.getTitle().toString();
            switch (title) {
			case "Novo":
				Intent intent = new Intent(Intent.ACTION_INSERT,
						ContactsContract.Contacts.CONTENT_URI);
				startActivity(intent);
				break;

			default:
				Toast.makeText(MainActivity.this, "Got click: " + item, Toast.LENGTH_SHORT).show();
				break;
			}
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    }

}
