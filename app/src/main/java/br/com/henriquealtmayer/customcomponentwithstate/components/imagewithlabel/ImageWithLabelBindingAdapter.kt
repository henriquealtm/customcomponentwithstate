package br.com.henriquealtmayer.customcomponentwithstate.components.imagewithlabel

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("app:is_selected")
fun ImageWithLabel.setIsSelected(
    newValue: Boolean
) {
    if (isSelected.value != newValue) {
        isSelected.value = newValue
    }
}

@InverseBindingAdapter(attribute = "app:is_selected", event = "app:is_selectedAttrChanged")
fun ImageWithLabel.getSelected(): Boolean? = isSelected.value

@BindingAdapter("app:is_selectedAttrChanged")
fun ImageWithLabel.setListeners(
    attrChange: InverseBindingListener
) {
    selectedCallbackAdapter = {
        attrChange.onChange()
    }
}