package com;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xiangchijie
 * @date 2022/1/12 4:41 下午
 */
public class URLClassLoaderDemo {

    public static void main(String[] args) throws Exception {
        loaderByPath();
        loaderByHttp();
    }

    private static void loaderByPath() throws Exception {
        // /Users/xiangchijie/com/clz/Demo.class;
        File file = new File("/Users/xiangchijie/");
        URL url = file.toURI().toURL();
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[]{url});
        System.out.println(urlClassLoader.getParent());

        Class<?> demoClz = urlClassLoader.loadClass("com.clz.Demo");
        demoClz.newInstance();
    }

    private static void loaderByHttp() throws Exception {
        // http://47.104.156.200:8888/com/clz/Demo.class
        URL url = new URL("http://47.104.156.200:8888/");
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[]{url});
        Class<?> demoClz = urlClassLoader.loadClass("com.clz.Demo");
        demoClz.newInstance();
    }

}
