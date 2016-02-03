package com.test.viaplay.biocram.viaplaytest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viaplay.biocram.viaplaytest.data.DataManager;
import com.test.viaplay.biocram.viaplaytest.data.model.SectionDetailModel;

/**
 * A fragment representing a single RawViaplaySectionModel detail screen.
 * This fragment is either contained in a {@link ViaplaySectionListActivity}
 * in two-pane mode (on tablets) or a {@link ViaplaySectionDetailActivity}
 * on handsets.
 */
public class ViaplaySectionDetailFragment extends Fragment implements DataManager.OnSectionDetailReadyListener {

    public static final String KEY_ITEM_POS = "key_item_pos";

    private TextView mTextViewTitle;
    private TextView mTextViewDescription;

    private Handler mHandler;

    public static ViaplaySectionDetailFragment getFragment(int pos) {
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_ITEM_POS, pos);
        ViaplaySectionDetailFragment fragment = new ViaplaySectionDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();

        if (getArguments().containsKey(KEY_ITEM_POS)) {

            final int pos = getArguments().getInt(KEY_ITEM_POS);

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    DataManager.getInstance().getSpecificSection(getActivity(), pos, ViaplaySectionDetailFragment.this);
                }
            };

            Thread t = new Thread(runnable);
            t.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viaplaysection_detail, container, false);

        mTextViewTitle = ((TextView) rootView.findViewById(R.id.textview_title));
        mTextViewDescription = ((TextView) rootView.findViewById(R.id.textview_description));

        return rootView;
    }

    @Override
    public void onSectionDetailReady(final SectionDetailModel sectionDetail) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextViewTitle.setText(sectionDetail.getTitle());
                mTextViewDescription.setText(sectionDetail.getDescription());
            }
        });
    }
}
