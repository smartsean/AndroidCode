package com.sean.demo.widget.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.sean.demo.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.ceil;


/**
 * Created by Sean on 2017/6/5 15:50.
 */

public class ArcProgressBar extends View {

    private static final String TAG = "ArcProgressBar";
    /**
     * 默认字体颜色
     */
    private static final int CENTER_TEXT_COLOR = Color.parseColor("#393939");
    /**
     * 默认字体大小 10sp
     */
    private static final int CENTER_TEXT_SIZE = 10;
    /**
     * 默认外圆半径
     */
    private static final int OUTER_RAD_RADIUS = 62;// dp
    /**
     * 默认内圆占外圆的百分比
     */
    private static final float INNER_CIRCLE_PERCENT = 0.7f;
    /**
     * 默认中心区域显示的图标
     */
    private static final int CENTER_ICON = R.drawable.mt_detail_chart_center_ic;
    /**
     * 默认内圆背景
     */
    private static final int INNER_BACKGROUND_COLOR = Color.parseColor("#F8FBFE");
    /**
     * 默认外圆刻度线的颜色
     */
    private static final int OUTER_LINE_COLOR = Color.parseColor("#CCCCCC");
    /**
     * 默认外圆未到达的线的颜色
     */
    private static final int OUTER_LINE_UNREACH_COLOR = Color.parseColor("#E6E6E6");
    /**
     * 默认外圆刻度线的个数
     */
    private static final int OUTER_LINE_NUMBER = 100;
    /**
     * 默认外圆刻度线的长度 dp
     */
    private static final int OUTER_LINE_LENGTH = 12;
    /**
     * 默认是否显示未到达外圆刻度线的背景
     */
    private static final boolean IS_SHOW_OUTER_LINE_BG = false;

