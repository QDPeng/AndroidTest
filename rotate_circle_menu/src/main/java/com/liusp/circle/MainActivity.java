package com.liusp.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.liusp.circle.widget.CircleMenu;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MainActivity extends AppCompatActivity {
    CircleMenu circleMenu;
    ImageView circleBg;
    ImageView circleUpView;
    private ArrayList<ItemEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        circleBg = (ImageView) findViewById(R.id.circle_bg);
        circleUpView = (ImageView) findViewById(R.id.circle_up_view);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        if (circleMenu != null) {
            initDisplay();
            circleMenu.setRotating(true);//是否启用旋转
            circleMenu.setOnRotationFinishedListener(new CircleMenu.OnRotationFinishedListener() {
                @Override
                public void onRotationFinished(CircleMenu.ItemView view) {

                }
            });
            circleMenu.setCircleUpView(circleUpView);
            circleMenu.setCircleBg(circleBg);
            circleMenu.setChildAngles();
        }
        initData();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            ItemEntity entity = new ItemEntity();
            if (i <= 10) {
                entity.setDay(i % 10);
                entity.setName("发芽期");
            } else if (i <= 20) {
                entity.setDay((i - 10) % 10);
                entity.setName("育苗期");
            } else if (i <= 50) {
                entity.setDay((i - 20) % 30);
                entity.setName("开花期");
            } else if (i <= 80) {
                entity.setDay((i - 50) % 30);
                entity.setName("结果期");
            } else {
                entity.setDay((i - 80) % 20);
                entity.setName("成熟期");
            }
            list.add(entity);
        }
    }

    private void initDisplay() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        circleMenu.setLayoutParams(new RelativeLayout.LayoutParams(width, width));
        circleBg.setLayoutParams(new RelativeLayout.LayoutParams(width, width));
        circleUpView.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2 + 10));
    }
}
