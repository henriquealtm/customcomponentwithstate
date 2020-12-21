package br.com.henriquealtmayer.customcomponentwithstate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.henriquealtmayer.customcomponentwithstate.commons.handleOptional
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainVm: MainViewModel

    @Before
    @Throws(Exception::class)
    fun prepare() {
        mainVm = MainViewModel()
    }

    // Items
    @Test
    fun `verify if isHealthySelected is false when creating MainViewModel`() {
        assertFalse(mainVm.isHealthySelected.value.handleOptional())
    }

    @Test
    fun `verify if isFastFoodSelected is true when creating MainViewModel`() {
        assertTrue(mainVm.isFastFoodSelected.value.handleOptional())
    }

    // Message Prefix
    @Test
    fun `verify if selectedValuesMessagePrefix is null when creating MainViewModel`() {
        assertNull(mainVm.selectedValuesMessagePrefix)
    }

    @Test
    fun `verify if selectedValuesMessagePrefix, after calling initViewModel(), is equal to initViewModel() param`() {
        mainVm.run {
            val prefixParam = "Prefix param"

            initViewModel(prefixParam)

            assertEquals(prefixParam, selectedValuesMessagePrefix)
        }
    }

    @Test
    fun `verify if showSelectedValuesAmountMessage is null when creating MainViewModel`() {
        assertNull(mainVm.showSelectedValuesAmountMessage.value)
    }

    @Test
    fun `verify if getSelectedValuesAmount() returns 0 when both selectableItems are false`() {
        mainVm.run {
            isHealthySelected.value = false
            isFastFoodSelected.value = false

            assertEquals(0, getSelectedValuesAmount())
        }
    }

    @Test
    fun `verify if getSelectedValuesAmount() returns 2 when both selectableItems are true`() {
        mainVm.run {
            isHealthySelected.value = true
            isFastFoodSelected.value = true

            assertEquals(2, getSelectedValuesAmount())
        }
    }

    @Test
    fun `verify if getSelectedValuesMessage() returns selectedValuesMessagePrefix + getSelectedValuesAmount()`() {
        mainVm.run {
            val prefixParam = "Prefix param"
            initViewModel(prefixParam)

            isHealthySelected.value = true
            isFastFoodSelected.value = true

            // Selected beer
            val beer = "Corona"
            beerSelectedItem.value = beer


            assertEquals("$prefixParam 2, $beer", getSelectedValuesMessage())
        }
    }

    @Test
    fun `verify if showSelectedValuesAmountMessage, after calling showSelectedValuesAmountMessage(), is equal to getSelectedValuesMessage() returned value`() {
        mainVm.run {
            val prefixParam = "Prefix param"
            initViewModel(prefixParam)

            // getSelectedValuesAmount() will return 2
            isHealthySelected.value = true
            isFastFoodSelected.value = true

            // Selected beer
            val beer = "Corona"
            beerSelectedItem.value = beer

            showSelectedValuesAmountMessage()

            assertEquals(
                "$prefixParam 2, $beer",
                showSelectedValuesAmountMessage.value.handleOptional()
            )
        }
    }

}