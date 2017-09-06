package com.bb.taold.db;

import com.bb.taold.bean.User;

/**
 * Created by fancl.
 * 数据库接口类
 */

public interface Database {
    
    public boolean updateUser(String authority);

    public User getUser();

}
