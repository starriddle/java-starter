package com.starriddle.starter.java.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starriddle.starter.java.entity.Person;

/**
 * description
 *
 * @author CYL
 * @date 2019-01-08
 */
public class JsonUtil {

    public static void main(String[] args) {
        testObject();
        testList();
    }

    private static void testList() {

        Gson gs = new Gson();

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person ps = new Person();
            ps.setId(i);
            ps.setName("我是第"+i+"个");
            ps.setAge(i+10);
            persons.add(ps);
        }

        // 把List转为JSON格式的字符串
        String listStr = gs.toJson(persons);
        System.out.println("把list转为JSON格式的字符串\n\t"+listStr);

        // 把JSON格式的字符串转为List
        List<Person> jsonListObject = gs.fromJson(listStr, new TypeToken<List<Person>>(){}.getType());
        System.out.println("把JSON格式的字符串转为List");
        for (Person p : jsonListObject) {
            System.out.println("\t"+p.toString());
        }
    }

    private static void testObject() {

        Gson gs = new Gson();

        Person person = new Person();
        person.setId(1);
        person.setName("我是酱油");
        person.setAge(24);

        // 把对象转为JSON格式的字符串
        String objectStr = gs.toJson(person);
        System.out.println("把对象转为JSON格式的字符串\n\t" + objectStr);

        // 把JSON字符串转为对象
        Person jsonObject = gs.fromJson(objectStr, Person.class);
        System.out.println("把JSON字符串转为对象\n\t"+jsonObject.toString());
    }
}
