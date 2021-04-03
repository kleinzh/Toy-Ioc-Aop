package org.example.ioc.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Classname BeanFactory
 * @Description TODO
 * @Date 2021-04-03 15:35
 * @Created by Klein
 */
public class BeanFactory {

    private static Map<String, Object> map = new HashMap<>();

    static {
        InputStream resourceStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceStream);
            Element rootElement = document.getRootElement();

            List<Element> list = rootElement.selectNodes("//bean");

            // 加载解析xml，读取xml中的bean信息，通过反射技术实例化bean对象，然后放⼊ map待⽤
            for (int i = 0; i < list.size(); i++) {
                Element element = list.get(i);
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Class<?> aClass = Class.forName(className);
                map.put(id, aClass.newInstance());
            }
            //维护bean之间的依赖关系,属性注入进去
            List<Element> refList = rootElement.selectNodes("//property");
            for (int i = 0; i < refList.size(); i++) {
                Element element = refList.get(i);
                String ref = element.attributeValue("ref");
                String name = element.attributeValue("name");

                String parentId = element.getParent().attributeValue("id");
                Object parentObject = map.get(parentId);
                Method[] methods = parentObject.getClass().getMethods();
                for (Method method : methods) {
                    if (("set" + name).equalsIgnoreCase(method.getName())) {
                        // bean之间的依赖关系（注⼊bean）
                        method.invoke(parentObject, map.get(ref));
                    }
                }
                // 维护依赖关系后重新将bean放⼊map中
                map.put(parentId, parentObject);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public  static Object getBean(String id) {
        return map.get(id);
    }

}
