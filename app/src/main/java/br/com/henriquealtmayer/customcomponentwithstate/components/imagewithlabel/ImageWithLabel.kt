package br.com.henriquealtmayer.customcomponentwithstate.components.imagewithlabel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import br.com.henriquealtmayer.customcomponentwithstate.R
import br.com.henriquealtmayer.customcomponentwithstate.commons.handleOptional
import kotlinx.android.synthetic.main.image_with_label.view.*

class ImageWithLabel @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var text: String? = null
        set(value) {
            field = value
            tv_image_with_label.text = text
        }

    private var image: Int? = null
        set(value) {
            field = value
            value?.let {
                iv_image_with_label.setImageResource(it)
            }
        }

    val isSelected = MutableLiveData(false)
    var selectedCallbackAdapter: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.image_with_label, this, true)

        initializeComponents()

        initializeObservers()
    }

    private fun initializeComponents() {
        initializeComponentsWithAttrs()

        initializeOnClickListener()
    }

    private fun initializeComponentsWithAttrs() {
        attrs?.let {
            context.obtainStyledAttributes(
                attrs,
                R.styleable.ImageWithLabel, 0, 0
            ).apply {
                text = getString(R.styleable.ImageWithLabel_text)
                image = getResourceId(R.styleable.ImageWithLabel_image, 0)

                recycle()
            }
        }
    }

    private fun initializeOnClickListener() {
        setOnClickListener {
            isSelected.value = isSelected.value?.not()

            selectedCallbackAdapter?.invoke()
        }
    }

    private fun initializeObservers() {
        val lifecycleOwner = (context as? LifecycleOwner?) ?: return

        isSelected.observe(lifecycleOwner, {
            getImageWithLabelColor().let { color ->
                tv_image_with_label.setTextColor(color)
                iv_image_with_label.setColorFilter(color)
            }
        })
    }

    private fun getImageWithLabelColor() = ContextCompat.getColor(
        context,
        getImageWithLabelIdColor(isSelected.value.handleOptional())
    )

}

@VisibleForTesting
fun getImageWithLabelIdColor(selectedValue: Boolean) =
    if (selectedValue) {
        R.color.image_with_label_selected
    } else {
        R.color.image_with_label_unselected
    }