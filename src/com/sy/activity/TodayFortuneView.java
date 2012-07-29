package com.sy.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TodayFortuneView extends View {

	Button testBtn;
	LuckyGameActivity luckyActivity;
	Bitmap center_bg;
	Bitmap titleImg;
	Bitmap startImg;
	Bitmap startOnImg;
	Bitmap startOffImg;
	//tools part
	Bitmap soundOnImg;
	Bitmap soundOffImg;
	Bitmap settingsOnImg;
	Bitmap settingsOffImg;
	Bitmap backOnImg;
	Bitmap backOffImg;
	Bitmap guangbiaoNormal;
	Bitmap guangbiaoSelected;
	
	Bitmap starFullImg;
	Bitmap starHalfImg;
	Bitmap starEmptyImg;
	
	int topGap;
	int startImgX;
	int startImgY;
	int toosImgY;
	int settingsImgX;
	int goBackImgX;
	boolean isStartPressed;
	public final static int rowNumbers = 6;
	public final static int columnNumbers = 6;
	public final static int totalNumbers = rowNumbers*columnNumbers;
	public final static int qiziToQipanX = 2;//���������������֮��ļ�϶
	public final static int qiziToQipanY = 2;
	public final static int qiziToQiziX = 3;//����������֮��� ��϶
	public final static int qiziToQiziY = 3;
	public final static int chessWidth = 50;
	public final static int chessHeight = 50;
	int[][] qizi = new int[6][6]; 
	private int score;//�÷�
//	public int tmpI = -1;
//	public int tmpJ = -1;
//	
//	public Runnable runnable = new Runnable() {
//		
//		@Override
//		public void run() {
//			this.update();
//			luckyActivity.myHandler.postDelayed(this, 1000);
//		}
//		void update(){
//			if (luckyActivity.gameState == 1){
//				tmpI = randomNumber(0, 6);
//				tmpJ = randomNumber(0, 6);
//			}
//		}
//	};
	public TodayFortuneView(Context context, LuckyGameActivity activity) {
		super(context);
		luckyActivity = activity;
//		this.setBackgroundColor(Color.RED);
//		this.setBackgroundResource(R.drawable.background_game);
//		new Thread(new GameThread()).start();/
//		LinearLayout l = new LinearLayout(context);
//		l.setOrientation(LinearLayout.VERTICAL);
////		testBtn.setLayoutParams(params)
//		
//		testBtn = new Button(context);
//		testBtn.setText("Hello, Everyone!!");
//		l.addView(testBtn);
//		luckyActivity.setContentView(l);
		initBitmap();
		
//		new Thread(new GameThread()).start();
	}
	
	public void initBitmap(){
		center_bg = BitmapFactory.decodeResource(getResources(), R.drawable.center_bg);
		titleImg = BitmapFactory.decodeResource(getResources(), R.drawable.todayfortune);
		startImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_start);
		startOnImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_start_on);
		startOffImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_start_off);
		
//		soundOnImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_sound_on);
//		soundOffImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_sound_off);
		settingsOnImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_settings_on);
		settingsOffImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_settings_off);
		backOnImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_back_on);
		backOffImg = BitmapFactory.decodeResource(getResources(), R.drawable.btn_back_off);
		guangbiaoNormal = BitmapFactory.decodeResource(getResources(), R.drawable.guangbiao_normal);
		guangbiaoSelected = BitmapFactory.decodeResource(getResources(), R.drawable.guangbiao_selected);
		
		starFullImg = BitmapFactory.decodeResource(getResources(), R.drawable.star_full);
		starHalfImg = BitmapFactory.decodeResource(getResources(), R.drawable.star_half);
		starEmptyImg = BitmapFactory.decodeResource(getResources(), R.drawable.star_empty);
		
		topGap = 0;
//		topGap = (luckyActivity.getScreenHeight() - center_bg.getHeight())/2;
		startImgX = (luckyActivity.getScreenWidth() - startImg.getWidth())/2;
		startImgY = topGap + center_bg.getHeight() ;
		toosImgY = luckyActivity.getScreenHeight() - backOnImg.getHeight();
		goBackImgX = luckyActivity.getScreenWidth() - backOnImg.getWidth();
		settingsImgX = (luckyActivity.getScreenWidth() - settingsOnImg.getWidth())/2;
	}

	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
