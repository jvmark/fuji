package com.onmysky.fuji.client.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mark on 15/10/17.
 */
public class UserDTO implements Serializable {

  private static final long serialVersionUID = 4693620960007968175L;
  private int    id;
  private int    userId;
  private String name;
  private String passwd;
  private Date   createTime;
  private Date   updateTime;

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public int getId() {

    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
