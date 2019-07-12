package com.demo.android.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jinxh on 16/8/9.
 */
public class SerializableUtils {


    public static void serializable2File(Object serializable, String fileName) {
        File file = new File(fileName);
        try {
            //实例化ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            //将对象写入文件
            oos.writeObject(serializable);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object file2serializable(String file) {
        Object returnValue = null;
        //实例化ObjectInputStream对象
        //读取对象people,反序列化
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            returnValue = ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
