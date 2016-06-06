package com.lsp.test.flowlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.ActionBar.LayoutParams;

import android.view.ViewGroup.MarginLayoutParams;

import com.lsp.test.R;
import com.lsp.test.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/6/6.
 */
public class FlowFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flow, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        FlowLayout layout = (FlowLayout) view.findViewById(R.id.flow_layout);

        for (int i = 0; i < 15; i++) {
            Button button = new Button(getActivity());
            button.setText("test" + i * 2);

            layout.addView(button, new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }
}
