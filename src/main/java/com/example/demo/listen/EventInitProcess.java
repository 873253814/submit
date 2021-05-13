package com.example.demo.listen;

import com.example.demo.annotation.Listener;
import org.assertj.core.util.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class EventInitProcess extends InstantiationAwareBeanPostProcessorAdapter {
    private Map<Class<?>, List<EventProxy>> listenerMap = new ConcurrentHashMap<>();
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        for (Class<?> aClass : ClassUtils.getAllInterfacesForClass(bean.getClass())) {
            if (aClass.isAnnotationPresent(Listener.class)) {
                listenerMap.computeIfAbsent(aClass, k -> new ArrayList<>()).add(new EventProxy(aClass, bean));

            }
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    public void fire(Class<?> clazz) {
        List<EventProxy> eventProxies = listenerMap.get(clazz);
        if (eventProxies.isEmpty()) {
            return;
        }
        eventProxies.forEach(eventProxy -> {
            eventProxy.getProxy().eventStart();
        });
    }
}
