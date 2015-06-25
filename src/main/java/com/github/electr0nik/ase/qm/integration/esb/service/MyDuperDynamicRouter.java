package com.github.electr0nik.ase.qm.integration.esb.service;

import com.github.electr0nik.ase.qm.service.dto.domain.JokeDomain;
import org.apache.camel.Consume;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by nik on 25.06.15.
 */
public class MyDuperDynamicRouter {

  /**
   * expected:
   * "http4://api.icndb.com/jokes/random/{amount}?limitTo=[nerdy,explicit]&firstName=Nik&lastName=Ko"
   *
   * @param params
   * @return
   */
  public String route(JokeDomain params) {
    StringBuilder sb = new StringBuilder("http4://api.icndb.com/jokes/random/")
        .append(2)//params.getAmpunt())
        .append("?firstName=").append("klaus") //params.getFirstName())
        .append("&lastName=").append("cleber") //params.getLastName())
        //.append("limitTo=[").append(StringUtils.join(params.getLimitToList(), ",")).append("]")
        ;
    return sb.toString();
  }

}
