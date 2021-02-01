package com.muy.muysamples.http

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.muy.muysamples.R
import kotlinx.android.synthetic.main.activity_http_sample.*
import java.io.*
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val URL_GOOGLE = "http://www.android.com"
private const val TAG = "HttpSampleActivity"

class HttpSampleActivity : AppCompatActivity(), RequestCallback<String> {

    private var downloading = false
    private var callback: RequestCallback<String>? = null
    private var httpTask: HttpTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_sample)

        btn_request.setOnClickListener {
            if (!downloading) {
                downloading = true
                onLoadingChanged()
                startRequest()
            }

        }
    }

    private fun onLoadingChanged() {
        if (downloading) {
            scrollView2.visibility = View.INVISIBLE
            pb_request.visibility = View.VISIBLE
        } else {
            scrollView2.visibility = View.VISIBLE
            pb_request.visibility = View.INVISIBLE
        }
    }

    private fun startRequest() {
        callback?.also {
            httpTask = HttpTask(it).apply {
                execute(URL_GOOGLE)
            }
        }
    }

    private fun cancelRequest() {
        httpTask?.cancel(true)
    }

    override fun onResume() {
        super.onResume()
        callback = this
    }

    override fun onDestroy() {
        super.onDestroy()
        callback = null
    }

    override fun updateFromRequest(resutl: String?) {
        Log.d(TAG, "updateFromRequest")
        downloading = false
        onLoadingChanged()
        tv_content.text = resutl ?: "Network is unavailable"
    }

    override fun getActiveNetworkInfo(): NetworkInfo {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo!!
    }

    override fun onProgressUpdate(progressCode: Int, percentComplete: Int) {

    }

    override fun finishRequest() {
        Log.d(TAG, "finishRequest")
        downloading = false
        onLoadingChanged()
    }

    private class HttpTask(callback: RequestCallback<String>) : AsyncTask<String, Int, HttpTask.Result>() {

        private var callback: RequestCallback<String>? = null

        init {
            setCallback(callback)
        }

        internal fun setCallback(callback: RequestCallback<String>) {
            this.callback = callback
        }

        internal class Result {
            var resultValue: String? = null
            var exception: Exception? = null

            constructor(resultValue: String) {
                this.resultValue = resultValue
            }

            constructor(exception: Exception) {
                this.exception = exception
            }
        }

        override fun onPreExecute() {
            if (callback != null) {
                val networkInfo = callback?.getActiveNetworkInfo()
                if (networkInfo?.isConnected == false || networkInfo?.type != ConnectivityManager.TYPE_WIFI
                        && networkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                    callback?.updateFromRequest(null)
                    cancel(true)
                }
            }
        }

        override fun doInBackground(vararg params: String?): HttpTask.Result? {
            var result: Result? = null
            if (!isCancelled && params.isNotEmpty()) {
                val urlString = params[0]
                result = try {
                    val url = URL(urlString)
                    val resultString = downloadUrl(url)
                    if (resultString != null) {
                        Result(resultString)
                    } else {
                        throw IOException("No response received.")
                    }
                } catch (e: Exception) {
                    Result(e)
                }

            }
            return result
        }

        override fun onPostExecute(result: Result?) {
            callback?.apply {
                result?.exception?.also { exception ->
                    (exception.message)
                    return
                }
                result?.resultValue?.also { resultValue ->
                    updateFromRequest(resultValue)
                    return
                }
                finishRequest()
            }
        }

        @Throws(IOException::class)
        private fun downloadUrl(url: URL): String? {
            var connection: HttpsURLConnection? = null
            return try {
                connection = (url.openConnection() as? HttpsURLConnection)
                connection?.run {
                    // Timeout for reading InputStream arbitrarily set to 3000ms.
                    readTimeout = 3000
                    // Timeout for connection.connect() arbitrarily set to 3000ms.
                    connectTimeout = 3000
                    // For this use case, set HTTP method to GET.
                    requestMethod = "GET"
                    // Already true by default but setting just in case; needs to be true since this request
                    // is carrying an input (response) body.
                    doInput = true
                    // Open communications link (network traffic occurs here).
                    connect()
                    publishProgress(CONNECT_SUCCESS)
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        throw IOException("HTTP error code: $responseCode")
                    }
                    // Retrieve the response body as an InputStream.
                    publishProgress(GET_INPUT_STREAM_SUCCESS, 0)
                    inputStream?.let { stream ->
                        // Converts Stream to String with max length of 500.
                        readStream(stream, 1000)
                    }
                }
            } finally {
                // Close Stream and disconnect HTTPS connection.
                connection?.inputStream?.close()
                connection?.disconnect()
            }
        }

        /**
         * Converts the contents of an InputStream to a String.
         */
        @Throws(IOException::class, UnsupportedEncodingException::class)
        fun readStream(stream: InputStream, maxReadSize: Int): String? {
            val reader: Reader? = InputStreamReader(stream, "UTF-8")
            val rawBuffer = CharArray(maxReadSize)
            val buffer = StringBuffer()
            var readSize: Int
            var maxReadBytes = maxReadSize
            do {
                readSize = reader?.read(rawBuffer) ?: -1

                if (readSize > maxReadBytes) {
                    readSize = maxReadBytes
                }

                buffer.append(rawBuffer, 0, readSize)
                maxReadBytes -= readSize
            } while (readSize != 1 && maxReadBytes > 0)

            return buffer.toString()
        }
    }
}
