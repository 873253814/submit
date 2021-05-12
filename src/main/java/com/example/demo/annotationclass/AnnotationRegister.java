package com.example.demo.annotationclass;

import com.example.demo.annotation.Receiver;
import com.example.demo.listen.EventInitProcess;
import com.example.demo.listen.EventListener;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AnnotationRegister {

    @Autowired
    private EventInitProcess eventInitProcess;

    private Map<Object, List<Method>> registerMap = new ConcurrentHashMap<>();

    private List<Object> clazzList = Lists.newArrayList();

    public void register(Object bean) {
        if (bean == null) {
            return;
        }
        clazzList.add(bean);
        Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
        int methodsLength = methods.length;
        for (int i = 0; i < methodsLength; i++) {
            Receiver annotation = methods[i].getAnnotation(Receiver.class);
            if (annotation != null) {
                Class<? extends Receiver> annotationClass = annotation.getClass();
                registerMap.computeIfAbsent(bean, key -> new ArrayList<>()).add(methods[i]);
            }
        }
        for (Map.Entry<Object, List<Method>> entry : registerMap.entrySet()) {
            entry.getValue().forEach(method -> {
                try {
                    method.invoke(entry.getKey());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        }

        for (Object aClass : clazzList) {
            for (Field field : aClass.getClass().getFields()) {
                System.out.println(field.getGenericType());
            }
        }

        eventInitProcess.fire(EventListener.IEvent.class);
    }
}
