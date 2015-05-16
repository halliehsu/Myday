package com.hallie.myday;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private HomeLocalActivity activity;
	private Paint paint;

	private Bitmap[] logos = new Bitmap[2];
	Bitmap currentLogo;

	int screenWidth = 320;
	int screenHeight = 480;
	private int currentAlpha = 0;
	private int currentX;
	private int currentY;

	protected long sleepSan = 50;

	public MySurfaceView(HomeLocalActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);
		paint = new Paint();
		paint.setAntiAlias(true);

		logos[0] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.dukea);
		logos[1] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.dukeb);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColor(Color.BLACK);
		paint.setAlpha(255);
		canvas.drawRect(0, 0, screenWidth, screenHeight, paint);

		if (currentLogo == null) {
			return;
		}
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(currentLogo, currentX, currentY, paint);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread() {
			public void run() {
				for (Bitmap bm : logos) {
					currentLogo = bm;
					currentX = screenWidth / 2 - bm.getWidth();
					currentY = screenHeight / 2 - bm.getHeight();

					for (int i = 255; i > -10; i = i - 10) {
						currentAlpha = i;
						if (currentAlpha < 0) {
							currentAlpha = 0;
						}
						SurfaceHolder myholder = MySurfaceView.this.getHolder();
						Canvas canvas = myholder.lockCanvas();
						try {
							synchronized (myholder) {
								onDraw(canvas);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (canvas != null) {
								myholder.unlockCanvasAndPost(canvas);
							}
						}
						try {
							if (i == 255) {
								Thread.sleep(1000);

							}
							Thread.sleep(sleepSan);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				//activity.handler.sendEmptyMessage(0);
			}
		}.start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
