package com.github.electr0nik.ase.qm.common.config;

import com.github.electr0nik.ase.qm.service.dto.domain.JokeDomain;
import com.github.electr0nik.ase.qm.web.mvc.form.JokeTO;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

@Configuration
public class DozerConfiguration {

  @Bean(name = "org.dozer.Mapper")
  public DozerBeanMapper dozerBean(BeanMappingBuilder builder) {

    DozerBeanMapper mapper = new DozerBeanMapper();
    mapper.addMapping(builder);
    return mapper;
  }

  @Bean
  public BeanMappingBuilder getBeanMappingBuilder() {

    return new BeanMappingBuilder() {
      protected void configure() {
        mapping(JokeTO.class, JokeDomain.Builder.class)
            .fields("categoryList", "limitToList", copyByReference())
            .fields("firstname", "firstName")
            .fields("lastname", "lastName")
            .fields("amount", "amount")
            .fields("badWordList", "wordList", copyByReference());
      }
    };
  }
}
