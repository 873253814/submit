package com.example.demo.service;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        serializeFlyPig();
        Person person = deserializeFlyPig();
        System.out.println(person.toString());

    }

    /**
     * 序列化
     */
    private static void serializeFlyPig() throws IOException {
        Person person = new Person();
        person.getAtomicInteger().set(88);
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/flyPig.txt")));
        oos.writeObject(person);
        System.out.println("FlyPig 对象序列化成功！");
        oos.close();
    }

    /**
     * 反序列化
     */
    private static Person deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/flyPig.txt")));
        Person person = (Person) ois.readObject();
        System.out.println("FlyPig 对象反序列化成功！");
        return person;
    }
}