//		super.onDraw(canvas);
		Paint paint = new Paint();
//		canvas.drawRect(30, 30, 50, 50, paint);
//		canvas.drawText("Test!!", 100, 100, paint);
//		canvas.drawBitmap(titleImg, 0, 0, paint);
		canvas.drawBitmap(center_bg, 0, topGap, paint);
		//init ���
		if (luckyActivity.gameState == 0){//��Ϸδ��ʼ()
			for (int i=0; i<rowNumbers; i++){
				for (int j=0; j<columnNumbers; j++){
					canvas.drawBitmap(guangbiaoNormal,  2+ j* (guangbiaoNormal.getWidth()+3),
							topGap+2 + i*(guangbiaoNormal.getWidth() + 3), paint);
				}
			}
		}else if(luckyActivity.gameState == 1){//��Ϸ��ʼ
			//���һ������� �������Ӧ��λ�þ���ʾһ�����Ա�ѡ�еĺ�ɫ��������
			
			for (int i=0; i<rowNumbers; i++){
				for (int j=0; j<columnNumbers; j++){
					if (i == luckyActivity.tmpI && j == luckyActivity.tmpJ){
						canvas.drawBitmap(guangbiaoSelected,  qiziToQipanX+ j* (guangbiaoNormal.getWidth()+qiziToQiziX),
								topGap+qiziToQipanY + i*(guangbiaoNormal.getWidth() + qiziToQiziY), paint);
						qizi[i][j] = 1;//�����ɫ
					}else {
						canvas.drawBitmap(guangbiaoNormal,  qiziToQipanX+ j* (guangbiaoNormal.getWidth()+qiziToQiziX),
								topGap+qiziToQipanY + i*(guangbiaoNormal.getWidth() + qiziToQiziY), paint);
						qizi[i][j] = 0;
					}
				}
			}
		}
		
