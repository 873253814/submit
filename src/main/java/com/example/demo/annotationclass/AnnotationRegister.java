package com.example.demo.annotationclass;

import com.example.demo.annotation.Receiver;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AnnotationRegister {

    private Map<Object, List<Method>> registerMap = new HashMap<>();
    public void register(Object bean) {
        Object clz = bean;
        Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(clz.getClass());
        int methodsLength = methods.length;
        for (int i = 0; i < methodsLength; i++) {
            Receiver annotation = methods[i].getAnnotation(Receiver.class);
            if (annotation != null) {
                Class<? extends Receiver> annotationClass = annotation.getClass();
                registerMap.computeIfAbsent(clz, key -> new ArrayList<>()).add(methods[i]);
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
    }
}
