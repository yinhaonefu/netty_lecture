package com.shengsiyuan.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by yinhao on 17/12/2.
 */
public class AtomicUpdaterTest {
    public static void main(String[] args) {
        Person person = new Person();

//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                try {
//                    Thread.sleep(20);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(person.age++);
//            }).start();
//        }

        System.out.println("----");
        //线程安全的原子更新，底层采用自旋锁的方式保证原子更新
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(Person.class,"age");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
            }).start();
        }

    }
}

class Person{
    //必须是volatile int类型才能被AtomicIntegerFieldUpdater原子更新
    volatile int age = 1;
}
