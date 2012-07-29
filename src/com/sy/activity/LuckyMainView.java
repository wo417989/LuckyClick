package com.sy.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class LuckyMainView extends View {

	LuckyClickActivity luckyActivity;
	//id 标明点的是哪一个
	public LuckyMainView(Context context,LuckyClickActivity activity) {
		super(context);
		this.setBackgroundColor(Color.GREEN);
		this.luckyActivity = activity;
	}
	
	
	public LuckyMainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
//		this.setBackgroundColor(R.drawable.background_game);
		this.setBackgroundResource(R.drawable.background_game);
//		luckyActivity = (LuckyClickActivity)context;
//		luckyActivity.myHandler.sendEmptyMessage(1);
		//初始化 根据不同id
		
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
	}
	

}
