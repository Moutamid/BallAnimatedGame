package com.moutamid.ballanimatedgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class CustomView extends ImageView {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrst) {
        super(context, attrst);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        canvas.drawBitmap(bitmap, new Rect(0,0,400,400), new Rect(0,0,240,135) , null);
        super.onDraw(canvas);
        //you need to call postInvalidate so that the system knows that it  should redraw your custom ImageView
        this.postInvalidate();
    }
}
