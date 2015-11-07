package com.lsp.test.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.CompoundButtonCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.gelitenight.waveview.library.WaveView;
import com.lsp.test.R;
import com.lsp.test.utils.LogUtil;
import com.lsp.test.utils.WaveHelper;

/**
 * Created by xian on 2015/11/7.
 */
public class WaveViewFragment extends BaseFragment {
    private WaveHelper mWaveHelper;

    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wave_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final WaveView waveView = (WaveView) view.findViewById(R.id.wave);
        waveView.setBorder(mBorderWidth, mBorderColor);

        mWaveHelper = new WaveHelper(waveView);

        ((RadioGroup) view.findViewById(R.id.shapeChoice))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.shapeCircle:
                                waveView.setShapeType(WaveView.ShapeType.CIRCLE);
                                break;
                            case R.id.shapeSquare:
                                waveView.setShapeType(WaveView.ShapeType.SQUARE);
                                break;
                            default:
                                break;
                        }
                    }
                });

        ((SeekBar) view.findViewById(R.id.seekBar))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        mBorderWidth = i;
                        waveView.setBorder(mBorderWidth, mBorderColor);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        CompoundButtonCompat.setButtonTintList(
                (RadioButton) view.findViewById(R.id.colorDefault),
                getResources().getColorStateList(android.R.color.white));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) view.findViewById(R.id.colorRed),
                getResources().getColorStateList(R.color.red));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) view.findViewById(R.id.colorGreen),
                getResources().getColorStateList(R.color.green));
        CompoundButtonCompat.setButtonTintList(
                (RadioButton) view.findViewById(R.id.colorBlue),
                getResources().getColorStateList(R.color.blue));

        ((RadioGroup) view.findViewById(R.id.colorChoice))
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.colorRed:
                                waveView.setWaveColor(
                                        Color.parseColor("#28f16d7a"),
                                        Color.parseColor("#3cf16d7a"));
                                mBorderColor = Color.parseColor("#44f16d7a");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            case R.id.colorGreen:
                                waveView.setWaveColor(
                                        Color.parseColor("#40b7d28d"),
                                        Color.parseColor("#80b7d28d"));
                                mBorderColor = Color.parseColor("#B0b7d28d");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            case R.id.colorBlue:
                                waveView.setWaveColor(
                                        Color.parseColor("#88b8f1ed"),
                                        Color.parseColor("#b8f1ed"));
                                mBorderColor = Color.parseColor("#b8f1ed");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                            default:
                                waveView.setWaveColor(
                                        WaveView.DEFAULT_BEHIND_WAVE_COLOR,
                                        WaveView.DEFAULT_FRONT_WAVE_COLOR);
                                mBorderColor = Color.parseColor("#44FFFFFF");
                                waveView.setBorder(mBorderWidth, mBorderColor);
                                break;
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        LogUtil.i("wave_view_fragment","onResume");
        mWaveHelper.start();
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtil.i("wave_view_fragment","onPause");
        mWaveHelper.cancel();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        LogUtil.i("wave_view_fragment","onDestroy");
        super.onDestroy();
    }
}
