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
  private final Long amount;

  private final String firstName;
  private final String lastName;

  private final List<String> limitToList;
  private final List<String> wordList;

  private final String joke;


  public JokeDomain(Builder builder) {
    this.id = builder.id;
    this.amount = builder.amount;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.limitToList = builder.limitToList;
    this.wordList = builder.wordList;
    this.joke = builder.joke;
  }

  public Integer getId() {
    return id;
  }

  public Long getAmount() {
    return amount;
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


    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Long getAmount() {
      return amount;
    }

    public void setAmount(Long amount) {
      this.amount = amount;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public List<String> getLimitToList() {
      return limitToList;
    }

    public void setLimitToList(List<String> limitToList) {
      this.limitToList = limitToList;
    }

    public List<String> getWordList() {
      return wordList;
    }

    public void setWordList(List<String> wordList) {
      this.wordList = wordList;
    }

    public String getJoke() {
      return joke;
    }

    public void setJoke(String joke) {
      this.joke = joke;
    }

    public JokeDomain create() {
      return new JokeDomain(this);
    }
  }
}