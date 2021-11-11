package com.orbitalsonic.rxjavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    val tag = "RxJavaTestingTag"
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskObservable:Observable<Task> = Observable
            .fromIterable(DataSource.getTaskData())
            .filter { task ->
                Log.i(tag, "test: ${Thread.currentThread().name}")
                Thread.sleep(3000)
                false
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        taskObservable.subscribe(object :Observer<Task>{
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
                Log.i(tag,"onSubscribe: Called")
            }

            override fun onNext(t: Task) {
                Log.i(tag,"onNext: ${Thread.currentThread().name}")
                Log.i(tag,t.description)
            }

            override fun onError(e: Throwable) {
                Log.i(tag,"onError: $e")
            }

            override fun onComplete() {
                Log.i(tag,"onComplete: Called")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}