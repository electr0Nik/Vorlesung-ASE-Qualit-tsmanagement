package com.github.electr0nik.ase.qm.service.dto.domain;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by nik on 24.06.15.
 */
public class JokeDomain {

  /**
   * possible parameter for the webservice
   *
   * @ulr: http://api.icndb.com/jokes/random/15?limitTo=[nerdy,explicit]&firstName=Nik&lastName=Ko
   */

  private final Integer id;
  private final Long ampunt;

  private final String firstName;
  private final String lastName;

  private final List<String> limitToList;
  private final List<String> wordList;

  private final String joke;


  public JokeDomain(Builder builder) {
    this.id = builder.id;
    this.ampunt = builder.amount;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.limitToList = builder.limitToList;
    this.wordList = builder.wordList;
    this.joke = builder.joke;
  }

  public Integer getId() {
    return id;
  }

  public Long getAmpunt() {
    return ampunt;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<String> getLimitToList() {
    return ImmutableList.copyOf(this.limitToList);
  }

  public List<String> getWordList() {
    return wordList;
  }

  public String getJoke() {
    return joke;
  }

  public static class Builder {

    private Integer id;
    private Long amount;

    private String firstName;
    private String lastName;

    private List<String> limitToList;
    private List<String> wordList;
    private String joke;

    public Builder id(Integer id) {
      this.id = id;
      return this;
    }

    public Builder amount(Long amount) {
      this.amount = amount;
      return this;
    }

    public Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder limitToList(List<String> limitToList) {
      this.limitToList = limitToList;
      return this;
    }

    public Builder wordList(List<String> wordList) {
      this.wordList = wordList;
      return this;
    }

    public Builder joke(String joke) {
      this.joke = joke;
      return this;
    }


    public JokeDomain create() {
      return new JokeDomain(this);
    }
  }
}