package com.starriddle.starter.java.designPattern.creational.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 饿汉式创建单例模式
 *
 * @author CYL
 * @date 2019-11-09
 */
public class HungrySingleton implements Serializable {

    private static HungrySingleton singleton = new HungrySingleton();

    /**
     * 构造函数私有化，禁止外部访问
     */
    private HungrySingleton() {}

    /**
     * 返回单例对象
     * @return
     */
    public static HungrySingleton getSingleton() {
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

        HungrySingleton singleton = HungrySingleton.getSingleton();
        HungrySingleton singleton1 = HungrySingleton.getSingleton();
        System.out.println(singleton == singleton1);

        // 反射攻击：易受攻击，无法防御
        try {
            Constructor<HungrySingleton> constructor = HungrySingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);

            HungrySingleton singleton2 = constructor.newInstance();
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
        // 添加 readResolve() 方法后，反序列化返回同一个对象，攻击无效
        try {
            byte[] serialize = SerializationUtils.serialize(singleton);
            HungrySingleton singleton2 = SerializationUtils.deserialize(serialize);
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
