package com.sf.shiva.oms.ht.service.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * 描述：jar加载器
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月4日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public class JarClassLoader {
    
    private static List<JarURLConnection> cachedJarFile = new ArrayList<>();
    
    public static void loadJar(String filePath) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException{
        URL url = new URL("jar:file:"+filePath+"!/");
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);  
        boolean accessible = method.isAccessible();  
        if (!accessible) {  
            method.setAccessible(true);  
        }  
        URLClassLoader classLoader = (URLClassLoader) JarClassLoader.class.getClassLoader();             
        try {
            URLConnection uc = url.openConnection();
            uc.setUseCaches(true);
            cachedJarFile.add((JarURLConnection)uc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        method.invoke(classLoader, url);  
    }
    
    public static void loadJarByDirectory(String directory) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException{
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);  
        boolean accessible = method.isAccessible();  
        if (!accessible) {  
            method.setAccessible(true);  
        }  
        // 设置类加载器  
        URLClassLoader classLoader = (URLClassLoader) JarClassLoader.class.getClassLoader();
        File file = new File(directory);
        if(file.isDirectory()){
            File[] loadFiles = file.listFiles();
            for(File loadFile : loadFiles){
             // 将当前类路径加入到类加载器中  
                method.invoke(classLoader, loadFile.toURI().toURL());  
            }
        }
    }
    
    public static void unloadJarFile(String jarName){
        for(JarURLConnection url : cachedJarFile){
            try {
                String fileName = url.getJarFile().getName();
                if(jarName.equals(getSimpleName(fileName))){
                    url.getJarFile().close();
                    url = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String getSimpleName(String path){
        String [] strs = path.split("\\\\");
        return strs[strs.length-1];
    }
    
}
