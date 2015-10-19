package com.onmysky.fuji.client.service;

import com.onmysky.fuji.client.dto.UserDTO;

/**
 * Created by mark on 15/10/19.
 */
public interface IUserService {
  public UserDTO findOne(int id);
}
