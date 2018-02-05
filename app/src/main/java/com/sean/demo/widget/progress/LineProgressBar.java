package com.sean.demo.widget.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.sean.demo.R;


/**
 * @author smartsean
 */
public class LineProgressBar extends View {

    private static final int MAX_PROGRESS = 100;
    private static final int DEFAULT_LINE_COLOR = Color.parseColor("#e6e6e6");
    private static final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#71db77");

    /**
     * progress底部线的画笔
     */
    private Paint linePaint;
    /**
     * progress画笔
     */
    private Paint progressPaint;
    /**
     * progress底部线的颜色
     */
    private int lineColor;
    /**
     * progress的颜色
     */
    private int progressColor;
    /**
     * 进度值 百分比
     */
    private float progress;
    /**
     * 是否平滑显示progress
     */
    private boolean isSmoothProgress;

    public LineProgressBar(Context context) {
        this(context, null);
    }

    public LineProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化参数
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.LineProgressBar);
        lineColor = attributes.getColor(R.styleable.LineProgressBar_progress_line_color, DEFAULT_LINE_COLOR);
        progressColor = attributes.getColor(R.styleable.LineProgressBar_progress_color, DEFAULT_PROGRESS_COLOR);
        progress = attributes.getInteger(R.styleable.LineProgressBar_progress, 0) / MAX_PROGRESS;
        isSmoothProgress = attributes.getBoolean(R.styleable.LineProgressBar_is_smooth_progress, true);
        attributes.recycle();
        initializePainters();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    private int measure(int measureSpec, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
            result += padding;
            if (mode == MeasureSpec.AT_MOST) {
                if (isWidth) {
                    result = Math.max(result, size);
                } else {
                    result = Math.min(result, size);
                }
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight() / 2.0f, getWidth(), getHeight() / 2.0f, linePaint);
        canvas.drawRoundRect(new RectF(0, 0, progress * getWidth(), getHeight()), getHeight() / 2.0f, getHeight() / 2.0f, progressPaint);
    }

    /**
     * 设置进度
     *
     * @param pProgress
     */
    public void setProgress(float pProgress) {
        if (pProgress > 1) {
            pProgress = 1;
        } else if (pProgress < 0) {
            pProgress = 0;
        }
        if (isSmoothProgress) {
            smoothRun(this.progress, pProgress);
        } else {
            this.progress = pProgress;
            invalidate();
        }
    }

    /**
     * 设置平滑滑动
     *
     * @param currentProgress
     * @param targetProgress
     */
    private void smoothRun(float currentProgress, float targetProgress) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(currentProgress, targetProgress);
        valueAnimator.setTarget(this.progress);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 初始化画笔
     */
    private void initializePainters() {
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(lineColor);
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressColor);
    }

    /**
     * 设置线颜色
     *
     * @param lineColor
     */
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        invalidate();
    }

    /**
     * 设置进度条颜色
     *
     * @param progressColor
     */
    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        invalidate();
    }

    /**
     * 设置是否平滑滑动
     *
     * @param smoothProgress
     */
    public void setSmoothProgress(boolean smoothProgress) {
        isSmoothProgress = smoothProgress;
        invalidate();
    }
}
