package com.starriddle.starter.java.designPattern.creational.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Constructor;

/**
 * 通过枚举创建单例对象
 *
 * @author CYL
 * @date 2019-11-09
 */
public enum  EnumSingleton {

    /**
     * 单例对象
     */
    INSTANCE;

    public static EnumSingleton getSingleton() {
        return INSTANCE;
    }

    public static void main(String[] args) {

        EnumSingleton singleton = EnumSingleton.getSingleton();
        System.out.println(singleton.name() + ":" + singleton.ordinal());
        EnumSingleton singleton1 = EnumSingleton.getSingleton();
        System.out.println(singleton == singleton1);

        // 反射攻击：无效，只有一个编译期构造器，无法通过反射调用
        try {
            // Enum 没有空构造器，所以 NoSuchMethodException
            // Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();

            // Enum 存在 protect 两参 的唯一构造器，为编译期构造器，无法调用
            Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            // 调用构造器创建对象时抛出异常（校验clazz.modifier未通过）
            EnumSingleton singleton2 = constructor.newInstance("INSTANCE", 0);
            if(singleton == singleton2) {
                System.out.println("反射攻击失败！");
            } else {
                System.out.println("反射攻击成功！");
            }
        } catch (Exception e) {
            System.out.println("反射攻击捕获: " + e);
            e.printStackTrace();
        }

        // 反序列化攻击：无效，反序列化后仍为同一个对象
        try {
            byte[] serialize = SerializationUtils.serialize(singleton);
            EnumSingleton singleton2 = SerializationUtils.deserialize(serialize);
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
