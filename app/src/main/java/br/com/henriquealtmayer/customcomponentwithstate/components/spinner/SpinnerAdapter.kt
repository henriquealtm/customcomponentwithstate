package br.com.henriquealtmayer.customcomponentwithstate.components.spinner

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

// String Adapter
@BindingAdapter("app:items")
fun AppCompatSpinner.setItems(
    newList: List<String>
) {
    val listAdapter = ArrayAdapter(context, R.layout.simple_spinner_item, newList)

    listAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
    this.adapter = listAdapter
}

// String Selected Item
@BindingAdapter("app:item_selected")
fun AppCompatSpinner.setItemSelected(
    value: String?
) {
    value?.let {
        @Suppress("UNCHECKED_CAST")
        val pos = (adapter as ArrayAdapter<String>).getPosition(value)
        setSelection(pos, true)
    }
}

@InverseBindingAdapter(attribute = "app:item_selected", event = "app:item_selectedAttrChanged")
fun AppCompatSpinner.getItemSelected(): String = selectedItem.toString()

@BindingAdapter("app:item_selectedAttrChanged")
fun AppCompatSpinner.setItemSelectedListeners(
    attrChange: InverseBindingListener
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            attrChange.onChange()
        }
    }
}
