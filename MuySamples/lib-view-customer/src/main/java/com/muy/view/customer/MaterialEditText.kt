package com.muy.view.customer

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log

/**
 * Created by James on 1/18/21.
 * Desc:
 */
class MaterialEditText(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {
    val LABEL_PADDING = 10.dp
    val TEXT_SIZE = 15.sp

    val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        textSize = TEXT_SIZE.toFloat()
    }
    val animator by lazy {
        ObjectAnimator.ofFloat(this, "factor", 0f, 1f)
                .setDuration(200)
    }

    var factor: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var showLabel = false
        set(value) {
            field = value
            invalidate()
        }

    var enableLabel = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    setPadding(paddingLeft, paddingTop + LABEL_PADDING + TEXT_SIZE, paddingRight, paddingBottom)
                } else {
                    setPadding(paddingLeft, paddingTop - LABEL_PADDING - TEXT_SIZE, paddingRight, paddingBottom)
                }
            }
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        enableLabel = typedArray.getBoolean(R.styleable.MaterialEditText_enableFloatingLabel, true)
        typedArray.recycle()

        paint.color = Color.parseColor("#000000")
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        Log.d(TAG, "onTextChanged: text=${text}")

        if (enableLabel) {
            val isEmptyText = text.isNullOrEmpty()

            if (showLabel && isEmptyText) {
                showLabel = false
                animator.reverse()
            } else if (!showLabel && !isEmptyText) {
                showLabel = true
                animator.start()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (enableLabel) {
            paint.alpha = (factor * 255).toInt()

            val offsetY = TEXT_SIZE.toFloat() + (bottom - TEXT_SIZE.toFloat()) * (1 - factor)

            Log.d(TAG, "onDraw: showLabel=${showLabel}, hint=${hint}")
            canvas.drawText(hint.toString(), 0f, offsetY, paint)
        }
    }

    companion object {
        private const val TAG = "MaterialEditText"
    }
}