    /**
     * 中间文字
     */
    private String mCenterText = "时长统计";
    /**
     * 文字颜色
     */
    private int mCenterTextColor = CENTER_TEXT_COLOR;
    /**
     * 文字大小
     */
    private int mCenterTextSize = sp2px(CENTER_TEXT_SIZE);
    /**
     * 中间的icon
     */
    private Bitmap mCenterIcon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.mt_detail_chart_center_ic);
    /**
     * 内圆的背景色
     */
    private int mInnerBackgroundColor = INNER_BACKGROUND_COLOR;
    /**
     * 内圆的大小
     */
    private int mInnerCircleRadius = dp2px((int) (OUTER_RAD_RADIUS * INNER_CIRCLE_PERCENT));
    /**
     * 外弧线的颜色
     */
    private int mOuterRedColor = OUTER_LINE_COLOR;
    /**
     * 外圆小锯齿的颜色
     */
    private int mOuterUnreachColor = OUTER_LINE_UNREACH_COLOR;
    /**
     * 外圆半径
     */
    private int mOuterRadRadius = dp2px(OUTER_RAD_RADIUS);
    /**
     * 外环刻度线的数量
     */
    private int mOuterLineNumber = OUTER_LINE_NUMBER;
    /**
     * 是否显示外环底部背景刻度线
     */
    private boolean mIsShowOuterLineBg = IS_SHOW_OUTER_LINE_BG;
    /**
     * 外环刻度线的长度
     */
    private int mOuterLineLength = dp2px(OUTER_LINE_LENGTH);
    /**
     * 是否平滑滑动
     */
    private boolean isSmoothProgress = false;
    /**
     * 真实宽度
     */
    private int realWidth;
    /**
     * 弧形Progress的总角度
     */
    private float progress = 270.00f;

    /**
     * 弧形Progress的颜色
     */
    private int[] progressColor = new int[]{Color.parseColor("#0CC470"), Color.parseColor("#FABF40"), Color
            .parseColor("#d8d8d8")};
    /**
     * 弧形Progress的数值
     */
    private float[] progressData = new float[]{0.44f, .26f, .30f,};

    /**
     * 文字画笔
     */
    private Paint textPaint;
    /**
     * 中间图标画笔
     */
    private Paint bitmapPaint;
    /**
     * 内圆画笔
     */
    private Paint innerCirclePaint;
    /**
     * 外圆画笔
     */
    private Paint outerRadPaint;
    /**
     * 外圆刻度画笔
     */
    private Paint outerLinePaint;
    /**
     * 外圆刻度未到达画笔
     */
    private Paint unReachPaint;

    public ArcProgressBar(Context context) {

        this(context, null);
    }

    public ArcProgressBar(Context context, @Nullable AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public ArcProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initPaint();
    }

    private void initAttrs(AttributeSet attrs) {

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ArcProgressBar);
        mCenterText = (String) ta.getText(R.styleable.ArcProgressBar_center_text);
        mCenterTextColor = ta.getColor(R.styleable.ArcProgressBar_center_text_color, mCenterTextColor);
        mCenterTextSize = (int) ta.getDimension(R.styleable.ArcProgressBar_center_text_size, mCenterTextSize);
        mCenterIcon = BitmapFactory.decodeResource(getContext().getResources(), ta.getResourceId(R.styleable.ArcProgressBar_center_icon, R.drawable.mt_detail_chart_center_ic));
        mInnerBackgroundColor = ta.getColor(R.styleable.ArcProgressBar_inner_background_color, mInnerBackgroundColor);
        mOuterRedColor = ta.getColor(R.styleable.ArcProgressBar_outer_red_color, mOuterRedColor);
        mOuterUnreachColor = ta.getColor(R.styleable.ArcProgressBar_outer_line_unreach_color, mOuterUnreachColor);
        mInnerCircleRadius = (int) ta.getDimension(R.styleable.ArcProgressBar_inner_rad_radius, mInnerCircleRadius);
        mOuterRadRadius = (int) ta.getDimension(R.styleable.ArcProgressBar_outer_rad_radius, mOuterRadRadius);
        mOuterLineNumber = ta.getInt(R.styleable.ArcProgressBar_outer_line_number, mOuterLineNumber);
        mIsShowOuterLineBg = ta.getBoolean(R.styleable.ArcProgressBar_is_show_outer_line_bg, mIsShowOuterLineBg);
        isSmoothProgress = ta.getBoolean(R.styleable.ArcProgressBar_is_smooth, false);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec, 0, true);
        int height = measure(heightMeasureSpec, 0, true);
        realWidth = Math.min(width, height);
        mOuterRadRadius = (realWidth - mOuterLineLength * 2) / 2;
        setMeasuredDimension(realWidth, realWidth);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        realWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mOuterRadRadius = (realWidth - mOuterLineLength * 2) / 2;
    }

    private int measure(int measureSpec, int minSize, boolean isWidth) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        if (mode == MeasureSpec.EXACTLY) {//限制固定值
            result = size;
        } else {//不限制尺寸
            size = Math.max(isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight(), minSize);
            result = size + padding;
            if (mode == MeasureSpec.AT_MOST) {//限制上限
                result = Math.min(size, result);
            }
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        // 移动坐标原点到中心
        canvas.translate(mOuterRadRadius + mOuterLineLength + getPaddingLeft(), mOuterRadRadius + mOuterLineLength +
                getPaddingTop());

        // 画内圆
        canvas.drawCircle(0, 0, mInnerCircleRadius, innerCirclePaint);

        // 画文字
        if (!TextUtils.isEmpty(mCenterText)) {
            float textWidth = getTextWidth(textPaint, mCenterText);
            float textHeight = (textPaint.descent() + textPaint.ascent()) / 2;
            canvas.drawText(mCenterText, -textWidth / 2, mInnerCircleRadius * 5 / 12 + textHeight, textPaint);
        }

        //画图片，就是贴图
        float pngWidth = mCenterIcon.getWidth();
        float pngHeight = mCenterIcon.getHeight();
        canvas.drawBitmap(mCenterIcon, -pngWidth / 2, -pngHeight - dp2px(6), bitmapPaint);

        // 画外环
        RectF rectF = new RectF();
        rectF.set(-mOuterRadRadius, -mOuterRadRadius, mOuterRadRadius, mOuterRadRadius);
        canvas.drawArc(rectF, 135, 270, false, outerRadPaint);
        checkData();
        // 画背景刻度线
        drawOutBgScale(canvas);
        // 画进度刻度线
//        drawOutProgressScale(canvas);
        drawOutProgressScale1(canvas);
    }

    private void drawOutProgressScale1(Canvas canvas) {
        canvas.save();
        canvas.rotate(135);
        progressData = formatData(progressData);
        float rotateDegree = progress / (mOuterLineNumber - 1);
        int[] scale = new int[progressData.length];
        int count = 0;
        for (int i = 0; i < progressData.length; i++) {
            count = (int) (progressData[i] * mOuterLineNumber) + count;
            scale[i] = count;
        }
        for (int i = 0; i < mOuterLineNumber; i++) {
            for (int i1 = 0; i1 < scale.length; i1++) {
                if (i < scale[i1]) {
                    outerLinePaint.setColor(progressColor[i1]);
                    break;
                }
            }
            canvas.drawLine(mOuterRadRadius, 0, mOuterRadRadius + dp2px(12), 0, outerLinePaint);
            //将坐标系绕点（width/2，height/2）旋转270 / 99度
            canvas.rotate(rotateDegree);
        }
        canvas.restore();
    }

    private void drawOutBgScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(135);
        // 需要外环刻度线背景
        if (mIsShowOuterLineBg) {
            for (int i = 0; i < mOuterLineNumber; i++) {
                canvas.drawLine(mOuterRadRadius, 0, mOuterRadRadius + dp2px(12), 0, unReachPaint);
                canvas.rotate(270.00f / (mOuterLineNumber - 1));
            }
            canvas.rotate(90 - 270.00f / (mOuterLineNumber - 1));
        }
        canvas.restore();
    }

    /**
     * 画外环锯齿线
     */
    private void drawOutProgressScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(135);
        progressData = formatData(progressData);
        //遍历数据集合
        for (int i = 0; i < progressData.length; i++) {
            double line = progressData[i] + 0.000005f;
            int lineNum = (int) (mOuterLineNumber * line);
            for (int j = 0; j < lineNum; j++) {
                outerLinePaint.setColor(progressColor[i]);
                canvas.drawLine(mOuterRadRadius, 0, mOuterRadRadius + dp2px(12), 0, outerLinePaint);
                //将坐标系绕点（width/2，height/2）旋转270 / 99度
                canvas.rotate(progress / (mOuterLineNumber - 1));
            }
        }
        canvas.restore();
    }

    /**
     * 设置颜色
     *
     * @param colors
     */
    public void setColors(int[] colors) {

        if (colors != null) {
            progressColor = colors;
        }
        smoothRun(270.00f);
//        invalidate();
    }

    /**
     * 设置数据
     *
     * @param datas
     */
    public void setDatas(float[] datas) {

        if (null == datas) return;
        progressData = datas;
//        smoothRun(270.00f);
        invalidate();
    }

    /**
     * 处理百分比数据
     *
     * @param datas
     * @return
     */
    private float[] formatData(float[] datas) {

        if (datas.length <= 0) return datas;
        float total = 0.00f;
        float min = 1.00f;
        int minPos = 0;
        for (int i = 0; i < datas.length; i++) {
            total += datas[i];
            if (min > datas[i]) {
                min = datas[i];
                minPos = i;
            }
        }
        float exMinValue = 0.00f;
        if (Math.abs(total) >= 1e-6) {

            for (int i = 0; i < datas.length; i++) {
                if (i != minPos) {
                    datas[i] = datas[i] / total;
                    exMinValue += datas[i];
                }
            }
        }
        BigDecimal b = new BigDecimal(1 - exMinValue);
        datas[minPos] = (float) b.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return datas;
    }

    /**
     * 设置平滑滑动
     *
     * @param targetProgress
     */
    private void smoothRun(float targetProgress) {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, targetProgress);
        valueAnimator.setTarget(targetProgress);
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
    private void initPaint() {

        textPaint = new Paint();
        textPaint.setColor(mCenterTextColor);
        textPaint.setTextSize(mCenterTextSize);
        textPaint.setAntiAlias(true);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);

        innerCirclePaint = new Paint();
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setColor(mInnerBackgroundColor);
        innerCirclePaint.setStyle(Paint.Style.FILL);

        outerRadPaint = new Paint();
        outerRadPaint.setAntiAlias(true);
        outerRadPaint.setColor(mOuterRedColor);
        outerRadPaint.setStyle(Paint.Style.STROKE);
        outerRadPaint.setStrokeWidth(dp2px(1));

        outerLinePaint = new Paint();
        outerLinePaint.setColor(Color.parseColor("#E6E6E6"));
        outerLinePaint.setStyle(Paint.Style.STROKE);
        outerLinePaint.setStrokeWidth(dp2px(1));
        outerLinePaint.setAntiAlias(true);

        unReachPaint = new Paint();
        unReachPaint.setColor(Color.parseColor("#E6E6E6"));
        unReachPaint.setStyle(Paint.Style.STROKE);
        unReachPaint.setStrokeWidth(dp2px(1));
        unReachPaint.setAntiAlias(true);
    }


    protected int dp2px(int dpVal) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }

    /**
     * 获取文字宽度
     *
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {

        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) ceil(widths[j]);
            }
        }
        return iRet;
    }

    /**
     * 绘制前检查数据
     */
    private void checkData() {
        if (progressColor == null && progressColor.length == 0) {
            throw new IllegalArgumentException("progressColor cannot be empty!");
        }
        if (progressData == null && progressData.length == 0) {
            throw new IllegalArgumentException("progressData cannot be empty!");
        }
        if (progressColor.length != progressData.length) {
            throw new IllegalArgumentException("progressColor length must equal to progressData length!");
        }
    }
}
