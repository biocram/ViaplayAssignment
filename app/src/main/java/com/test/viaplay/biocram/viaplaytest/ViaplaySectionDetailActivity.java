package com.test.viaplay.biocram.viaplaytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * An activity representing a single RawViaplaySectionModel detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ViaplaySectionListActivity}.
 */
public class ViaplaySectionDetailActivity extends AppCompatActivity {

    private static final String KEY_POS = "key_pos";

    public static Intent getIntent(Context context, int pos) {

        Intent intent = new Intent(context, ViaplaySectionDetailActivity.class);
        intent.putExtra(KEY_POS, pos);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaplaysection_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            int pos = getIntent().getIntExtra(KEY_POS, 0);

            Fragment fragment = ViaplaySectionDetailFragment.getFragment(pos);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.viaplaysection_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ViaplaySectionListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
