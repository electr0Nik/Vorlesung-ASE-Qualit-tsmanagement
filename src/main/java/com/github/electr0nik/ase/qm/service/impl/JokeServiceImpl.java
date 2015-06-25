package com.github.electr0nik.ase.qm.service.impl;

import com.github.electr0nik.ase.qm.integration.esb.model.JsonModel;
import com.github.electr0nik.ase.qm.integration.esb.model.enums.JsonModelHashMapEnum;
import com.github.electr0nik.ase.qm.service.JokeService;
import com.github.electr0nik.ase.qm.service.dto.domain.JokeDomain;
import com.github.electr0nik.ase.qm.web.mvc.form.JokeTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nik on 24.06.15.
 */
@Service
public class JokeServiceImpl implements JokeService {

  private DozerBeanMapper mapper;

  private ProducerTemplate producerTemplate;

  @Autowired
  public JokeServiceImpl(DozerBeanMapper mapper, ProducerTemplate producerTemplate) {
    this.mapper = mapper;
    this.producerTemplate = producerTemplate;
  }

  @Override
  public List<JokeDomain> getSimpleRandomJoke(List<Integer> idList) {
    String routeFrom = "direct:simpleJoke";
    return this.getMappedJokeDomainResponse(routeFrom, idList, 1);
  }

  @Override
  public List<JokeDomain> getSpecificJoke(List<Integer> idList, JokeTO jokeModel) {
    String routeFrom = "direct:start";

    // JsonModel jsonModel = (JsonModel)
        producerTemplate.send(routeFrom, exchange -> {
      // nothing
    }); //.getOut().getBody();
    return null;
  }

  private List<JokeDomain> getMappedJokeDomainResponse(String from, List<Integer> idList, int amount) {
    List<JokeDomain> returnValue = new ArrayList<>();
    JokeDomain.Builder builder = new JokeDomain.Builder();

    JsonModel jsonModel = (JsonModel) this.producerTemplate.send(from, exchange -> {
      // nothing
    }).getOut().getBody();

    // iterate over list; but since there is only one entry
    jsonModel.getValue().forEach(stringObjectMap -> {
      int id = (int) stringObjectMap.get(JsonModelHashMapEnum.ID.getValue());
      if (!idList.contains(id)) {
        builder.id(id);
        builder.joke((String) stringObjectMap.get(JsonModelHashMapEnum.JOKE.getValue()));
        builder.limitToList((List<String>) stringObjectMap.get(JsonModelHashMapEnum.CATEGORIES.getValue()));
        returnValue.add(builder.create());
      }
    });

    if (returnValue.size() < amount) {
      this.getMappedJokeDomainResponse(from, idList, amount);
    }
    return returnValue;
  }
}
