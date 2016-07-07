package com.wedcel.dragexpandgrid.other;

import android.content.Context;

public class CommUtil {

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}


}
