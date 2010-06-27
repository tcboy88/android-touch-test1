package net.hanjava.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View {
	public static final int QUEUE_CAPACITY = 20;
	public static final int PRESSURE_CONST = 500;

	private TouchData[] touchDataQ = new TouchData[QUEUE_CAPACITY];
	private int pointer = 0;
	private Paint paint = new Paint();

	public TouchView(Context cxt) {
		super(cxt);
		paint.setARGB(100, 100, 100, 100);
		for(int i=0;i<QUEUE_CAPACITY;i++) {
			touchDataQ[i] = new TouchData();
		}
	}

	public TouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		TouchData tData = touchDataQ[pointer];
		tData.x = e.getX();
		tData.y = e.getY();
		tData.r = e.getPressure() * PRESSURE_CONST;
		invalidate();
		pointer = getNext( pointer );
		return true;
	}

	private static int getNext(int cur) {
		int next = cur + 1;
		if(next>=QUEUE_CAPACITY) {
			next = 0;
		}
		return next;
	}

	@Override
	protected void onDraw(Canvas c) {
		int p = pointer;
		for(int i=0;i<QUEUE_CAPACITY;i++) {
			TouchData d = touchDataQ[p];
			int a = 255 * i / QUEUE_CAPACITY;
			paint.setAlpha(a);
			if(d.r>0) {
				c.drawCircle(d.x, d.y, d.r, paint);
			}
			p = getNext(p);
		}
	}
}