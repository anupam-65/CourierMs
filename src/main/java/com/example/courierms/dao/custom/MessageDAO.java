package com.example.courierms.dao.custom;

import com.example.courierms.dao.CrudDAO;
import com.example.courierms.entity.Message;

import java.sql.SQLException;

public interface MessageDAO extends CrudDAO<Message,String> {
    String getMessage()throws ClassNotFoundException, SQLException;
    int getNoMessages ()throws ClassNotFoundException, SQLException;
}
