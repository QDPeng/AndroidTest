package com.lsp.test.rotate;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MatinalLight .
 * 转盘选项卡
 */
public class DialView extends ViewGroup {
    private static final String TAG = DialView.class.getSimpleName();

    private BaseAdapter adapter;
    private int height;
    private int width;
    private double rotationAngle;
    private float evDownX;
    private float evDownY;
    private boolean isFirstAdd = true;
    private int centrePointX;
    private int centrePointY;
    private OnDialViewItemClickListener onDialViewItemClickListener;
    private double startAngle;

    public DialView(Context context) {
        super(context);
    }

    public DialView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        adapter = baseAdapter;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //onMeasure执行了2次 导致addView多执行了一遍
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = width < height ? width : height;
        height = width;
        int count = adapter.getCount();
        if (isFirstAdd) {//防止onMeasure多次调用 重复添加child
            View child;
            for (int i = 0; i < count; i++) {
                final int position = i;
                child = adapter.getView(i, null, null);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                child.measure(childWidth, childHeight);
                addView(child);
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDialViewItemClickListener.onItemClickListenr(v, position);
                    }
                });
            }
            isFirstAdd = false;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        double radian = Math.toRadians(360 / count);
        View child;
        int childWidth;
        int childHeight;
        int radius;
        int childLeft;
        int childTop;
        int childRight;
        int childBottom;
        centrePointX = r - width / 2;
        centrePointY = height / 2;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();
            radius = (getMeasuredWidth() - childWidth) / 2;
            childLeft = (int) (width - (width + childWidth) / 2 + radius * Math.sin(i * radian + rotationAngle));//相对父View的坐标，不是手机屏幕的坐标
            childTop = (int) (height - (height + childHeight) / 2 - radius * Math.cos(i * radian + rotationAngle));
            childRight = childLeft + childWidth;
            childBottom = childTop + childHeight;
            child.layout(childLeft, childTop, childRight, childBottom);
            child.setRotation((float) (i * (360 / count) + Math.toDegrees(rotationAngle) + 180));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        double startTime = System.currentTimeMillis();
        double latestAngle;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                evDownX = ev.getX();
                evDownY = ev.getY();
                startAngle = getAngle(evDownX, evDownY);
                break;
            case MotionEvent.ACTION_MOVE:
                float evMoveNewX = ev.getX();
                float evMoveNewY = ev.getY();
                latestAngle = getAngle(evMoveNewX, evMoveNewY);
                if (latestAngle - startAngle > 5) {
                    rotationAngle += Math.toRadians(latestAngle - startAngle);
                    startAngle = getAngle(evMoveNewX, evMoveNewY);
                    requestLayout();
                } else if (latestAngle - startAngle < -5) {
                    rotationAngle -= Math.toRadians(startAngle - latestAngle);
                    startAngle = getAngle(evMoveNewX, evMoveNewY);
                    requestLayout();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;//让DOWN事件返回ture
    }

    /**
     * 获取夹角 [0,180]∪[0,-180]
     */
    private double getAngle(float evX, float evY) {
        //圆心坐标centrePointX  centrePointY
        double legX = evX - centrePointX;
        double legY = evY - centrePointY;
        double hypotenuse = Math.hypot(legX, legY);
        double angle = Math.toDegrees(Math.asin(legX / hypotenuse));//与Y轴的夹角
        if (legY > 0) {//Y坐标为负，说明与初始角度大于90°
            angle = 180 - angle;
        }
        return angle;
    }

    public void setOnItemClickListener(OnDialViewItemClickListener onItemClickListener) {
        onDialViewItemClickListener = onItemClickListener;
    }

    public interface OnDialViewItemClickListener {
        void onItemClickListenr(View view, int position);
    }

}
