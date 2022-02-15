package com.cxz.wanandroid.compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class LaunchExt<T>() {
    internal lateinit var api: (suspend () -> Flow<HttpResult<T?>>)
        private set
    internal lateinit var onSuccess: ((T) -> Unit)
        private set
    internal var onBeforeCall: (() -> Unit)? = null
        private set
    internal var onFailure: ((msg: String?) -> Unit)? = null
        private set
    internal var onComplete: (() -> Unit)? = null
        private set

    fun api(block: (suspend () -> Flow<HttpResult<T?>>)) {
        this.api = block
    }

    fun onBeforeCall(block: () -> Unit) {
        this.onBeforeCall = block
    }

    fun onSuccess(block: ((T) -> Unit)) {
        this.onSuccess = block
    }

    fun onFailure(block: ((msg: String?) -> Unit)) {
        this.onFailure = block
    }

    fun onComplete(block: (() -> Unit)) {
        this.onComplete = block
    }
}