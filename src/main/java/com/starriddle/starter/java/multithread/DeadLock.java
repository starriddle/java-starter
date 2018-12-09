package com.starriddle.starter.java.multithread;

/**
 * 死锁
 *
 * @author CYL
 * @date 2018-11-20
 */
public class DeadLock {

    private static Integer lock1 = 1;

    private static Integer lock2 = 2;

    public static void main(String[] args){

        new Thread(new Runnable() {
            @Override
            public void run() {
                sync1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sync2();
            }
        }).start();
    }

    public static void sync1(){
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()+":启动 sycn1() 开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":调用 sycn2() 开始");
            sync2();
            System.out.println(Thread.currentThread().getName()+":调用 sycn2() 结束");
            System.out.println(Thread.currentThread().getName()+":启动 sycn1() 结束");
        }
    }

    public static void sync2(){
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName()+":启动 sycn2() 开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":调用 sycn1() 开始");
            sync1();
            System.out.println(Thread.currentThread().getName()+":调用 sycn1() 结束");
            System.out.println(Thread.currentThread().getName()+":启动 sycn2() 结束");
        }
    }
}