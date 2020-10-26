package com.sunshanpeng.devops.common.configuration;

import com.sunshanpeng.devops.common.core.base.BaseEnum;
import com.sunshanpeng.devops.common.enums.dict.DictController;
import com.sunshanpeng.devops.common.enums.dict.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import javax.annotation.Resource;
import java.util.Set;

@Configuration
@Order
@Slf4j
public class DictConfiguration implements InitializingBean {
    @Resource
    private DictService dictService;

    @Override
    public void afterPropertiesSet() throws Exception {
        BeanDefinitionRegistry bdr = new SimpleBeanDefinitionRegistry();
        ClassPathBeanDefinitionScanner s = new ClassPathBeanDefinitionScanner(bdr);

        TypeFilter tf = new AssignableTypeFilter(BaseEnum.class);
        s.resetFilters(false);
        s.addIncludeFilter(tf);
        Set<BeanDefinition> candidateComponents = s.findCandidateComponents("com.sunshanpeng.devops");
        for (BeanDefinition candidateComponent : candidateComponents) {
            Class<?> clazz = getClass().getClassLoader().loadClass(candidateComponent.getBeanClassName());
            Class<? extends BaseEnum> dictClazz = clazz.asSubclass(BaseEnum.class);
            dictService.registerDict(dictClazz);
        }
    }

    @Bean
    public DictService dictService() {
        return new DictService();
    }

    @Bean
    public DictController dictController() {
        return new DictController();
    }

}
