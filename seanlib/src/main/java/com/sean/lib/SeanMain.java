package com.sean.lib;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.functions.Action;


public class SeanMain {

    private static final String TAG = "SeanMain";

    public static void main(String[] args) {
        String[] names = {"action0", "action1", "action2"};

        Observable.just("1", "3").subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
