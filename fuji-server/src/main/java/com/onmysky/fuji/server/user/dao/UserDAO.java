package com.onmysky.fuji.server.user.dao;

import com.onmysky.fuji.server.user.domain.UserDO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * Created by mark on 15/10/19.
 */
@Component
public class UserDAO {

  @Resource
  private SqlSession writeTpl;

  @Resource
  private SqlSession readTpl;

  @Resource
  private TransactionTemplate transactionTpl;

  public UserDO findById(int id) {
    return (UserDO) readTpl.selectOne("user.findById", id);
  }
}
