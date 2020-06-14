package com.muy.muysamples.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.muy.muysamples.R

/**
 * Created by James on 2020/5/24.
 * Desc:
 */
class RxjavaSampleActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

//        var single = Single.just(1)
//        val singleString = single.map { t -> t.toString() }
//        singleString.subscribe(object : SingleObserver<String> {
//            override fun onSuccess(t: String?) {
//
//            }
//
//            override fun onSubscribe(d: Disposable?) {
//
//            }
//
//            override fun onError(e: Throwable?) {
//
//            }
//
//        })
//
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .subscribe(object : Observer<Long>{
//                    override fun onComplete() {
//                    }
//
//                    override fun onSubscribe(d: Disposable?) {
//                    }
//
//                    override fun onNext(t: Long?) {
//                    }
//
//                    override fun onError(e: Throwable?) {
//                    }
//                })


    }
}