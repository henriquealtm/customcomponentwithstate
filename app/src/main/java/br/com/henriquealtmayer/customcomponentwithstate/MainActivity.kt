package br.com.henriquealtmayer.customcomponentwithstate

import androidx.activity.viewModels
import br.com.henriquealtmayer.customcomponentwithstate.commons.BaseActivity
import br.com.henriquealtmayer.customcomponentwithstate.commons.showShortToast
import br.com.henriquealtmayer.customcomponentwithstate.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()

    override val loadVm: (ActivityMainBinding) -> Unit = { binding ->
        binding.vm = mainViewModel
    }

    override fun initializeViewModels() {
        mainViewModel.initViewModel(getString(R.string.show_selected_values_prefix))

        initializeShowSelectedValuesAmountMessage()
    }

    private fun initializeShowSelectedValuesAmountMessage() {
        mainViewModel.showSelectedValuesAmountMessage.observe(this) { message ->
            message?.let { showShortToast(message) }
        }
    }

}