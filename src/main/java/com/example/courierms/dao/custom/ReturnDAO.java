package com.example.courierms.dao.custom;

import com.example.courierms.dao.CrudDAO;
import com.example.courierms.entity.ReturnDetails;

import java.sql.SQLException;

public interface ReturnDAO extends CrudDAO<ReturnDetails,String> {
    int getNoOfReturn()throws ClassNotFoundException, SQLException;
    int getDailyReturnCount(String id)throws ClassNotFoundException,SQLException;
    int getMonthlyReturnCount()throws ClassNotFoundException,SQLException;
}
