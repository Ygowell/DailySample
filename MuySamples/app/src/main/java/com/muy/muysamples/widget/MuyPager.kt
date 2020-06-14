package com.muy.muysamples.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.OverScroller
import kotlin.math.abs

/**
 * Created by James on 2020/5/30.
 * Desc:
 */
class MuyPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private var downX = 0f
    private var downY = 0f
    private var downScrollX = 0f
    private var currPos = 0
    private var isAtLeftScrollToLeft = false
    private var isAtRightScrollToRight = false
    private var isHandledByMyself = false

    private val viewConfiguration = ViewConfiguration.get(context)
    private val pagingSlop = viewConfiguration.scaledPagingTouchSlop

    private val overScroller = OverScroller(context, LinearInterpolator())
    private val velocityTracker = VelocityTracker.obtain()
    private val minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    private val maxVelocity = viewConfiguration.scaledMaximumFlingVelocity

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var top = 0
        var right = width
        var bottom = height

        for (index in 0 until childCount) {
            val child = getChildAt(index)
            child.layout(left, top, right, bottom)
            left += width
            right += width
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (MotionEvent.ACTION_DOWN == ev.actionMasked) {
            velocityTracker.clear()
        }

        var result = false
        velocityTracker.addMovement(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
                isAtLeftScrollToLeft = false
                isAtRightScrollToRight = false
                isHandledByMyself = false
                Log.d("Muy", "downScrollX: $downScrollX")
            }

            MotionEvent.ACTION_MOVE -> {
                if (!isHandledByMyself) {
                    if (abs(ev.x) > pagingSlop) {
                        parent.requestDisallowInterceptTouchEvent(true)
                        result = true
                        isHandledByMyself = true
                    }
                }
            }
        }

        return result
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        velocityTracker.addMovement(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
                isAtLeftScrollToLeft = false
                isAtRightScrollToRight = false
                Log.d("Muy", "downScrollX: $downScrollX")
            }

            MotionEvent.ACTION_MOVE -> {
                val atLeast = if (currPos == 0) 0 else (currPos - 1) * width
                val atMost = if (currPos == childCount - 1) currPos * width else (currPos + 1) * width

                val dx = (downX - ev.x + downScrollX).toInt()
                        .coerceAtLeast(atLeast)
                        .coerceAtMost(atMost)

                if (currPos == 0 && dx <= 0) {
                    isAtLeftScrollToLeft = true
                } else if (currPos == childCount - 1 && dx >= currPos * width) {
                    isAtRightScrollToRight = true
                }

                if (isAtLeftScrollToLeft || isAtRightScrollToRight) {
                    return true
                }

                scrollTo(dx, 0)
            }

            MotionEvent.ACTION_UP -> {
                if (isAtLeftScrollToLeft || isAtRightScrollToRight) {
                    return true
                }

                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val vx = velocityTracker.xVelocity

                val pivot = 1 / 2f * width
                val pageIndex = if (abs(vx) < minVelocity) {
                    // 没有达到快滑的临界值
                    when {
                        (scrollX - currPos * width) > pivot -> {
                            currPos + 1
                        }
                        currPos * width - scrollX > pivot -> {
                            currPos - 1
                        }
                        else -> {
                            currPos
                        }
                    }
                } else {
                    // 快滑的情况
                    if (vx < 0) currPos + 1 else currPos - 1
                }.coerceAtLeast(0).coerceAtMost(childCount - 1)

                Log.d("Muy", "currPos: $currPos")

                val scrollDistance = if (pageIndex == currPos) {
                    currPos * width - scrollX
                } else {
                    pageIndex * width - scrollX
                }
                currPos = pageIndex

                overScroller.startScroll(scrollX, 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }

        return true
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }
}