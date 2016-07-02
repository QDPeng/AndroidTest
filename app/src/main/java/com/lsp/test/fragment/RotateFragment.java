package com.lsp.test.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lsp.test.R;
import com.lsp.test.rotate.DialView;

/**
 * Created by Administrator on 2016/5/28.
 */
public class RotateFragment extends BaseFragment {
    private static final String TAG = RotateFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rotate, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        final DialView dialView = (DialView) view.findViewById(R.id.dialView);
        DialViewAdapter adapter = new DialViewAdapter(getActivity());
        dialView.setAdapter(adapter);
        dialView.setOnItemClickListener(new DialView.OnDialViewItemClickListener() {
            @Override
            public void onItemClickListenr(View view, int position) {
                Toast.makeText(getActivity(), view.getClass().getSimpleName() + "===" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class DialViewAdapter extends BaseAdapter {
        private Context mContext;

        public DialViewAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(mContext);
            textView.setText("TextView" + position);
            textView.setBackgroundColor(Color.YELLOW);
            return textView;
        }
    }
}
