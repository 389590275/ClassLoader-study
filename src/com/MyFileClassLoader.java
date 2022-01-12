package com;

import java.io.*;

/**
 * 自定义文件类加载器
 *
 * @author xiangchijie
 * @date 2022/1/12 5:13 下午
 */
// 覆盖findClass方法
public class MyFileClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        MyFileClassLoader classLoader = new MyFileClassLoader("/Users/xiangchijie/");//加载该类所在的目录
        Class<?> demoClz = classLoader.loadClass("com.clz.Demo");
        demoClz.newInstance();
        System.out.println(classLoader.getParent());
    }

    public String dir;//被加载的类所在的目录

    public MyFileClassLoader(String dir) {
        this.dir = dir;
    }

    public MyFileClassLoader(String dir, ClassLoader parent) {
        super(parent);
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) {
        // 把类型转换为目录
        String file = dir + File.separator + name.replace(".", File.separator) + ".class";
        byte[] datas = null;
        try (FileInputStream fileInputStream = new FileInputStream(file); ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(buf)) != -1) {
                arrayOutputStream.write(buf, 0, len);
            }
            datas = arrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, datas, 0, datas.length);
    }

}