//		if (isStartPressed){
//			canvas.drawBitmap(startOnImg, startImgX, startImgY, paint);
//		}else {
//			canvas.drawBitmap(startOffImg, startImgX, startImgY, paint);
//		}
//		
//		if (luckyActivity.isOpenSound){
//			canvas.drawBitmap(soundOnImg, 0, toosImgY, paint);
//		}else {
//			canvas.drawBitmap(soundOffImg, 0, toosImgY, paint);
//		}
//		canvas.drawBitmap(settingsOnImg, settingsImgX, toosImgY, paint);
//		canvas.drawBitmap(backOnImg, goBackImgX, toosImgY, paint);
	}
	
	/* ���� ��Χ����� */
	public int randomNumber(int max, int min){
		return (int)Math.round(Math.random() * (max - min)) +min;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN){
			if(luckyActivity.gameState == 1){//��Ϸ��ʼ
				if (event.getX() > 0 && event.getX() < center_bg.getWidth()
						&& event.getY() > 0 && event.getY() < center_bg.getHeight()){//��������
					int i = -1, j = -1;
					int[] pos = getPos(event);
					i = pos[0];
					j = pos[1];
					if (qizi[i][j] == 1){//����Ӧ������һ���������
						luckyActivity.gameState = 0;
						qizi[i][j] = 0;
//						luckyActivity.gamePlayThread.interrupt();
//						luckyActivity.gamePlayThread.stop();
						luckyActivity.myHandler.removeCallbacks(luckyActivity.runnable);
						
						//������
						LayoutInflater inflater = (LayoutInflater)luckyActivity.getApplicationContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View view = inflater.inflate(R.layout.resultlayout, null);
						
						ImageView star_1 = (ImageView)view.findViewById(R.id.starImg_1);
						ImageView star_2 = (ImageView)view.findViewById(R.id.starImg_2);
						ImageView star_3 = (ImageView)view.findViewById(R.id.starImg_3);
						ImageView star_4 = (ImageView)view.findViewById(R.id.starImg_4);
						ImageView star_5 = (ImageView)view.findViewById(R.id.starImg_5);
						
						TextView resultText = (TextView)view.findViewById(R.id.resultText);
						
						score = luckyActivity.randomNumber(0, 11);//0,0.5,1,1.5,...,9.5,10;
						switch (score){
							case 0: resultText.setText("�ҳ��ź����������������൱��˥����");break;
							case 1: star_1.setImageBitmap(starHalfImg);
									resultText.setText("�ۣ����쿪��������������ô��������ˣ�");break;
							case 2: star_1.setImageBitmap(starFullImg);
									resultText.setText("�������٣�����Ҫ���Լ�Ŭ���ˣ�");break;
							case 3: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starHalfImg);
									resultText.setText("����������һ�㣡");break;
							case 4: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									resultText.setText("�����������е���ɫ��");break;
							case 5: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									star_3.setImageBitmap(starHalfImg);
									resultText.setText("һ������������һ���������У�");break;
							case 6: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									star_3.setImageBitmap(starFullImg);
									resultText.setText("��������������ˣ�����̫���Ŀ���");break;
							case 7: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									star_3.setImageBitmap(starFullImg);
									star_4.setImageBitmap(starHalfImg);
									resultText.setText("�����������У���Ҫ���Ͱ���");break;
							case 8: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									star_3.setImageBitmap(starFullImg);
									star_4.setImageBitmap(starFullImg);
									resultText.setText("������������Ŷ����Ҫ�û�������Ŷ��");break;
							case 9: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									star_3.setImageBitmap(starFullImg);
									star_4.setImageBitmap(starFullImg);
									star_5.setImageBitmap(starHalfImg);
									resultText.setText("�����������൱����Ŷ��");break;
							case 10: star_1.setImageBitmap(starFullImg);
									star_2.setImageBitmap(starFullImg);
									star_3.setImageBitmap(starFullImg);
									star_4.setImageBitmap(starFullImg);
									star_5.setImageBitmap(starFullImg);
									resultText.setText("��ϲ�㣬������޵���!");break;
						
						}
//						star_1.setImageBitmap(starFullImg);
//						star_2.setImageBitmap(starFullImg);
//						star_3.setImageBitmap(starFullImg);
//						star_4.setImageBitmap(starFullImg);
//						star_5.setImageBitmap(starHalfImg);
						
						AlertDialog.Builder builder = new AlertDialog.Builder(luckyActivity);
						builder.setView(view);
						
						builder.setTitle("����ָ��").setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
								luckyActivity.onBackPressed();
							}
						}).create().show();
					}
				}
			}
				
			
//			if (event.getX() > startImgX && event.getX() < startImgX + startImg.getWidth()
//					&& event.getY() > startImgY && event.getY() <startImgY + startImg.getHeight()){
//				isStartPressed = true;
//				//��Ҫ�Ķ���(��һ���̣߳��㿪ʼʱ�򿪵��ã�ˢ��)
//				
//			}
//			
//			if (event.getX() > goBackImgX && event.getX() < goBackImgX + backOnImg.getWidth()
//					&& event.getY() > toosImgY && event.getY() <toosImgY + backOnImg.getHeight()){
//				this.luckyActivity.onBackPressed();
//			}
		}
		return super.onTouchEvent(event);
	}
	
	public int[] getPos(MotionEvent event){
		int[] pos = new int[2];
		double x = event.getX();
		double y = event.getY();
		if (x > 0 && x < center_bg.getWidth() && y > 0 && y < center_bg.getHeight()){
			pos[0] = (int)Math.floor((y - qiziToQipanY)/(chessHeight - this.qiziToQiziY/2));
			pos[1] = (int)Math.floor((x - qiziToQipanX)/(chessWidth - this.qiziToQiziX/2));
		}else {
			pos[0] = -1;
			pos[1] = -1;
		}
		return pos;
	}

	class startGameThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (luckyActivity.gameState == 1){
//				tmpI = randomNumber(0, 6);
//				tmpJ = randomNumber(0, 6);
			}
		}
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
				TodayFortuneView.this.postInvalidate();
			}
		}
		
	}

}
