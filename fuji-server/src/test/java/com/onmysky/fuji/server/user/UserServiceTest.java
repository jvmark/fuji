package com.onmysky.fuji.server.user;

import com.onmysky.fuji.client.dto.UserDTO;
import com.onmysky.fuji.client.service.IUserService;
import com.onmysky.fuji.server.BaseTest;
import com.onmysky.fuji.server.user.dao.UserDAO;
import com.onmysky.fuji.server.user.domain.UserDO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by mark on 15/10/19.
 */
public class UserServiceTest extends BaseTest{

  @Resource
  private IUserService iUserService;

  @Resource
  private UserDAO userDAO;

  @Test
  public void testFindById() {
    UserDTO user = null;
    try {
      user = iUserService.findOne(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(user);
  }

  @Test
  public void testUserDAO() {
    UserDO userDO = userDAO.findById(1);
    System.out.println(userDO.getName());
  }
}
