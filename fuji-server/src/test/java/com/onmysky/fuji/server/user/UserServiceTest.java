package com.onmysky.fuji.server.user;

import com.onmysky.fuji.client.dto.UserDTO;
import com.onmysky.fuji.client.service.IUserService;
import com.onmysky.fuji.server.BaseTest;
import com.onmysky.fuji.server.user.dao.UserDAO;
import com.onmysky.fuji.server.user.domain.UserDO;
import com.onmysky.fuji.server.user.service.UserService;
import com.onmysky.nova.client.RpcProxy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by mark on 15/10/19.
 */
public class UserServiceTest extends BaseTest{

  @Resource
  private IUserService iUserService;

  @Resource
  private UserDAO userDAO;

  @Autowired
  private RpcProxy rpcProxy;

  @Test
  public void testFindById() {
    UserDTO user = null;
    try {
      user = iUserService.findOne(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(user.getName());
  }

  @Test
  public void testUserDAO() {
    UserDO userDO = userDAO.findById(1);
    System.out.println(userDO.getName());
  }

  @Test
  public void test() {
    UserService userService = rpcProxy.create(IUserService.class);
    UserDTO user = userService.findOne(1);
    System.out.println(user.getName());
  }
}
