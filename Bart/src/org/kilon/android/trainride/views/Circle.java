package org.kilon.android.trainride.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Circle extends View {

	private final float x;
	private final float y;
	private final int r;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public Circle(Context context, float x, float y, int r, int color) {
		super(context);
		this.x = x;
		this.y = y;
		this.r = r;
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(color);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
	    super.onDraw(canvas);
	    canvas.drawCircle(x, y, r, paint);
	    invalidate();
	}
	

}
