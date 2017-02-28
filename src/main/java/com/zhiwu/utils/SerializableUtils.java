package com.zhiwu.utils;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;

import java.io.*;

/**
 * Created by weiqingming on 2017/2/21.
 * 对象序列化工具类
 */
public class SerializableUtils {

    public static SerializableUtils getUtils() {
        return new SerializableUtils();
    }


    /**
     * 纯hessian序列化
     *
     * @param object
     * @return
     * @throws Exception
     */

    public byte[] hSerialize(Object object) {

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianSerializerOutput hessianOutput = new HessianSerializerOutput(os);
            hessianOutput.writeObject(object);
            return os.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new NullPointerException();
    }

    /**
     * 纯hessian反序列化
     *
     * @param bytes
     * @return
     * @throws Exception
     */

    public Object dehSerialize(byte[] bytes) {


        try {

            Object object = null;
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            HessianSerializerInput hessianInput = new HessianSerializerInput(is);
            object = hessianInput.readObject();
            return object;

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new NullPointerException();

    }


    /**
     * 序列化，使用常规java序列化方式
     *
     * @param obj
     * @return
     * @throws Exception
     */

    public <T> byte[] serialize(T obj) {

        try {

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            ObjectOutputStream out = new ObjectOutputStream(os);

            out.writeObject(obj);

            return os.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new NullPointerException();

    }

    /**
     * 反序列化
     *
     * @param by
     * @return
     * @throws Exception
     */

    public <T> T deSerialize(byte[] by) {

        try {

            ByteArrayInputStream is = new ByteArrayInputStream(by);

            ObjectInputStream in = null;
            in = new ObjectInputStream(is);
            return (T) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
