package com.ca.fire.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ContextRefreshedEvent 事件会在Spring容器初始化完成会触发该事件
 * 防止重复触发,
 * Spring 提供了一个SmartApplicationListener类，可以支持listener之间的触发顺序，普通的ApplicationListener优先级最低（最后触发）。
 */
@Component
public class SpringInitFinishListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(SpringInitFinishListener.class);
    private volatile AtomicBoolean isInit = new AtomicBoolean(false);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!isInit.compareAndSet(false, true)) {
            //防止重复触发
            return;
        }

        ApplicationContext applicationContext = event.getApplicationContext();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        String[] arr$ = beanNames;
        int len$ = beanNames.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String beanName = arr$[i$];

            try {
                Object bean = applicationContext.getBean(beanName);
                if (bean != null) {
                    logger.debug(beanName, bean);
                }
            } catch (Exception var8) {
                logger.error(var8.getMessage());
            }
        }
    }
}
