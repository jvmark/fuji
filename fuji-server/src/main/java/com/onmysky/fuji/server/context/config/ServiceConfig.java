package com.onmysky.fuji.server.context.config;

import com.onmysky.nova.client.RpcProxy;
import com.onmysky.nova.client.ServiceDiscovery;
import com.onmysky.nova.server.RpcServer;
import com.onmysky.nova.server.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mark on 15/10/22.
 */
@Configuration
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
  public RpcServer rpcServer() {
    ServiceRegistry serviceRegistry = serviceRegistry();
    RpcServer rpcServer = new RpcServer(serverAddress, serviceRegistry);
    return rpcServer;
  }

  //@Bean
  public ServiceDiscovery serviceDiscovery() {
    ServiceDiscovery serviceDiscovery = new ServiceDiscovery(registerAddress);
    return serviceDiscovery;
  }

  //@Bean
  public RpcProxy rpcProxy() {
    ServiceDiscovery serviceDiscovery = serviceDiscovery();
    RpcProxy rpcProxy = new RpcProxy(serviceDiscovery);
    System.out.println("=================rpcProxy================");
    return rpcProxy;
  }
}
