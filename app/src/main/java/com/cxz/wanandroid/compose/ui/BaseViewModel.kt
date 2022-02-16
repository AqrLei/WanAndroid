package com.cxz.wanandroid.compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cxz.wanandroid.compose.data.http.HttpResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * created by AqrLei on 2022/2/15
 */
open class BaseViewModel : ViewModel() {

    fun <T> async(block: LaunchExt<T>.() -> Unit) {
        viewModelScope.async(block)
    }

    fun <T : Any> asyncPage(block: LaunchPageExt<T>.() -> Unit) {
        viewModelScope.asyncPage(block)
    }
}

fun <T> CoroutineScope.async(block: LaunchExt<T>.() -> Unit) {
    val launchExt = LaunchExt<T>().apply(block)
    launch {
        launchExt.onBeforeCall?.invoke()
        try {
            launchExt.api().collectLatest { response ->
                when (response) {
                    is HttpResult.Error -> launchExt.onFailure?.invoke(response.e.message)
                    is HttpResult.Success -> launchExt.onSuccess(response.result!!)
                }
            }

        } catch (e: Exception) {
            launchExt.onFailure?.invoke(e.message)
        } finally {
            launchExt.onComplete?.invoke()
        }
    }
}

fun <T : Any> CoroutineScope.asyncPage(block: LaunchPageExt<T>.() -> Unit) {
    val launchPageExt = LaunchPageExt<T>().apply(block)
    launch {
        launchPageExt.onBeforeCall?.invoke()
        try {
            val result = launchPageExt.api().cachedIn(this)
            launchPageExt.onSuccess(result)
        } catch (e: Exception) {
            launchPageExt.onFailure?.invoke(e.message)
        } finally {
            launchPageExt.onComplete?.invoke()
        }
    }
}


typealias OnBeforeCall = (() -> Unit)
typealias Api<T> = (suspend () -> Flow<HttpResult<T?>>)
typealias PageApi<T> = (suspend () -> Flow<PagingData<T>>)
typealias OnSuccess<T> = ((T) -> Unit)
typealias OnPageSuccess<T> = ((Flow<PagingData<T>>) -> Unit)
typealias OnFailure = ((msg: String?) -> Unit)
typealias OnComplete = (() -> Unit)


class LaunchExt<T>() {
    internal lateinit var api: Api<T>
        private set
    internal lateinit var onSuccess: OnSuccess<T>
        private set
    internal var onBeforeCall: OnBeforeCall? = null
        private set
    internal var onFailure: OnFailure? = null
        private set
    internal var onComplete: OnComplete? = null
        private set

    fun api(block: Api<T>) {
        this.api = block
    }

    fun onBeforeCall(block: OnBeforeCall) {
        this.onBeforeCall = block
    }

    fun onSuccess(block: OnSuccess<T>) {
        this.onSuccess = block
    }

    fun onFailure(block: OnFailure) {
        this.onFailure = block
    }

    fun onComplete(block: OnComplete) {
        this.onComplete = block
    }
}

class LaunchPageExt<T : Any>() {
    internal lateinit var api: PageApi<T>
        private set
    internal lateinit var onSuccess: OnPageSuccess<T>
        private set
    internal var onBeforeCall: OnBeforeCall? = null
        private set
    internal var onFailure: OnFailure? = null
        private set
    internal var onComplete: OnComplete? = null
        private set

    fun api(block: PageApi<T>) {
        this.api = block
    }

    fun onBeforeCall(block: OnBeforeCall) {
        this.onBeforeCall = block
    }

    fun onSuccess(block: OnPageSuccess<T>) {
        this.onSuccess = block
    }

    fun onFailure(block: OnFailure) {
        this.onFailure = block
    }

    fun onComplete(block: OnComplete) {
        this.onComplete = block
    }
}