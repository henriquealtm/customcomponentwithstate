package br.com.henriquealtmayer.customcomponentwithstate.components.imagewithlabel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

    val selected = MutableLiveData(false)
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
            selected.value = selected.value?.not()

            selectedCallbackAdapter?.invoke()
        }
    }

    private fun initializeObservers() {
        (context as? LifecycleOwner?)?.let { lifecycleOwner ->
            selected.observe(lifecycleOwner, Observer {
                val color = ContextCompat.getColor(
                    context, if (selected.value.handleOptional()) {
                        R.color.image_with_label_selected
                    } else {
                        R.color.image_with_label_unselected
                    }
                )

                tv_image_with_label.setTextColor(color)
                iv_image_with_label.setColorFilter(color)
            })
        }
    }

}