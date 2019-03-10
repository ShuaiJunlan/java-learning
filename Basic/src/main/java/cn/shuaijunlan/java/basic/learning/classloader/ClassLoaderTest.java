package cn.shuaijunlan.java.basic.learning.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:57 AM 3/7/19.
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader());
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null){
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };

        ClassLoader loader1 = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        };

        try {
            Object obj = loader.loadClass(ClassLoaderTest.class.getName());
            System.out.println(obj instanceof ClassLoaderTest);

            Object o = new ClassLoaderTest();
            System.out.println(o instanceof ClassLoaderTest);

            Object o1 = loader.loadClass(Object.class.getName());
            System.out.println(o1 instanceof Object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
