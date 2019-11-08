package com.bwie.retrofitdesigndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        rxjava();
    }

    /**
     * rxjava学习
     */
    private void rxjava() {

        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);//第一个事件
                emitter.onNext(2);//第一个事件
                emitter.onError(new NullPointerException());
                emitter.onNext(3);//第一个事件
                emitter.onNext(4);//第一个事件
                emitter.onNext(5);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
                emitter.onNext(6);//第一个事件
            }
        });

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                d.isDisposed();
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

        Observable observable1 = Observable.just(1,2,3,4);
        Observer observer1 = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable1.subscribe(observer1);

        String[] strings = new String[]{"1","2"};
        Observable observable2 = Observable.fromArray(strings);

    }
}
