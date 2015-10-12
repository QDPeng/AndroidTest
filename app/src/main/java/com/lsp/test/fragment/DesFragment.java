package com.lsp.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsp.test.R;
import com.lsp.test.utils.ActionUtil;
import com.lsp.test.utils.AlarmUtil;
import com.lsp.test.utils.LogUtil;

/**
 * Created by xian on 2015/10/12.
 */
public class DesFragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_des, container, false);
        initListener(view);
        return view;
    }

    private void initListener(View view) {
        view.findViewById(R.id.fragment_des_start_btn).setOnClickListener(this);
        view.findViewById(R.id.fragment_des_stop_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_des_start_btn:
                LogUtil.i("desFragment","start");
                AlarmUtil.addAlarmBroadcast(getActivity(), 5 * 1000, ActionUtil.HEART_BEAT_BROADCAST_START);
                break;
            case R.id.fragment_des_stop_btn:
                LogUtil.i("desFragment","stop");
                AlarmUtil.removeAlarmBroadcast(getActivity(), ActionUtil.HEART_BEAT_BROADCAST_STOP);
                break;
        }

    }
}
