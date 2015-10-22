package com.onmysky.fuji.server.context.config;

import com.onmysky.nova.server.RpcServer;
import com.onmysky.nova.server.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by mark on 15/10/22.
 */

public class ServiceConfig {

  @Value("${rpc.registry.address}")
  private String registerAddress;
  @Value("${rpc.server.address}")
  private String serverAddress;

  @Bean
  public ServiceRegistry serviceRegistry() {
    ServiceRegistry serviceRegistry = new ServiceRegistry(registerAddress);
    return serviceRegistry;
  }

  @Bean
  public RpcServer serverServer() {
    ServiceRegistry serviceRegistry = serviceRegistry();
    RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
    return rpcServer;
  }
}
