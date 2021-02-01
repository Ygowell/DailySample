package com.muy.muysamples.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class SmartTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    init {
        initView()
    }

    private fun initView() {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val widths = floatArrayOf()
//        paint.getTextWidths(text.toString(), widths)
//
//        widths.map {
//
//        }

        if (measuredWidth > 0 && measuredHeight > 0) {
            val autoSplitText = autoSplitText(this)
            if (!TextUtils.isEmpty(autoSplitText)) {
                text = autoSplitText
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun autoSplitText(tv: TextView): CharSequence {
        val originalText: CharSequence = tv.text
        val rawText = originalText.toString()
        val canUseWidth = tv.measuredWidth - tv.paddingLeft - tv.paddingRight

        val words = rawText.replace("\r", "").split(" ")
        val wordBuilder = StringBuilder()

        words.map { word ->
            if (tv.paint.measureText(word) <= canUseWidth) {
                wordBuilder.append(word)
            } else {
                var lettersLength = 0f
                var i = 0
                var startIndex = wordBuilder.length
                while (i < word.length) {
                    lettersLength += tv.paint.measureText(word[i].toString())

                    if (lettersLength <= canUseWidth) { // 还能放下字母
                        wordBuilder.append(word[i])
                        i++
                    } else { // 需要换行
                        wordBuilder.deleteCharAt(startIndex + i - 1)
                        wordBuilder.append("-\n")
                        lettersLength = 0f
                        i--
                    }
                }
            }
            Log.d("Muy", "word = $wordBuilder")
            wordBuilder.append(" ")
        }

        val result = wordBuilder.subSequence(0, wordBuilder.length - 1)
        return result

//        val spannableString = SpannableString()
//        if (originalText is Spanned) {
//            TextUtils.copySpansFrom(
//                    originalText,
//                    0,
//                    originalText.length,
//                    null,
//                    spannableString,
//                    0
//            )
//        }
//
//        return spannableString

//        val lineTexts = rawText.replace("\r", "").split("\n")
//        val stringBuilder = StringBuilder()
//        lineTexts.map { lineText ->
//            Log.d("Muy", lineText)
//            if (tv.paint.measureText(lineText) <= canUseWidth) {
//                stringBuilder.append(lineText)
//            } else {
//                var lineWidth = 0f
//                var i = 0
//                while (i < lineText.length) {
//                    lineWidth += tv.paint.measureText(lineText[i].toString())
//
//                    if (lineWidth <= canUseWidth) {
//                        stringBuilder.append(lineText[i])
//                        i++
//                    } else {
//                        stringBuilder.deleteCharAt(i - 1)
//                        stringBuilder.append("-\n")
//                        lineWidth = 0f
//                        i--
//                    }
//                }
//            }
//            stringBuilder.append("\n")
//        }
//
//        if (rawText.endsWith("\n")) {
//            stringBuilder.deleteCharAt(stringBuilder.length - 1)
//        }
//
//        val spannableString = SpannableString(stringBuilder.toString())
//        if (originalText is Spanned) {
//            TextUtils.copySpansFrom(
//                    originalText,
//                    0,
//                    originalText.length,
//                    null,
//                    spannableString,
//                    0
//            )
//        }
//
//        return spannableString
    }
}