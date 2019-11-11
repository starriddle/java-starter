package com.starriddle.starter.java.designPattern.creational.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 懒汉式创建单例模式
 * 双重锁检查
 *
 * 将实例化的过程抽出来, 放在一个 sychronized/lock 代码块中执行,
 * 同时在代码块内外均对 singleton 进行检查，实现懒加载与并发安全
 *
 * @author CYL
 * @date 2019-11-09
 */
public class LazyDoubleCheckSingleton implements Serializable {

    /**
     * 使用volatile进行修饰，禁止指令重排
     */
    private static volatile LazyDoubleCheckSingleton singleton = null;

    private LazyDoubleCheckSingleton() {}

    public static LazyDoubleCheckSingleton getSingleton() {
        if (singleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new LazyDoubleCheckSingleton();
                }
            }
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

        LazyDoubleCheckSingleton singleton = LazyDoubleCheckSingleton.getSingleton();
        LazyDoubleCheckSingleton singleton1 = LazyDoubleCheckSingleton.getSingleton();
        System.out.println(singleton == singleton1);

        // 反射攻击：易受攻击，无法防御
        try {
            Constructor<LazyDoubleCheckSingleton> constructor = LazyDoubleCheckSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            LazyDoubleCheckSingleton singleton2 = constructor.newInstance();
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
            LazyDoubleCheckSingleton singleton2 = SerializationUtils.deserialize(serialize);
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
