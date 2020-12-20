package br.com.henriquealtmayer.customcomponentwithstate.components

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("app:is_selected")
fun ImageWithLabel.setIsSelected(
    newValue: Boolean
) {
    if (selected.value != newValue) {
        selected.value = newValue
    }
}

@InverseBindingAdapter(attribute = "app:is_selected", event = "app:is_selectedAttrChanged")
fun ImageWithLabel.getSelected(): Boolean? = selected.value

@BindingAdapter("app:is_selectedAttrChanged")
fun ImageWithLabel.setListeners(
    attrChange: InverseBindingListener
) {
    selectedCallbackAdapter = {
        attrChange.onChange()
    }
}