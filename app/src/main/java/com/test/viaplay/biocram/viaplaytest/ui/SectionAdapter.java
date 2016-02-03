package com.test.viaplay.biocram.viaplaytest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.viaplay.biocram.viaplaytest.R;
import com.test.viaplay.biocram.viaplaytest.data.model.SectionModel;
import com.test.viaplay.biocram.viaplaytest.utils.CommonUtil;

import java.util.List;

/**
 * Created by biocram on 02/02/16.
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    public interface OnSectionClickListener {
        void onSectionClicked(int pos);
    }

    private List<SectionModel> mSections;
    private OnSectionClickListener mListener;

    public SectionAdapter(List<SectionModel> sections, OnSectionClickListener listener) {
        mSections = sections;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viaplaysection_list_content, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.setTitle(mSections.get(position).getName());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onSectionClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return CommonUtil.isEmpty(mSections) ? 0 : mSections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private View mRootView;

        public ViewHolder(View itemView) {
            super(itemView);

            mRootView = itemView;
            mTextViewTitle = (TextView) itemView.findViewById(R.id.textview_title);
        }

        public void setTitle(String title) {
            mTextViewTitle.setText(title);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            mRootView.setOnClickListener(onClickListener);
        }
    }
}
