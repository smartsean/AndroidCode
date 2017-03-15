package com.example;

/**
 * Created by sean on 2017/3/15.
 */

public class ThreadLocalTest {
    private static final String TAG = "SeanMain";
    private static ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();

    public static void main(String[] args){

        mBooleanThreadLocal.set(true);
        System.out.println("MAIN---->>"+mBooleanThreadLocal.get());

        new Thread("Thread#1"){
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                System.out.println("Thread#1---->>"+mBooleanThreadLocal.get());
            }
        }.start();
        new Thread("Thread#2"){
            @Override
            public void run() {
                System.out.println("Thread#2---->>"+mBooleanThreadLocal.get());
            }
        }.start();
    }
}
