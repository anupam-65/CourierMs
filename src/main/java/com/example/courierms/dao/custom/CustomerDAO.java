package com.example.courierms.dao.custom;

import com.example.courierms.dao.CrudDAO;
import com.example.courierms.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String>  {
    int getNoOfCustomers() throws ClassNotFoundException, SQLException;
}
