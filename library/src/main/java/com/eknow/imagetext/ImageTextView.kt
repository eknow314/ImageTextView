package com.eknow.imagetext

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * @Description: 上图片，下文字控件，中间图片遮罩
 * @author: Eknow
 * @date: 2022/1/13 17:55
 */
class ImageTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val mContext: Context = context

    var mImageView: AppCompatImageView? = null
        private set

    var mTextView: AppCompatCheckedTextView? = null
        private set

    var mMaskView: AppCompatImageView? = null
        private set

    private fun createView() {
        when (mImgAt) {
            ImgAt.LEFT -> createViewLeft()
            ImgAt.TOP -> createViewTop()
        }
    }

    private fun createViewTop() {
        val parentId = this.id
        // 创建上部图片
        mImageView = AppCompatImageView(mContext).apply {
            id = generateViewId()
            setImageResource(mImgRes)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            // 图标选择颜色转变
            if (mImgTintSelectedColor != Color.TRANSPARENT && mImgTintNormalColor != Color.TRANSPARENT) {
                imageTintList = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_selected),
                        intArrayOf(-android.R.attr.state_selected)
                    ),
                    intArrayOf(mImgTintSelectedColor, mImgTintNormalColor)
                )
            }
        }
        addView(mImageView, LayoutParams(mImgSize, mImgSize).apply {
            topToTop = parentId
            startToStart = parentId
            endToEnd = parentId
            if (mImgTopMargin != 0) {
                topMargin = mImgTopMargin
            }
        })

        // 创建下部文字
        mTextView = AppCompatCheckedTextView(mContext).apply {
            id = generateViewId()
            setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
            setTextColor(mTextNormalColor)
            gravity = mTextGravity
            textAlignment = TEXT_ALIGNMENT_GRAVITY
            text = if (mTextStr.isNullOrEmpty()) "" else mTextStr
            setLines(mTextLines)
            if (mTextMaxLines > mTextLines) {
                maxLines = mTextMaxLines
            }
        }
        addView(mTextView, LayoutParams(0, 0).apply {
            topToBottom = mImageView!!.id
            startToStart = parentId
            endToEnd = parentId
            bottomToBottom = parentId
            if (mTextTopMargin != 0) {
                topMargin = mTextTopMargin
            }
            marginStart = dp2px(2f)
            marginEnd = dp2px(2f)
        })

        // 创建中间图片遮罩
        if (mMaskCan) {
            mMaskView = AppCompatImageView(mContext).apply {
                setImageResource(mMaskRes)
                setBackgroundResource(mMaskBackgroundRes)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                visibility = GONE
            }
            addView(
                mMaskView,
                LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    private fun createViewLeft() {
        val parentId = this.id
        // 创建左边图片
        mImageView = AppCompatImageView(mContext).apply {
            id = generateViewId()
            setImageResource(mImgRes)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            // 图标选择颜色转变
            if (mImgTintSelectedColor != Color.TRANSPARENT && mImgTintNormalColor != Color.TRANSPARENT) {
                imageTintList = ColorStateList(
                    arrayOf(
                        intArrayOf(android.R.attr.state_selected),
                        intArrayOf(-android.R.attr.state_selected)
                    ),
                    intArrayOf(mImgTintSelectedColor, mImgTintNormalColor)
                )
            }
        }
        addView(mImageView, LayoutParams(mImgSize, mImgSize).apply {
            topToTop = parentId
            startToStart = parentId
            bottomToBottom = parentId
        })

        // 创建右边文字
        mTextView = AppCompatCheckedTextView(mContext).apply {
            id = generateViewId()
            setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
            setTextColor(mTextNormalColor)
            gravity = mTextGravity
            textAlignment = TEXT_ALIGNMENT_GRAVITY
            text = if (mTextStr.isNullOrEmpty()) "" else mTextStr
            setLines(mTextLines)
            if (mTextMaxLines > mTextLines) {
                maxLines = mTextMaxLines
            }
        }
        addView(mTextView, LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            topToTop = parentId
            startToEnd = mImageView!!.id
            endToEnd = parentId
            bottomToBottom = parentId

            if (mTextImgMargin != 0) {
                marginStart = mTextImgMargin
            }
            topMargin = dp2px(2f)
            bottomMargin = dp2px(2f)
            // 添加宽度约束
            constrainedWidth = true
        })

        // 创建中间图片遮罩
        if (mMaskCan) {
            mMaskView = AppCompatImageView(mContext).apply {
                setImageResource(mMaskRes)
                setBackgroundResource(mMaskBackgroundRes)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                visibility = GONE
            }
            addView(
                mMaskView,
                LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    /**
     * 设置控件选中状态
     */
    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        mImageView?.isSelected = selected
        mTextView?.isChecked = selected
        mTextView?.setTextColor(if (selected) mTextHoldColor else mTextNormalColor)
    }

    /**
     * 设置控件可用状态
     */
    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        mImageView?.isEnabled = enabled
        setMaskEnable(enabled)
    }

    /**
     * 设置遮罩可用状态
     */
    fun setMaskEnable(enabled: Boolean) {
        mMaskView?.let {
            if (enabled) {
                it.visibility = GONE
            } else {
                it.visibility = VISIBLE
            }
        }
    }

    /**
     * 设置控件可用状态，但不显示遮罩
     */
    fun setEnabledNoMask(enabled: Boolean) {
        super.setEnabled(enabled)
        mImageView?.isEnabled = enabled
        mMaskView?.visibility = GONE
    }

    private fun dp2px(dp: Float): Int {
        val scale = mContext.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun sp2px(sp: Float): Int {
        val scale = mContext.resources.displayMetrics.scaledDensity
        return (sp * scale + 0.5f).toInt()
    }

    var imgRes: Int
        get() = mImgRes
        set(value) {
            mImgRes = value
            mImageView?.setImageResource(value)
        }

    var textStr: String?
        get() = mTextStr
        set(value) {
            mTextStr = value
            mTextView?.text = mTextStr
        }

    var maskRes: Int
        get() = mMaskRes
        set(value) {
            mMaskRes = value
            mMaskView?.setImageResource(value)
        }

    var maskBackgroundRes: Int
        get() = mMaskBackgroundRes
        set(value) {
            mMaskBackgroundRes = value
            mMaskView?.setBackgroundResource(value)
        }

    private var mImgAt: ImgAt
    private var mImgRes: Int
    private var mImgSize: Int
    private var mImgTintSelectedColor = Color.TRANSPARENT
    private var mImgTintNormalColor = Color.TRANSPARENT
    private var mImgTopMargin: Int

    private var mTextSize: Float
    private var mTextStr: String?
    private var mTextNormalColor = Color.DKGRAY
    private var mTextHoldColor = Color.WHITE
    private var mTextLines: Int
    private var mTextMaxLines: Int
    private var mTextGravity: Int
    private var mTextTopMargin: Int
    private var mTextImgMargin: Int

    private var mMaskRes: Int
    private var mMaskBackgroundRes: Int
    private var mMaskCan: Boolean

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ImageTextView).apply {
            mImgAt = when (getInt(R.styleable.ImageTextView_itv_imgAt, 1)) {
                0 -> ImgAt.LEFT
                1 -> ImgAt.TOP
                else -> ImgAt.TOP
            }
            mImgRes = getResourceId(R.styleable.ImageTextView_itv_imgRes, 0)
            mImgSize = getDimensionPixelOffset(R.styleable.ImageTextView_itv_imgSize, dp2px(24f))
            mImgTintSelectedColor =
                getColor(R.styleable.ImageTextView_itv_imgTintSelectedColor, Color.TRANSPARENT)
            mImgTintNormalColor =
                getColor(R.styleable.ImageTextView_itv_imgTintNormalColor, Color.TRANSPARENT)
            mImgTopMargin = getDimensionPixelOffset(R.styleable.ImageTextView_itv_imgTopMargin, 0)

            mTextSize = getDimension(R.styleable.ImageTextView_itv_textSize, sp2px(14f).toFloat())
            mTextStr = getString(R.styleable.ImageTextView_itv_textStr)
            mTextNormalColor =
                getColor(R.styleable.ImageTextView_itv_textNormalColor, mTextNormalColor)
            mTextHoldColor = getColor(R.styleable.ImageTextView_itv_textHoldColor, mTextHoldColor)
            mTextLines = getInteger(R.styleable.ImageTextView_itv_textLines, 1)
            mTextMaxLines = getInteger(R.styleable.ImageTextView_itv_textMaxLines, 1)
            mTextGravity = when (getInt(R.styleable.ImageTextView_itv_textGravity, 0)) {
                1 -> Gravity.CENTER_HORIZONTAL
                2 -> Gravity.CENTER_VERTICAL
                else -> Gravity.CENTER
            }
            mTextTopMargin = getDimensionPixelOffset(R.styleable.ImageTextView_itv_textTopMargin, 0)
            mTextImgMargin = getDimensionPixelOffset(R.styleable.ImageTextView_itv_textImgMargin, 0)

            mMaskRes = getResourceId(R.styleable.ImageTextView_itv_maskRes, 0)
            mMaskBackgroundRes = getResourceId(R.styleable.ImageTextView_itv_maskBackgroundRes, 0)
            mMaskCan = getBoolean(R.styleable.ImageTextView_itv_maskCan, false)
            recycle()
        }
        createView()
    }

    enum class ImgAt(var value: Int) {
        LEFT(0),
        TOP(1)
    }

    private fun getImgAtValue(type: ImgAt) = when (type) {
        ImgAt.LEFT -> 0
        ImgAt.TOP -> 1
    }


}