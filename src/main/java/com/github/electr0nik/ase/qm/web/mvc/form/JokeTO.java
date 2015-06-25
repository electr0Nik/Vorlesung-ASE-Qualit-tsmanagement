package com.github.electr0nik.ase.qm.web.mvc.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nik on 23.06.15.
 */
public class JokeTO {

  private List<String> badWordList = new ArrayList<>();
  private List<String> categoryList = new ArrayList<>();

  @NotEmpty//(groups = {IAssignNameGroup.class})
  private String firstname;
  @NotEmpty//(groups = {IAssignNameGroup.class})
  private String lastname;

  private Boolean isBadWordsAllowed;

  @NotNull//(groups = {IAssignAmountGroup.class})
  private Long amount;


  public List<String> getBadWordList() {
    return badWordList;
  }

  public void setBadWordList(List<String> badWordList) {
    this.badWordList = badWordList;
  }

  public List<String> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<String> categoryList) {
    this.categoryList = categoryList;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Boolean getIsBadWordsAllowed() {
    return isBadWordsAllowed;
  }

  public void setIsBadWordsAllowed(Boolean isBadWordsAllowed) {
    this.isBadWordsAllowed = isBadWordsAllowed;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }
}
