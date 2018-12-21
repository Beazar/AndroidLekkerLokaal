package com.example.sanderbeazar.sportinaalst.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.sanderbeazar.sportinaalst.base.InjectedViewModel
import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import com.example.sanderbeazar.sportinaalst.network.SportclubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.orhanobut.logger.Logger
import javax.inject.Inject

class SportclubViewmodel: InjectedViewModel() {

    private val sportclubs = MutableLiveData<List<Sportclub>>()
    @SuppressLint("NewApi")

    @Inject
    lateinit var sportclubApi: SportclubApi

    /**
     * Indicates whether the loading view should be displayed.
     */
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Represents a disposable resources
     */
    private var subscription: Disposable

    init{
        subscription = sportclubApi.getAllSportclubs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveSportclubStart() }
                .doOnTerminate { onRetrieveSportclubFinish() }
                .subscribe(
                        { result -> onRetrieveSportclubSuccess(result) },
                        { error -> onRetrieveSportclubError(error) }
                )
        Log.d("testpurp",sportclubApi.toString())

    }

    private fun onRetrieveSportclubError(error: Throwable) {
        //Currently requests fail silently, which isn't great for the user.
        //It would be better to show a Toast, or maybe make a TextView visible with the error message.
        Logger.e(error.message!!)
    }

    @SuppressLint("NewApi")
    private fun onRetrieveSportclubSuccess(result: List<Sportclub>) {
        var filter = result.filter { r -> r.naam!!.contains("test",true) }
        sportclubs.value = result - filter//result.toList()

        Logger.i(result.toString())
    }

    private fun onRetrieveSportclubFinish() {
        Logger.i("Finished retrieving sportclub info")
        loadingVisibility.value = false
    }

    private fun onRetrieveSportclubStart() {
        Logger.i("Started retrieving resto info")
        loadingVisibility.value = true
    }

    fun getSportclubs(): MutableLiveData<List<Sportclub>> {
        Log.d("testpurp", sportclubs.toString())
        return sportclubs
    }

}