package com.onmysky.fuji.server.user.service;

import com.onmysky.fuji.client.dto.UserDTO;
import com.onmysky.fuji.client.service.IUserService;
import com.onmysky.fuji.server.user.dao.UserDAO;
import com.onmysky.fuji.server.user.domain.UserDO;
import com.onmysky.nova.server.RpcService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by mark on 15/10/19.
 */

@Component
@RpcService(value = UserService.class)
public class UserService implements IUserService{

  @Resource
  private UserDAO userDAO;

  @Resource
  private ModelMapper modelMapper;
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Override
  public UserDTO findOne(int id) {
    UserDTO res = null;
    UserDO userDO = userDAO.findById(id);
    if (null != userDO) {
      res = modelMapper.map(userDO, UserDTO.class);
    }
    logger.info("hello user");
    return res;
  }
}
