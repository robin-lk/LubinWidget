package com.lubin.widget.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;

import com.lubin.widget.R;
import com.lubin.widget.tools.DpSize;

/**
 * @author lubin
 * @version 1.0 2018
 * 水平进度条
 */
public class HorizontalProgressBar extends ProgressBar {
    /**
     * 进度大小
     */
    private int mProgressSize = 0;
    /**
     * 默认最大值100
     */
    private int maxProgressSize = 100;
    /**
     * 默认progressBar的高度
     */
    private int mProgressWidth = DpSize.dp2px(getContext(), 8);
    private int mUnProgressWidth = DpSize.dp2px(getContext(), 8);
    private int paddingStart=0;
    /**
     * 颜色默认#ff00a2
     */
    private int mProgressBarColor = Color.parseColor("#ff00a2");
    /**
     * 背景（未完成的进度值）颜色默认
     */
    private int mProgressBottomBarColor = Color.parseColor("#D9D9D9");
    private int mProgressCenterBarColor = Color.parseColor("#E6E6E6");
    /**
     * 进度text
     */
    private String mTextNum = "";
    private String mTextPercent = "%";

    private int progress = 1;

    /**
     * 经过测量后得到的需要绘制的总宽度
     */
    private int mDrawWidth;

    private int mTextColor = Color.parseColor("#FFFFFF");
    private int mTextSize = DpSize.sp2px(getContext(), 8);

    private Paint mTextPaint;
    private Paint mProgressPaint;
    private Paint mUnProgressPaint;

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initXmlDate(attrs);
        initPaint();
    }

    private void initXmlDate(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        mTextSize = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_textSize, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.HorizontalProgressBar_textColor, mTextColor);
        mProgressWidth = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_progressBar_height, mProgressWidth);
        mUnProgressWidth = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_progressBar_height, mUnProgressWidth);
        maxProgressSize = typedArray.getInt(R.styleable.HorizontalProgressBar_maxPercent, maxProgressSize);
        mProgressSize = typedArray.getInt(R.styleable.HorizontalProgressBar_defaultPercent, mProgressSize);
        mProgressBarColor = typedArray.getColor(R.styleable.HorizontalProgressBar_progressBar_color, mProgressBarColor);
        mProgressBottomBarColor = typedArray.getColor(R.styleable.HorizontalProgressBar_progressBar_Background, mProgressBottomBarColor);
        paddingStart= (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_paddingStart,paddingStart);
        typedArray.recycle();
    }

    protected void initPaint() {
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);

        mUnProgressPaint = new Paint();
        mUnProgressPaint.setColor(mProgressBottomBarColor);
        mUnProgressPaint.setStyle(Paint.Style.FILL);
        mUnProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mUnProgressPaint.setAntiAlias(true);
        mUnProgressPaint.setStrokeWidth(mUnProgressWidth);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(mProgressBarColor);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth(mProgressWidth);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int heigth = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, heigth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        drawBar(canvas);
        canvas.restore();
    }

    /**
     * 绘制
     *
     * @param canvas Canvas
     */
    private void drawBar(Canvas canvas) {
        drawUnProgress(canvas);
        drawProgress(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        int barH=(getHeight()-mUnProgressWidth)/2;
        int endX=(getWidth()-paddingStart*2)/maxProgressSize*mProgressSize;

        canvas.drawText(mProgressSize+mTextPercent,endX-mTextSize*2,barH+mTextSize/3,mTextPaint);
    }

    private void drawProgress(Canvas canvas) {
        int barH=(getHeight()-mUnProgressWidth)/2;
        int endX=(getWidth()-paddingStart*2)/maxProgressSize*mProgressSize;
        canvas.drawLine(paddingStart,barH,endX,barH,mProgressPaint);
    }

    private void drawUnProgress(Canvas canvas) {
        int barH=(getHeight()-mUnProgressWidth)/2;
        canvas.drawLine(paddingStart,barH,getWidth()-paddingStart,barH,mUnProgressPaint);
    }


    public void runProgressAnim(long duration) {
        setProgressInTime(0, duration);
    }

    /**
     * @param progress 进度值
     * @param duration 动画播放时间
     */
    public void setProgressInTime(final int progress, final long duration) {
        setProgressInTime(progress, getProgress(), duration);
    }

    /**
     * @param startProgress 起始进度
     * @param progress      进度值
     * @param duration      动画播放时间
     */
    public void setProgressInTime(int startProgress, final int progress, final long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startProgress, progress);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                setProgress(currentValue);
            }
        });
        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    public int getmProgressSize() {
        return mProgressSize;
    }

    public void setmProgressSize(int mProgressSize) {
        this.mProgressSize = mProgressSize;
    }
}
