package com.github.electr0nik.ase.qm.integration.esb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nik on 25.06.15.
 */

public class JsonModel {

  private String type;
  private List<Map<String, Object>> value = new ArrayList<>();


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Map<String, Object>> getValue() {
    return value;
  }

  public void setValue(List<Map<String, Object>> value) {
    this.value = value;
  }
}