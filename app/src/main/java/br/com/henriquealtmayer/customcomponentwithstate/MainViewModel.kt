package br.com.henriquealtmayer.customcomponentwithstate

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriquealtmayer.customcomponentwithstate.commons.handleOptional

open class MainViewModel : ViewModel() {

    // Items
    val isHealthySelected = MutableLiveData(false)
    val isFastFoodSelected = MutableLiveData(false)

    private val itemsList = listOf(
        isHealthySelected,
        isFastFoodSelected
    )

    // Show Selected Values Amount Message
    @VisibleForTesting
    var selectedValuesMessagePrefix: String? = null

    fun initViewModel(
        selectedValuesMessagePrefix: String
    ) {
        this.selectedValuesMessagePrefix = selectedValuesMessagePrefix
    }

    private val mShowSelectedValuesAmountMessage = MutableLiveData<String>()
    val showSelectedValuesAmountMessage: LiveData<String> = mShowSelectedValuesAmountMessage
    fun showSelectedValuesAmountMessage() {
        mShowSelectedValuesAmountMessage.value = getSelectedValuesMessage()
    }

    @VisibleForTesting
    fun getSelectedValuesMessage() =
        "${selectedValuesMessagePrefix.handleOptional()} ${getSelectedValuesAmount()}"


    @VisibleForTesting
    fun getSelectedValuesAmount() = itemsList.filter { it.value.handleOptional() }.count()

}