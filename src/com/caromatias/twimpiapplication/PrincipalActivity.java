package com.caromatias.twimpiapplication;


import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.os.Build;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;

public class PrincipalActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		try {
			Resources res = getResources();
			TabHost tabHost = getTabHost();
			TabHost.TabSpec spec;
			Intent intent;
			tabHost.clearAllTabs();

			// create an intent for the tab which points at the class file for
			// that tab
			intent = new Intent().setClass(this, TabUnoActivity.class);

			// give the tab a name and set the icon for the tab
			spec = tabHost.newTabSpec("tab1")
					.setIndicator("", res.getDrawable(R.drawable.ic_tab_1))
					.setContent(intent);
			tabHost.addTab(spec);

			intent = new Intent().setClass(this, TabDosActivity.class);
			spec = tabHost.newTabSpec("tab2")
					.setIndicator("", res.getDrawable(R.drawable.ic_tab_2))
					.setContent(intent);
			tabHost.addTab(spec);

			intent = new Intent().setClass(this, TabTresActivity.class);
			spec = tabHost.newTabSpec("tab3")
					.setIndicator("", res.getDrawable(R.drawable.ic_tab_3))
					.setContent(intent);
			tabHost.addTab(spec);

			tabHost.setCurrentTab(0);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_principal,
					container, false);
			return rootView;
		}
	}

}
