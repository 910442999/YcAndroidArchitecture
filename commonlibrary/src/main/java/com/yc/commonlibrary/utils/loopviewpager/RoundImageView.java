package com.yc.commonlibrary.utils.loopviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 1.BitmapShader
 *
 * @author Administrator
 */
public class RoundImageView extends AppCompatImageView {

    private BitmapShader mBitmapShader;
    private Paint mBitmapPaint = new Paint();
    private RectF mRoundRect = new RectF();
    private int x_radius = 20;
    private int y_radius = 20;

    public RoundImageView(Context context) {
        super(context);
        initObjectAttribute();
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObjectAttribute();
    }

    private void initObjectAttribute() {
        mBitmapPaint.setAntiAlias(true);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        createBitmapShader();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
//		createBitmapShader();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        createBitmapShader();
    }

    private void createBitmapShader() {
        mBitmapShader = null;
        Drawable mDrawable = getDrawable();
        if (null == mDrawable) {
            return;
        }

        if (mDrawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) mDrawable;
            Bitmap bitmap = bd.getBitmap();
            mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        } else //if Drawable instanceof NinePathDrawable ，the code below is bad , because a view reference two bitmap ( one in NinePath , other is here)
        {
            int w = mDrawable.getIntrinsicWidth();
            int h = mDrawable.getIntrinsicHeight();

            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            mDrawable.setBounds(0, 0, w, h);
            mDrawable.draw(canvas);
            mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRoundRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable mDrawable = getDrawable();
        if (null == mDrawable || null == mBitmapShader) {
            return;
        }
        Matrix mDrawMatrix = getImageMatrix();
        if (null == mDrawMatrix) {
            mDrawMatrix = new Matrix();
        }
        mBitmapShader.setLocalMatrix(mDrawMatrix);
        mBitmapPaint.setShader(mBitmapShader);
        canvas.drawRoundRect(mRoundRect, x_radius, y_radius, mBitmapPaint);
    }
}
