package hello.core.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean(BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class, () -> ac.getBean(BeanB.class));
    }

    @Configuration
    @ComponentScan (
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponet.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponet.class)
    )
    static class ComponentFilterAppConfig {

    }
}
