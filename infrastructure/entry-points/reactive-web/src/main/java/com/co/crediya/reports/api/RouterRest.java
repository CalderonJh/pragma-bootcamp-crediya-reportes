package com.co.crediya.reports.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterRest {
  @Bean
  @RouterOperations({
    @RouterOperation(
        path = "/api/v1/reportes",
        method = RequestMethod.GET,
        beanClass = Handler.class,
        beanMethod = "listenGETReport")
  })
  public RouterFunction<ServerResponse> routerFunction(Handler handler) {
    return route(GET("/api/v1/reportes"), sr -> handler.listenGETReport());
  }
}
