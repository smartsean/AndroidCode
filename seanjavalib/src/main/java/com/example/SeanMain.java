package com.example;


import io.reactivex.Observable;
import io.reactivex.functions.Action;
import sun.rmi.runtime.Log;

public class SeanMain {

    private static final String TAG = "SeanMain";

    public static void main(String[] args) {
        String[] names = {"action0","action1","action2"};

        Observable.fromArray(names).subscribe(new Action(String));
        Observable.fromArray(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.d(tag, name);
                    }
                });
    }
}
