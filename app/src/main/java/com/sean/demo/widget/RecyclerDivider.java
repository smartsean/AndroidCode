package com.sean.demo.widget;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sean.lib.utils.ErrorLogUtil;

/**
 * Created by Fred Zhao on 2017/11/2.
 */

public class RecyclerDivider extends DividerItemDecoration {


    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;

    public static final int VERTICAL = LinearLayout.VERTICAL;

    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    private Drawable mDivider;

    private static final int DEFAULT_DIVIDER_COLOR = Color.TRANSPARENT;

    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;

    private final Rect mBounds = new Rect();

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a
     * {@link LinearLayoutManager}.
     *
     * @param context     Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    public RecyclerDivider(Context context, int orientation) {

        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public RecyclerDivider(float size) {

        this(VERTICAL, size);
    }

    public RecyclerDivider(int orientation, float size) {

        this(orientation, size, DEFAULT_DIVIDER_COLOR);
    }

    public RecyclerDivider(int orientation, float size, @ColorInt int color) {

        mDivider = new DividerDrawable(orientation, size, color);
        setOrientation(orientation);
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * {@link RecyclerView.LayoutManager} changes orientation.
     *
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public void setOrientation(int orientation) {

        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public void setDrawable(@NonNull Drawable drawable) {

        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        mDivider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (parent.getLayoutManager() == null) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent) {

        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
                final int top = bottom - mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {

        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
                final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
                final int left = right - mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        boolean isLast = false;
        try {
            int i = parent.getChildAdapterPosition(view);
            isLast = (i == state.getItemCount() - 1 || i == RecyclerView.NO_POSITION);
        } catch (Exception e) {
            Log.e("RecyclerDivider", ErrorLogUtil.getStackMsg(e));
        }
        if (mOrientation == VERTICAL) {
            outRect.set(0, 0, 0, isLast ? 0 : mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, isLast ? 0 : mDivider.getIntrinsicWidth(), 0);
        }
    }

    class DividerDrawable extends ShapeDrawable {

        private int orientation;

        private float size;

        public DividerDrawable(int orientation, float size, int color) {

            super(new DividerShape(VERTICAL == orientation, size, color));
            this.orientation = orientation;
            this.size = size;
        }

        @Override
        public int getIntrinsicHeight() {

            return orientation == VERTICAL ? (int) size : super.getIntrinsicHeight();
        }

        @Override
        public int getIntrinsicWidth() {

            return orientation == VERTICAL ? super.getIntrinsicWidth() : (int) size;

        }
    }

    class DividerShape extends Shape {

        private RectF mRect = new RectF();

        private boolean isVertical;

        private float size;

        private int color;

        public DividerShape(boolean isVertical, float size, int color) {

            this.isVertical = isVertical;
            this.size = size;
            this.color = color;
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {

            paint.setColor(color);
            canvas.drawRect(mRect, paint);
        }

        @Override
        public void getOutline(Outline outline) {

            final RectF rect = rect();
            outline.setRect((int) Math.ceil(rect.left), (int) Math.ceil(rect.top),
                    (int) Math.floor(rect.right), (int) Math.floor(rect.bottom));
        }

        @Override
        protected void onResize(float width, float height) {

            Log.d("DividerShape", "onRize -> width : " + width + " height : " + height);
            if (isVertical) mRect.set(0, 0, width, size);
            else mRect.set(0, 0, size, height);
//            mRect.set(0, 0, width, height);
        }

        /**
         * Returns the RectF that defines this rectangle's bounds.
         */
        protected final RectF rect() {

            return mRect;
        }

        @Override
        public DividerShape clone() throws CloneNotSupportedException {

            final DividerShape shape = (DividerShape) super.clone();
            shape.mRect = new RectF(mRect);
            return shape;
        }
    }
}
