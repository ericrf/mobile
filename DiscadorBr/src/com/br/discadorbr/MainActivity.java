package com.br.discadorbr;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.br.discador.R;
import com.br.discadorbr.adapter.TabsPagerAdapter;

public class MainActivity extends SherlockFragmentActivity implements ActionBar.TabListener {
    private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	// Tab titles
		private String[] tabs = { "Discador", "Registros", "Contatos" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
      //  setTheme(SampleList.THEME); //Used for theme switching in samples
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar actionBar = getSupportActionBar();
        


     /*    ActionBar.Tab tabDiscador = getSupportActionBar().newTab();
            tabDiscador.setText("Discar");
            tabDiscador.setTabListener(this);
            getSupportActionBar().addTab(tabDiscador);
  
            ActionBar.Tab tabRegistros = getSupportActionBar().newTab();
            tabRegistros.setText("Registros");
            tabRegistros.setTabListener(this);
            getSupportActionBar().addTab(tabRegistros);
            
            ActionBar.Tab tabContatos = getSupportActionBar().newTab();
            tabContatos.setText("Contatos");
            tabContatos.setTabListener(this);
            getSupportActionBar().addTab(tabContatos);
            
            //set individual tab color and background image
          //  final View firstCustomView = new View(this);
           // firstCustomView.setBackgroundColor(Color.BLUE); 
          //  firstCustomView.setBackgroundResource(drawable.btn_star_big_on);
        //    tabDiscador.setCustomView(firstCustomView);  */
        
        
        
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
    //    mSelected.setText("Selected: " + tab.getText());
    	viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction transaction) {
    }
	
    
}
