package com;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义网络类加载器
 *
 * @author xiangchijie
 * @date 2022/1/12 5:29 下午
 */
public class MyURLClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        // http://47.104.156.200:8888/com/clz/Demo.class
        MyURLClassLoader myURLClassLoader = new MyURLClassLoader("http://47.104.156.200:8888/");
        Class<?> demoClz = myURLClassLoader.loadClass("com.clz.Demo");
        demoClz.newInstance();
    }

    private String url;

    public MyURLClassLoader(ClassLoader parent, String url) {
        super(parent);
        this.url = url;
    }

    public MyURLClassLoader(String url) {//默认类加载是系统类加载器 sun.misc.Launcher$AppClassLoader
        this.url = url;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String path = url + "/" + name.replace(".", "/") + ".class";
            URL url = null;
            url = new URL(path);
            byte[] datas = null;
            try (InputStream inputStream = url.openStream(); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buf)) != -1) {
                    byteArrayOutputStream.write(buf, 0, len);
                }
                datas = byteArrayOutputStream.toByteArray();
                return defineClass(name, datas, 0, datas.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
        return super.findClass(name);
    }
}