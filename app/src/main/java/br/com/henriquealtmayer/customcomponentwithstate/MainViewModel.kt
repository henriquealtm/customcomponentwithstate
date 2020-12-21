package br.com.henriquealtmayer.customcomponentwithstate

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriquealtmayer.customcomponentwithstate.commons.corona
import br.com.henriquealtmayer.customcomponentwithstate.commons.eisenbahn
import br.com.henriquealtmayer.customcomponentwithstate.commons.handleOptional
import br.com.henriquealtmayer.customcomponentwithstate.commons.heineken

open class MainViewModel : ViewModel() {

    // Food type items
    val isHealthySelected = MutableLiveData(false)
    val isFastFoodSelected = MutableLiveData(true)

    private val foodTypeList = listOf(
        isHealthySelected,
        isFastFoodSelected
    )

    val beerList = listOf(corona, heineken, eisenbahn)
    val beerSelectedItem = MutableLiveData<String>()

    // Show Selected Values
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
        "${selectedValuesMessagePrefix.handleOptional()} ${getSelectedValuesAmount()}, ${beerSelectedItem.value.handleOptional()}"

    @VisibleForTesting
    fun getSelectedValuesAmount() = foodTypeList.filter { it.value.handleOptional() }.count()

}