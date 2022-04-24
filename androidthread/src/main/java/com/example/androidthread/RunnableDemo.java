package com.example.androidthread;

public class RunnableDemo {

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        new Thread(mt,"线程1").start();
        new Thread(mt,"线程2").start();
    }

    static class MyThread implements Runnable {
        private int ticket = 10;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runnable ticket = " + ticket-- + "---" + Thread.currentThread().getName());
                if (ticket <= 0) {
                    break;
                }
            }
        }
    }
}
