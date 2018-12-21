package com.example.sanderbeazar.sportinaalst.base

import android.arch.lifecycle.ViewModel
import com.example.sanderbeazar.sportinaalst.injection.component.DaggerViewModelComponent
import com.example.sanderbeazar.sportinaalst.injection.component.ViewModelComponent
import com.example.sanderbeazar.sportinaalst.injection.module.NetworkModule
import com.example.sanderbeazar.sportinaalst.ui.SportclubViewmodel

abstract class InjectedViewModel: ViewModel() {

    /**
     * A ViewModelComponent is required to do the actual injecting.
     * Every Component has a default builder to which you can add all
     * modules that will be needed for the injection.
     */
    private val injector: ViewModelComponent = DaggerViewModelComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    /**
     * Perform the injection when the ViewModel is created
     */
    init {
        inject()
    }

    /**
     * Injects the required dependencies.
     * We need the 'when(this)' construct for each new ViewModel as the 'this' reference should
     * refer to an instance of that specific ViewModel.
     * Just injecting into a generic InjectedViewModel is not specific enough for Dagger.
     */
    private fun inject() {
        when (this) {
            is SportclubViewmodel -> injector.inject(this)
        }
    }

}