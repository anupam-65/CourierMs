package com.example.courierms.dao.custom;

import com.example.courierms.dao.CrudDAO;
import com.example.courierms.entity.DeliveryDetails;

import java.sql.SQLException;

public interface DeliveryDetailsDAO extends CrudDAO<DeliveryDetails,String> {
    int getNoOfDeliveries() throws ClassNotFoundException, SQLException;
}
