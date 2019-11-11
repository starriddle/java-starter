package com.starriddle.starter.java.designPattern.creational.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 懒汉式创建单例模式
 * 采用静态内部类的解决方案, 实现单例模式的懒加载
 *
 * 1. 内部类只在需要的时候才会被类加载器加载，实现了懒加载，
 *    即在需要的时候才实例化出一个 LazyStaticSingletonHolder 类对象，而且是唯一的一个
 *
 * 2. 由于 singleton 是 static final 类型，保证了内存中只有这样一个实例存在，而且只能被赋值一次，
 *    从而保证了线程安全性（即使在高并发的情况下多个线程同时访问 getSingleton() 方法也能够保证实例的唯一性）
 *
 * @author CYL
 * @date 2019-11-09
 */
public class LazyStaticSingleton implements Serializable {

    /**
     * 静态内部类
     */
    private static class LazyStaticSingletonHolder {
        private static LazyStaticSingleton singleton = new LazyStaticSingleton();
    }

    private LazyStaticSingleton() {}

    public static LazyStaticSingleton getSingleton(){
        return LazyStaticSingletonHolder.singleton;
    }

    /**
     * 防止 反序列化攻击
     * @return
     */
    private Object readResolve() {
        return LazyStaticSingletonHolder.singleton;
    }

    public static void main(String[] args) {

        LazyStaticSingleton singleton = LazyStaticSingleton.getSingleton();
        LazyStaticSingleton singleton1 = LazyStaticSingleton.getSingleton();
        System.out.println(singleton == singleton1);

        // 反射攻击：易受攻击，无法防御
        try {
            Constructor<LazyStaticSingleton> constructor = LazyStaticSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            LazyStaticSingleton singleton2 = constructor.newInstance();
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
            LazyStaticSingleton singleton2 = SerializationUtils.deserialize(serialize);
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
