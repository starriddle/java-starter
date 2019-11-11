package com.starriddle.starter.java.designPattern.creational.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 懒汉式创建单例模式
 * 使用 synchronize 对方法进行加锁
 *
 * @author CYL
 * @date 2019-11-09
 */
public class LazySynchronizeSingleton implements Serializable {

    /**
     * 是否已经调用过一次构造函数
     */
    private static boolean flag = false;

    private static LazySynchronizeSingleton singleton = null;

    private LazySynchronizeSingleton() {
        // flag 为线程间共享，进行加锁控制
        synchronized (LazySynchronizeSingleton.class) {
            // 构造器仅限调用一次，否则抛出异常，防止反射攻击
            if (flag) {
                throw new IllegalArgumentException("Cannot reflectively create objects");
            } else {
                flag = true;
            }
        }
    }

    public synchronized static LazySynchronizeSingleton getSingleton() {
        if (singleton == null) {
            singleton = new LazySynchronizeSingleton();
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

        LazySynchronizeSingleton singleton = LazySynchronizeSingleton.getSingleton();
        LazySynchronizeSingleton singleton1 = LazySynchronizeSingleton.getSingleton();
        System.out.println(singleton == singleton1);

        // 反射攻击：易受攻击，无法防御
        try {
            Constructor<LazySynchronizeSingleton> constructor = LazySynchronizeSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);

            // 反射调用构造器前，修改 flag 值，导致防御反射攻击无效

            // 获取 public 属性，此处 flag 为 private，所以 NoSuchFieldException
            // Field flagField = LazySynchronizeSingleton.class.getField("flag");

            Field flagField = LazySynchronizeSingleton.class.getDeclaredField("flag");
            flagField.setAccessible(true);
            flagField.set(singleton, false);

            LazySynchronizeSingleton singleton2 = constructor.newInstance();
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
            LazySynchronizeSingleton singleton2 = SerializationUtils.deserialize(serialize);
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
