package com.sy.activity;

import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;

public class LuckyGameActivity extends Activity {

	int viewId;
	int[] listImg = new int[7];
	Button startBtn;
	Button soundBtn;
	Button settingsBtn;
	Button backBtn;
	View gameRunLayout;
	FrameLayout operation_layout;
	ImageView titleImg;
	RelativeLayout titleLayout;//����layout
	RelativeLayout toolsLayout;
	boolean isOpenSound = true;//�Ƿ������
	public int gameState = 0;//��Ϸ��״̬ 0,��û��ʼ   1,������Ϸ  2,��Ϸ���� 
	
	View gameView;
	public int tmpI = -1;
	public int tmpJ = -1;
	public Thread gamePlayThread;
	public Handler myHandler = new Handler();
	
	public Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			this.update();
			myHandler.postDelayed(this, 2000);//������ɿ�����
		}
		void update(){
			if (gameState == 1){
				tmpI = randomNumber(0, 6);
				tmpJ = randomNumber(0, 6);
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		AdManager.init(this,"537ef88653a2993c", "b9e10bcfe994a9fb", 30, false);
		
		
		initImg();//��ʼ��
//		TextView testText = (TextView)this.findViewById(R.id.testText);
		Intent intent = getIntent();
//		viewId = intent.getIntExtra("viewId", 0);//����һ ��ȡintent����
		Bundle bundle = intent.getExtras();
		viewId = bundle.getInt("viewId");
//		testText.setText(String.valueOf(viewId));
		
		//��̬���view�����ַ���
		//LayoutInflater inflater = getLayoutInflater();     
//		LayoutInflater inflater = LayoutInflater.from(this);      
//		View view=inflater.inflate(R.layout.ID, null);     
//		//���߸ɴಢ��һ�䣺     
//		View view=LayoutInflater.from(this).inflate(R.layout.ID, null);
		
		///////////////////////////////*************//////////////////////////////
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.gameview, null);
		
		gameRunLayout = inflater.inflate(R.layout.gamerunlayout, null);//include����
		//FrameLayout ����
		operation_layout = (FrameLayout)view.findViewById(R.id.operation_layout);
		
//		RelativeLayout rl1 = (RelativeLayout)view.findViewById(R.id.gameTitle);
//		initTitleView(viewId);//1
		//��ʼ��title
		titleLayout = (RelativeLayout)view.findViewById(R.id.gameTitle);
		titleImg = (ImageView)view.findViewById(R.id.titleImg);
		titleImg.setImageResource(listImg[viewId]);
		
		AdView adView = new AdView(this); 
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);		
		params.addRule(RelativeLayout.BELOW, titleImg.getId());
		titleLayout.addView(adView, params);
		
		//��ʼ���м��view
//		gameStartView = (View)view.findViewById(R.id.gameStartView);
		
//		
//		RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.mainGame);
//		rl.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
//		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);//��ǰlayout�Ŀ��
//		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, -1);
//		layoutParams.setMargins(100, 100, 100, 100);
//		
//		rl.addView(gameStartView, layoutParams);
		toolsLayout = (RelativeLayout)view.findViewById(R.id.toolsBar);
		
		initGameViewById(viewId);//2
		setContentView(view);
//		addContentView(iv, layoutParams);//Ҫ��setContentView֮����Ч
//		setContentView(R.layout.gameview);
		
		
		
		startBtn = (Button)view.findViewById(R.id.startImg);
		soundBtn = (Button)view.findViewById(R.id.soundImg);
		settingsBtn = (Button)view.findViewById(R.id.settingsImg);
		backBtn = (Button)view.findViewById(R.id.onBackImg);
		
		gamePlayThread = new Thread(new GameThread());
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (LuckyGameActivity.this.gameState == 0){
					startBtn.setVisibility(View.GONE);
					startGame();
					myHandler.postDelayed(runnable, 1000);//������ʱ��
				}
				
//				tmpI = randomNumber(0, 6);
//				tmpJ = randomNumber(0, 6);
			}
		});
		
		soundBtn.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				isOpenSound = !isOpenSound;
				if (isOpenSound){//open sound
					soundBtn.setBackgroundResource(R.drawable.btn_sound_on);
				}else {
					soundBtn.setBackgroundResource(R.drawable.btn_sound_off);
				}
			}
		});
		
		settingsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LuckyGameActivity.this.onBackPressed();
			}
		});
		
	}
	
	public void initGameView(int id){
//		ImageView iv = new ImageView(this);
//		iv.setImageResource(R.drawable.center_bg);
//		RelativeLayout rl = new RelativeLayout(this);
//		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);//��ǰlayout�Ŀ��
//		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, -1);
//		layoutParams.setMargins(100, 100, 100, 100);
		
		RelativeLayout rl = (RelativeLayout)gameRunLayout.findViewById(R.id.gameRunLayout);
		RelativeLayout.LayoutParams rLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
		rLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//		rLayoutParams.setMargins(100, 100, 100, 100);
//		View view = 
//		rl.addView(iv, rLayoutParams);//�ȳ�ʼ��view�ټӵ�RelativeLayout����
		
//		this.addContentView(iv, rLayoutParams);
		//���еĶ��ӵ� gameRunLayout ���view�ϣ����Էŵ�һ��RelativeLayout�����ټӣ�
		
		
	}
	
	public void initTitleView(int id){
		
	}
	
	//��������������߳��� ����һ����������
	public void startGame(){
		gameState = 1;
		gamePlayThread.start();
//		new Thread(new GameThread()).start();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void initGameViewById(int id){
    	switch (id){
    	case 0:initTodayFortune();break;
    	case 1:initShiye();break;
    	case 2:initAiqing();break;
    	case 3:initCaipiao();break;
    	case 4:initMianshi();break;
    	case 5:initXiangqing();break;
    	case 6:initMajiang();break;
    	}
	}
	
	public void initTodayFortune(){
		gameView = new TodayFortuneView(this, this);
//		TodayFortuneView todayFortuneView = new TodayFortuneView(this, this);
//		RelativeLayout.LayoutParams rLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
//		rLayoutParams.addRule(RelativeLayout.BELOW, titleImg.getId());
//		rLayoutParams.addRule(RelativeLayout.ABOVE, toolsLayout.getId());
//		operation_layout.addView(todayFortuneView, rLayoutParams);
		operation_layout.addView(gameView);
//		operation_layout.setVisibility(View.VISIBLE);
//		this.addContentView(todayFortuneView, rLayoutParams);
		//include
//		RelativeLayout rl = (RelativeLayout)gameRunLayout.findViewById(R.id.gameRunLayout);
		
////		rLayoutParams.setMargins(100, 100, 100, 100);
//		rl.addView(todayFortuneView, rLayoutParams);//�ȳ�ʼ��view�ټӵ�RelativeLayout����
	}

	public void initShiye(){
	    
	}
	public void initAiqing(){
		
	}
	public void initCaipiao(){
		
	}
	public void initMianshi(){
		
	}
	public void initXiangqing(){
		
	}
	public void initMajiang(){
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (this.gamePlayThread != null){
			this.gamePlayThread.interrupt();
			this.gamePlayThread.stop();
		}
		this.myHandler.removeCallbacks(this.runnable);
	}

	class GameThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Thread.currentThread().isInterrupted())
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				gameView.postInvalidate();
			}
		}
		
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
	
	/* ���� ��Χ����� */
	public int randomNumber(int max, int min){
		return (int)Math.round(Math.random() * (max - min)) +min;
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
