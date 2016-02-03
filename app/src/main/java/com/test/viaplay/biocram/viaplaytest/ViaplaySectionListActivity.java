package com.test.viaplay.biocram.viaplaytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.test.viaplay.biocram.viaplaytest.data.DataManager;
import com.test.viaplay.biocram.viaplaytest.data.model.SectionModel;
import com.test.viaplay.biocram.viaplaytest.ui.SectionAdapter;

import java.util.List;

/**
 * An activity representing a list of ViaplaySections. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ViaplaySectionDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ViaplaySectionListActivity extends AppCompatActivity implements
        SectionAdapter.OnSectionClickListener, DataManager.OnAllSectionsReadyListener {

    private static final String TAG = ViaplaySectionListActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private RecyclerView mListView;
    private SectionAdapter mSectionAdapter;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaplaysection_list);

        mHandler = new Handler();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mListView = (RecyclerView) findViewById(R.id.viaplaysection_list);

        if (findViewById(R.id.viaplaysection_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DataManager.getInstance().getAllSections(getApplicationContext(),
                        ViaplaySectionListActivity.this);
            }
        };

        Thread t = new Thread(runnable);
        t.start();

    }

    @Override
    public void onSectionClicked(final int pos) {

        Log.d(TAG, "Clicked on section: " + pos);

        if (mTwoPane) {

            Fragment fragment = ViaplaySectionDetailFragment.getFragment(pos);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viaplaysection_detail_container, fragment)
                    .commit();
        } else {

            Intent intent = ViaplaySectionDetailActivity.getIntent(this, pos);
            startActivity(intent);
        }
    }

    @Override
    public void onAllSectionsReady(final List<SectionModel> sections) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSectionAdapter = new SectionAdapter(sections, ViaplaySectionListActivity.this);
                mListView.setAdapter(mSectionAdapter);
            }
        });
    }

}
