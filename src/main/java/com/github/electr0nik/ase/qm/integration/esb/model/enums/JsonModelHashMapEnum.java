package com.github.electr0nik.ase.qm.integration.esb.model.enums;

import com.github.electr0nik.ase.qm.integration.esb.model.JsonModel;

/**
 * Created by nik on 25.06.15.
 */
public enum JsonModelHashMapEnum {

  ID("id"),
  JOKE("joke"),
  CATEGORIES("categories");


  private String value;

  JsonModelHashMapEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String getName(String value) {
    for (JsonModelHashMapEnum e : values()) {
      if (e.name().equals(value)) {
        return e.name();
      }
    }
    throw new IllegalStateException("Enum not found!");
  }
}
