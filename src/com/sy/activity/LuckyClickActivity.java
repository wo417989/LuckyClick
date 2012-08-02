package com.sy.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.domob.android.ads.DomobAdListener;
import cn.domob.android.ads.DomobAdView;

import net.youmi.android.AdManager;
import net.youmi.android.AdView;

//import net.youmi.android.AdManager;
//import net.youmi.android.AdView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
	boolean isOpenSound = false;//�Ƿ������
//	static {
//		//				Ӧ��Id				Ӧ������			      ���������(s)   ����ģʽ      
//		AdManager.init("537ef88653a2993c", "b9e10bcfe994a9fb", 30, 			false);
//	}
	
	ListView listView;
	LinearLayout ll_adView;//AD layout
	public static final String PUBLISHER_ID = "56OJyM1ouMGoaSnvCK";
	DomobAdView mAdview320x50;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
     // Ӧ��Id Ӧ������ ���������(s) ����ģʽ
		AdManager.init(this,"537ef88653a2993c", "b9e10bcfe994a9fb", 30, false);
		
		ll_adView = (LinearLayout)this.findViewById(R.id.ad_view_1);
		listView = (ListView)this.findViewById(R.id.listView);
        initImg();
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.listlayout, 
        		new String[]{"img"}, new int[]{R.id.img});
//        setListAdapter(sa);
        listView.setAdapter(adapter); 
        
		
        AdView adView = new AdView(this); 
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);		
		ll_adView.addView(adView, params); 
		
//		initDomobView();
		
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//����һ
//				Intent intent2 = new Intent();
//				intent2.setClass(LuckyClickActivity.this, LuckyGameActivity.class);
//				intent2.putExtra("viewId", position);
//				startActivity(intent2);
				//������
				Intent intent = new Intent(LuckyClickActivity.this, LuckyGameActivity.class);
				Bundle bundle = new Bundle();
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
    
    private void initDomobView(){
		//����һ��320x50�Ĺ��View
		mAdview320x50 = new DomobAdView(this, PUBLISHER_ID, DomobAdView.INLINE_SIZE_300X250);
		mAdview320x50.setKeyword("game");
		mAdview320x50.setUserGender("male");
		mAdview320x50.setUserBirthdayStr("2000-08-08");
		mAdview320x50.setUserPostcode("123456");
		
		//���ù��view�ļ�������
		mAdview320x50.setOnAdListener(new DomobAdListener() {
			@Override
			public void onReceivedFreshAd(DomobAdView adview) {
				Log.i("DomobSDKDemo", "onReceivedFreshAd");
			}
			@Override
			public void onFailedToReceiveFreshAd(DomobAdView adview) {
				Log.i("DomobSDKDemo", "onFailedToReceiveFreshAd");
			}
			@Override
			public void onLandingPageOpening() {
				Log.i("DomobSDKDemo", "onLandingPageOpening");
			}
			@Override
			public void onLandingPageClose() {
				Log.i("DomobSDKDemo", "onLandingPageClose");
			}
		});
		//�����View���ӵ���ͼ�С�
		RelativeLayout rl = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		rl.addView(mAdview320x50, lp);
		listView.addFooterView(rl);
//		ll_adView.addView(mAdview320x50);
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
				//����id�Ĳ�ͬ�ֱ��ʼ����Ӧ����
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
    	
    	//����
//    	AdView adView = new AdView(this,Color.GRAY, Color.WHITE, 100);
        //ʹ��setPadding���ù������λ��
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
    
    //�����Ļ�Ŀ�
    public int getScreenWidth(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
    //�����Ļ�ĸ�
	public int getScreenHeight(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}