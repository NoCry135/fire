package com.ca.fire.test.reflect;

import com.ca.fire.domain.bean.OrderMain;
import com.ca.fire.test.domain.Student;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestClass {


    /**
     * 获取对象的三种方式
     */
    @Test
    public void test01() {
        //1.直接获取对象
        OrderMain orderMain = new OrderMain();
        Class aClass = orderMain.getClass();
        System.out.println("aClass.getName() 全类名" + aClass.getName());

        //2.第二种方式

        Class<OrderMain> orderMainClass = OrderMain.class;
        System.out.println("aClass.hashCode  " + aClass.hashCode());
        System.out.println("orderMainClass.hashCode  " + orderMainClass.hashCode());

        System.out.println("aClass == orderMainClass  " + (aClass == orderMainClass));

        //3.第三种方式
        try {
            Class forName = Class.forName("com.ca.fire.domain.bean.OrderMain");
            System.out.println("forName == forName  " + (aClass == forName));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("orderMainClass.getName() 全类名" + orderMainClass.getName());
        System.out.println("orderMainClass.getSimpleName() 类名 " + orderMainClass.getSimpleName());

    }

    /**
     * 测试构造器
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test02() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.得到student的class对象
        Class clazz = Class.forName("com.ca.fire.test.domain.Student");

        //获取所有共有构造方法
        Constructor[] constructors = clazz.getConstructors();

        for (Constructor constructor : constructors) {
            //输出 public com.ca.fire.test.domain.Student(java.lang.String,int)
            System.out.println(constructor);
        }
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象

        Constructor constructors1 = clazz.getConstructor();
        Constructor constructor = clazz.getConstructor(null);
        System.out.println("constructors1  constructor " + constructors1 + " " + constructor);

        Object instance = constructor.newInstance();

        System.out.println("instance " + instance);
        Student t = (Student) instance;
        System.out.println(t.toString());

        System.out.println("******************获取私有构造方法，并调用*******************************");
        Constructor declaredConstructor = clazz.getDeclaredConstructor(char.class);
        declaredConstructor.setAccessible(true);
        Object man = declaredConstructor.newInstance('男');
        System.out.println(man);

    }

    /**
     * 测试字段
     *
     * @throws ClassNotFoundException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void test03() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.ca.fire.test.domain.Student");

        System.out.println("************获取所有公有的字段********************");

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("*************获取公有字段**并调用***********************************");
        Field field = clazz.getField("name");
        System.out.println(field);
        //或得一个对象
        Object obj = clazz.getConstructor().newInstance();
        //为字段设置值
        field.set(obj, "李明");
        System.out.println(obj);

        System.out.println("**************获取私有字段****并调用********************************");
        Field phoneNum = clazz.getDeclaredField("phoneNum");
        //强制访问,不加会报错
        phoneNum.setAccessible(true);
        phoneNum.set(obj, "1008611");

        System.out.println(obj);

    }

    /**
     * 测试成员方法
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void test04() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.ca.fire.test.domain.Student");
        //共有方法,包括集成的父类的共有方法
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        //本类的
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        //获得指定的方法
        Method show1 = clazz.getMethod("show1", String.class);
        System.out.println(show1);

        Object obj = clazz.getConstructor().newInstance();
        //对象obj执行show1
        show1.invoke(obj, "欢迎您!");

        //测试私有方法
        Method show4 = clazz.getDeclaredMethod("show4", int.class);
        System.out.println(show4);
        show4.setAccessible(true);
        show4.invoke(obj, 14);

    }


    @Test
    public void test5() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName("com.ca.fire.test.domain.Student");

        Method main = clazz.getMethod("main", String[].class);
        System.out.println(main);
//        main.invoke(null, new String[]{"a", "b", "c"});
        //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
        //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
        main.invoke(null, (Object) new String[]{"a", "b", "c"});//方式一
        main.invoke(null, new Object[]{new String[]{"a", "b", "c"}});//方式二

    }

    /**
     * 反射获取文件内容
     */
    @Test
    public void test6() throws IOException {

        Properties properties = new Properties();
        FileReader fileReader = new FileReader("jdbc.properties");
        properties.load(fileReader);
        fileReader.close();

        Object username = properties.get("username");
        String password = properties.getProperty("password");
        System.out.println("username：" + username + ",password: " + password);
    }

    @Test
    public void test07() throws ClassNotFoundException {
        Class clazz = Class.forName("com.ca.fire.test.domain.Student");
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }

    }



}
