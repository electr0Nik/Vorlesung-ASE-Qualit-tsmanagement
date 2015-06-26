package com.github.electr0nik.ase.qm.service.impl;

import com.github.electr0nik.ase.qm.integration.esb.model.JsonModel;
import com.github.electr0nik.ase.qm.integration.esb.model.enums.JsonModelHashMapEnum;
import com.github.electr0nik.ase.qm.service.JokeService;
import com.github.electr0nik.ase.qm.service.dto.domain.JokeDomain;
import com.github.electr0nik.ase.qm.web.mvc.form.JokeTO;
import org.apache.camel.ExchangePattern;
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
        return this.getMappedJokeDomainResponse(routeFrom, idList, 1L, null, null);
    }

    @Override
    public List<JokeDomain> getSpecificJoke(List<Integer> idList, JokeTO jokeModel) {
        String routeFrom = "direct:start";

        JokeDomain.Builder builder = new JokeDomain.Builder();
        this.mapper.map(jokeModel, builder);
        return this.getMappedJokeDomainResponse(routeFrom, idList, jokeModel.getAmount(), builder.create(), null);
    }

    private List<JokeDomain> getMappedJokeDomainResponse(
            String from, List<Integer> idList, Long amount, JokeDomain jokeDomain, Integer iteration) {
        List<JokeDomain> returnValue = new ArrayList<>();

        JsonModel jsonModel = (JsonModel) this.producerTemplate.send(from, exchange -> {
            if (jokeDomain != null) {
                exchange.setProperty("params", jokeDomain);
            }
        }).getOut().getBody();

        // iterate over list; but since there is only one entry
        JokeDomain.Builder builder = new JokeDomain.Builder();
        jsonModel.getValue().forEach(stringObjectMap -> {
            int id = (int) stringObjectMap.get(JsonModelHashMapEnum.ID.getValue());
            if (!idList.contains(id)) {
                final boolean[] filter = {false};
                if (jokeDomain != null && jokeDomain.getWordList() != null && !jokeDomain.getWordList().isEmpty()) {
                    jokeDomain.getWordList().forEach(badWord -> {
                        filter[0] = ((String) stringObjectMap.get(JsonModelHashMapEnum.JOKE.getValue())).contains(badWord);
                    });
                }
                builder.setId(id);
                builder.setJoke((String) stringObjectMap.get(JsonModelHashMapEnum.JOKE.getValue()));
                builder.setLimitToList((List<String>) stringObjectMap.get(JsonModelHashMapEnum.CATEGORIES.getValue()));

                if (!filter[0]) {
                    returnValue.add(builder.create());
                }
            }
        });

        if (returnValue.size() < amount && (iteration == null || iteration < 1)) {
            this.getMappedJokeDomainResponse(from, idList, amount, jokeDomain, 1);
        }
        return returnValue;
    }
}
