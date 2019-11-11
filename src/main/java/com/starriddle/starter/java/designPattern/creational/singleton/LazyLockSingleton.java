package com.starriddle.starter.java.designPattern.creational.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 懒汉式创建单例模式
 * 使用 lock 对方法进行加锁
 *
 * @author CYL
 * @date 2019-11-09
 */
public class LazyLockSingleton implements Serializable {

    private static LazyLockSingleton singleton = null;

    private static Lock lock = new ReentrantLock();

    private LazyLockSingleton() {}

    public static LazyLockSingleton getSingleton() {
        try {
            lock.lock();
            if (singleton == null) {
                singleton = new LazyLockSingleton();
            }
        } finally {
            lock.unlock();
        }
        return singleton;
    }

    /**
     * 防止 反序列化攻击
     * @return
     */
    private Object readResolve() {
        return singleton;
    }

    public static void main(String[] args) {

        LazyLockSingleton singleton = LazyLockSingleton.getSingleton();
        LazyLockSingleton singleton1 = LazyLockSingleton.getSingleton();
        System.out.println(singleton == singleton1);

        // 反射攻击：易受攻击，无法防御
        try {
            // 获取 public 空构造器，单例模式的构造器都是 private，所以 NoSuchMethodException
            // Constructor<LazyLockSingleton> constructor = LazyLockSingleton.class.getConstructor();

            // 获取 空构造器，得到 private 空构造器
            Constructor<LazyLockSingleton> constructor = LazyLockSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            LazyLockSingleton singleton2 = constructor.newInstance();
            if(singleton == singleton2) {
                System.out.println("反射攻击失败！");
            } else {
                System.out.println("反射攻击成功！");
            }
        } catch (Exception e) {
            System.out.println("反射攻击捕获: " + e);
            e.printStackTrace();
        }

        // 反序列化攻击：易受攻击，容易防御
        try {
            byte[] serialize = SerializationUtils.serialize(singleton);
            LazyLockSingleton singleton2 = SerializationUtils.deserialize(serialize);
            if(singleton == singleton2) {
                System.out.println("反序列化攻击失败！");
            } else {
                System.out.println("反序列化攻击成功！");
            }
        } catch (Exception e) {
            System.out.println("反序列化攻击捕获: " + e);
            e.printStackTrace();
        }
    }
}
