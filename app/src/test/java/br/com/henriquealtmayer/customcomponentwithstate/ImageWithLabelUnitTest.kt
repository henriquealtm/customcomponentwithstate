package br.com.henriquealtmayer.customcomponentwithstate

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.henriquealtmayer.customcomponentwithstate.components.imagewithlabel.getImageWithLabelIdColor
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ImageWithLabelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `verify if getImageWithLabelIdColor() returns R_color_image_with_label_selected if selected param is true`() {
        Assert.assertEquals(getImageWithLabelIdColor(true), R.color.image_with_label_selected)
    }

    @Test
    fun `verify if getImageWithLabelIdColor() returns R_color_image_with_label_unselected if selected param is false`() {
        Assert.assertEquals(getImageWithLabelIdColor(false), R.color.image_with_label_unselected)
    }

}