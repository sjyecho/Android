package com.example.androidthread;

public class ThreadDemo {

    static class MyThread extends Thread {
        private int ticket = 10;

        public static void main(String[] args) {
            MyThread mt = new MyThread();
            new Thread(mt, "线程1").start();
            new Thread(mt, "线程2").start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    if (this.ticket > 0) {
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + "卖票----->" + (this.ticket--));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
