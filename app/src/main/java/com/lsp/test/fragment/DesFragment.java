package com.lsp.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.lsp.test.R;
import com.lsp.test.utils.ActionUtil;
import com.lsp.test.utils.AlarmUtil;
import com.lsp.test.utils.LogUtil;

/**
 * Created by xian on 2015/10/12.
 */
public class DesFragment extends BaseFragment implements View.OnClickListener {
    CircleProgress circleProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_des, container, false);
        initListener(view);
        return view;
    }

    private void initListener(View view) {
        circleProgress = (CircleProgress) view.findViewById(R.id.circle_progress);
        view.findViewById(R.id.fragment_des_start_btn).setOnClickListener(this);
        view.findViewById(R.id.fragment_des_stop_btn).setOnClickListener(this);
        ((SeekBar) view.findViewById(R.id.seekBar))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        int progress = 50 * i / 50;
                        LogUtil.i("progress", progress + "");
                        circleProgress.setProgress(progress);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_des_start_btn:
                LogUtil.i("desFragment", "start");
                AlarmUtil.addAlarmBroadcast(getActivity(), 5 * 1000, ActionUtil.HEART_BEAT_BROADCAST_START);
                break;
            case R.id.fragment_des_stop_btn:
                LogUtil.i("desFragment", "stop");
                AlarmUtil.removeAlarmBroadcast(getActivity(), ActionUtil.HEART_BEAT_BROADCAST_STOP);
                break;
        }

    }
}
