package com.sy.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import net.youmi.android.AdManager;
//import net.youmi.android.AdView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

public class LuckyClickActivity extends Activity {
    /** Called when the activity is first created. */
	int[] listImg = new int[7];
	ViewFlipper viewFlipper;
	LuckyMainView luckyView;
	TodayFortuneView todayFortuneView;
	Bitmap mainBackground;
	
	ImageButton startButton;
	boolean isOpenSound = false;//是否打开声音
	static {
		//				应用Id				应用密码			      广告请求间隔(s)   测试模式      
//		AdManager.init("537ef88653a2993c", "b9e10bcfe994a9fb", 30, 			true);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        
        ListView listView = (ListView)this.findViewById(R.id.listView);
        initImg();
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.listlayout, 
        		new String[]{"img"}, new int[]{R.id.img});
//        setListAdapter(sa);
        listView.setAdapter(adapter); 
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//方法一
//				Intent intent2 = new Intent();
//				intent2.setClass(LuckyClickActivity.this, LuckyGameActivity.class);
//				intent2.putExtra("viewId", position);
//				startActivity(intent2);
				//方法二
				Intent intent = new Intent(LuckyClickActivity.this, LuckyGameActivity.class);
				Bundle bundle = new Bundle();
//				bundle.putInt("viewId", position);
				bundle.putInt("viewId", position);
				intent.putExtras(bundle);
				startActivity(intent);
//				finish();
				int version = Integer.valueOf(android.os.Build.VERSION.SDK);
				if (version >= 5){
					overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
				}
//				if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.DONUT) {   
//					  overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);   
//				}
				
			}
		});
        
    }
    
//    @Override
//	public void onBackPressed() {
//    	showPreviousView();
//	}
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	public void initViewByid(final int id){
    	showNextView();
//    	this.myHandler.sendEmptyMessage(id);
    	viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				//根据id的不同分别初始化相应界面
//				initGameViewById(id);
//				luckyView.setBackgroundColor(Color.BLUE);
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
//				luckyView.setBackgroundColor(Color.RED);
//				initGameViewById(id);
			}
		});
    }
    
   
    public void initTodayFortune(){
//    	todayFortuneView = new TodayFortuneView(this, this);
    	this.setContentView(todayFortuneView);
    	
    	//有米
//    	AdView adView = new AdView(this,Color.GRAY, Color.WHITE, 100);
        //使用setPadding设置广告条的位置
//        adView.setPadding(0, 52, 0, 0);
     
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
//        addContentView(adView, params); 
    }
	
	public void showNextView() {
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.slide_right_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.slide_left_out));
		viewFlipper.showNext();
		
	}
	
	public void showPreviousView() {
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.slide_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.slide_right_out));
		viewFlipper.showPrevious();
	}
    
    public void initImg(){
    	listImg[0] = R.drawable.todayfortune;
    	listImg[1] = R.drawable.shiye;
    	listImg[2] = R.drawable.aiqing;
    	listImg[3] = R.drawable.caipiao;
    	listImg[4] = R.drawable.mianshi;
    	listImg[5] = R.drawable.xiangqin;
    	listImg[6] = R.drawable.majiang;
    }
    
    public List<Map<String, Object>> getData(){
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	for (int i=0; i<listImg.length; i++){
    		Map<String, Object> map = new HashMap<String, Object>();
        	map.put("img", listImg[i]);
//        	map.put("img", R.drawable.title);
        	list.add(map);
    	}
    	
    	return list;
    }
    
    //获得屏幕的宽
    public int getScreenWidth(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
    //获得屏幕的高
	public int getScreenHeight(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}