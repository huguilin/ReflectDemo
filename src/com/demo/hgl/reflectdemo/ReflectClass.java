package com.demo.hgl.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectClass {

    public static void main(String[] args) {
        Book book = new Book();
        book.setName("你好");
        Class c;
        try {
            c = Class.forName("com.demo.hgl.reflectdemo.Book");

            //获取name属性
            Field f = c.getDeclaredField("name");
            f.setAccessible(true);
            System.out.println(f.get(book));

            //获取有参构造函数
            Constructor cs = c.getDeclaredConstructor(String.class,String.class);
            cs.setAccessible(true);
            Object o = cs.newInstance("你好","hugl");
            Book book1 = (Book) o;
            System.out.println(book1.getAuthor());

            //获取无参构造函数
            Book book2 = (Book) c.newInstance();
            book2.setName("hh");
            book2.setAuthor("hgl");

            //获取declaredMethod函数
            Method m = c.getDeclaredMethod("declaredMethod",int.class);
            m.setAccessible(true);
            String s = (String) m.invoke(book1,2);
            System.out.println(s);

            //获取包名
            System.out.println(c.getPackage().getName());
            System.out.println(c.getName());

            //获取所有属性
            Field[] fields = c.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getName());
            }

            //静态变量
            fields[0].setAccessible(true);
            System.out.println(fields[0].get(null));

            //获取接口名
            System.out.println(c.getInterfaces()[0].getName());
            System.out.println(c.getSuperclass().getName());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
