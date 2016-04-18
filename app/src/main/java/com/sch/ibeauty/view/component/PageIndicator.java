package com.sch.ibeauty.view.component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.sch.ibeauty.R;
import com.sch.ibeauty.util.DisplayUtil;

/**
 * Created by shichaohui on 2015/7/10 0010.
 * <p>
 * 页码指示器类，获得此类实例后，可通过{@link PageIndicator#init(int)}方法初始化指示器,
 * 调用{@link PageIndicator#setCurrentPage(int)}方法切换指示器.
 */
public class PageIndicator extends LinearLayout {

    private Context context = null;
    private int count;
    private int lines; // 行/列的数量
    private int maxIndicatorOfLine = -1; // 一行/一列的数量

    private View animIndicator; // 执行动画的View

    private final int INDICATOR_NORMAL = R.mipmap.ic_indicator_normal;
    private final int INDICATOR_SELECTED = R.mipmap.ic_indicator_selected;

    private final int INDICATOR_SIZE = DisplayUtil.dp2px(15); // 单个指示器的宽高
    private final int INDICATOR_MARGIN = DisplayUtil.dp2px(2); // 单个指示器的边距

    public PageIndicator(Context context) {
        this(context, null);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        animIndicator = new View(context);
        animIndicator.setBackgroundResource(INDICATOR_SELECTED);
    }

    /**
     * 初始化指示器，默认选中第一页
     *
     * @param count 指示器数量，即页数
     */
    public void init(int count) {

        this.count = count;

        for (int i = 0; i < count; i++) {
            View view = new View(context);
            LayoutParams params = new LayoutParams(INDICATOR_SIZE, INDICATOR_SIZE);
            params.setMargins(INDICATOR_MARGIN, INDICATOR_MARGIN, INDICATOR_MARGIN, INDICATOR_MARGIN);

            view.setBackgroundResource(INDICATOR_NORMAL);
            addView(view, params);
        }

        addView(animIndicator);

    }

    /**
     * 设置选中页
     *
     * @param selected 页下标，从0开始
     */
    public void setCurrentPage(int selected) {

        View child = getChildAt(selected);

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(animIndicator, "translationX",
                animIndicator.getTranslationX(), child.getLeft() - animIndicator.getLeft());
        animatorX.setDuration(100);
        animatorX.start();

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(animIndicator, "translationY",
                animIndicator.getTranslationY(), child.getTop() - animIndicator.getTop());
        animatorY.setDuration(100);
        animatorY.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (count <= 0) {
            return;
        }

        if (getOrientation() == HORIZONTAL) {
            measureHorizontal();
        } else if (getOrientation() == VERTICAL) {
            measureVertical();
        }
    }

    private void measureHorizontal() {

        double countTmp = getMeasuredWidth() / (INDICATOR_SIZE + INDICATOR_MARGIN * 2d);
        maxIndicatorOfLine = (int) Math.ceil(countTmp);
        lines = (int) Math.ceil((double) count / maxIndicatorOfLine);

        setMeasuredDimension(getMeasuredWidth(), lines * (INDICATOR_SIZE + INDICATOR_MARGIN));
    }

    private void measureVertical() {

        double countTmp = getMeasuredHeight() / (INDICATOR_SIZE + INDICATOR_MARGIN * 2d);
        maxIndicatorOfLine = (int) Math.ceil(countTmp);
        lines = (int) Math.ceil((double) count / maxIndicatorOfLine);

        setMeasuredDimension(lines * (INDICATOR_SIZE + INDICATOR_MARGIN), getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (getChildCount() >= 0) {
            super.onLayout(changed, left, top, right, bottom);
        }

        int indicatorRangSize = (INDICATOR_SIZE + INDICATOR_MARGIN * 2);
        if (getOrientation() == HORIZONTAL) {
            layoutHorizontal(indicatorRangSize);
        } else if (getOrientation() == VERTICAL) {
            layoutVertical(indicatorRangSize);
        }

        View firstChild = getChildAt(0);
        if (firstChild != null) {
            animIndicator.layout(firstChild.getLeft(), firstChild.getTop(),
                    firstChild.getRight(), firstChild.getBottom());
        }
    }

    private void layoutHorizontal(int indicatorRangSize) {
        for (int i = 0; i < lines; i++) {
            int start = i * maxIndicatorOfLine;
            for (int j = start; j < start + maxIndicatorOfLine; j++) {
                if (j < count) {
                    // 计算剩余的总数量
                    int restCount = count - i * maxIndicatorOfLine;
                    // 计算当前行需要显示的数量
                    int countOfLine = restCount > maxIndicatorOfLine ? maxIndicatorOfLine : restCount;
                    // 计算当前行的起始位置
                    int startLeft = (getMeasuredWidth() - indicatorRangSize * countOfLine) / 2;
                    // 计算子View坐标
                    int indacatorLeft = startLeft + j % maxIndicatorOfLine * indicatorRangSize + INDICATOR_MARGIN;
                    int indacatorTop = i * INDICATOR_SIZE + INDICATOR_MARGIN;
                    int indacatorRifht = indacatorLeft + INDICATOR_SIZE;
                    int indacatorBottom = indacatorTop + INDICATOR_SIZE;
                    // 设置子View坐标
                    getChildAt(j).layout(indacatorLeft, indacatorTop, indacatorRifht, indacatorBottom);
                }
            }
        }
    }

    private void layoutVertical(int indicatorRangSize) {
        for (int i = 0; i < lines; i++) {
            int start = i * maxIndicatorOfLine;
            for (int j = start; j < start + maxIndicatorOfLine; j++) {
                if (j < count) {
                    // 计算剩余的总数量
                    int restCount = count - i * maxIndicatorOfLine;
                    // 计算当前列需要显示的数量
                    int countOfLine = restCount > maxIndicatorOfLine ? maxIndicatorOfLine : restCount;
                    // 计算当前行的起始位置
                    int startTop = (getHeight() - indicatorRangSize * countOfLine) / 2;
                    // 计算子View坐标
                    int indacatorLeft = i * INDICATOR_SIZE + INDICATOR_MARGIN;
                    int indacatorTop = startTop + j % maxIndicatorOfLine * indicatorRangSize + INDICATOR_MARGIN;
                    int indacatorRifht = indacatorLeft + INDICATOR_SIZE;
                    int indacatorBottom = indacatorTop + INDICATOR_SIZE;
                    // 设置子View坐标
                    getChildAt(j).layout(indacatorLeft, indacatorTop, indacatorRifht, indacatorBottom);
                }
            }
        }
    }

}