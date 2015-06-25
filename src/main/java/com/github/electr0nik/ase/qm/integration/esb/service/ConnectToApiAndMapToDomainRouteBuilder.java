package com.github.electr0nik.ase.qm.integration.esb.service;

import com.github.electr0nik.ase.qm.integration.esb.model.JsonModel;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/**
 * Created by nik on 25.06.15.
 */
@Component
public class ConnectToApiAndMapToDomainRouteBuilder extends RouteBuilder {

  /**
   * Let's configure the Camel routing rules using Java code...
   */
  @Override
  public void configure() {

    from("direct:start") // random start
        .setExchangePattern(ExchangePattern.InOut)
        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
        .dynamicRouter(method(MyDuperDynamicRouter.class, "route"))

        .to("stream:out")
    //.unmarshal().json(JsonLibrary.Jackson, JsonModel.class
      ;


    from("direct:simpleJoke")
        .setExchangePattern(ExchangePattern.InOut)
        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
        .to("http4://api.icndb.com/jokes/random/1")
        .unmarshal().json(JsonLibrary.Jackson, JsonModel.class);
  }

}
