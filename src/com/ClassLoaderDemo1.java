package com;

import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xiangchijie
 * @date 2022/1/12 3:52 下午
 */
public class ClassLoaderDemo1 {

    public static void main(String[] args) throws MalformedURLException {
        // null 根类加载器 BootStrap
        System.out.println("Object.class " + Object.class.getClassLoader());

        // sun.misc.Launcher$ExtClassLoader 扩展类加载器 Extension
        System.out.println("DNSNameService.class " + DNSNameService.class.getClassLoader());

        // sun.misc.Launcher$AppClassLoader 系统类加载器 System
        System.out.println("ClassLoaderDemo1.class " + ClassLoaderDemo1.class.getClassLoader());

        // MyLoaderClassLoader 自定义类加载器

        System.out.println();
        ClassLoader classLoader = ClassLoaderDemo1.class.getClassLoader();
        while(classLoader != null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println();

    }

}
