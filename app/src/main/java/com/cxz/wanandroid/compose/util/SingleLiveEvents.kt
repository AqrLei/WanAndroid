package com.cxz.wanandroid.compose.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * created by AqrLei on 2022/1/18
 */
class SingleLiveEvents<T> : MutableLiveData<List<T>>() {
    private val pending = AtomicBoolean(false)
    private val eventList = mutableListOf<List<T>>()


    override fun observe(owner: LifecycleOwner, observer: Observer<in List<T>>) {
        super.observe(owner) {
            if (pending.compareAndSet(true, false)) {
                eventList.clear()
                observer.onChanged(it)
            }
        }
    }

    override fun setValue(value: List<T>?) {
        pending.set(true)
        value?.let {
            eventList.add(it)
        }
        val list = eventList.flatten()
        super.setValue(list)
    }
}