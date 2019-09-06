package com.muy.muysamples.http

import android.net.NetworkInfo

const val ERROR = -1
const val CONNECT_SUCCESS = 0
const val GET_INPUT_STREAM_SUCCESS = 1
const val PROCESS_INPUT_STREAM_IN_PROGRESS = 2
const val PROCESS_INPUT_STREAM_SUCCESS = 3

interface RequestCallback<T> {

    fun updateFromRequest(resutl: T?)

    fun getActiveNetworkInfo(): NetworkInfo

    fun onProgressUpdate(progressCode: Int, percentComplete: Int)

    fun finishRequest()
}