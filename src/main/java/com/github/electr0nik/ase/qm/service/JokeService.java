package com.github.electr0nik.ase.qm.service;

import com.github.electr0nik.ase.qm.service.dto.domain.JokeDomain;
import com.github.electr0nik.ase.qm.web.mvc.form.JokeTO;

import java.util.List;

/**
 * Created by nik on 24.06.15.
 */
public interface JokeService {
  List<JokeDomain> getSimpleRandomJoke(List<Integer> idList);

  List<JokeDomain> getSpecificJoke(List<Integer> idList, JokeTO jokeModel);
}
