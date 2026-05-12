package com.example.courierms.bo.custom;

import com.example.courierms.bo.SuperBO;
import com.example.courierms.dto.BillDetailsDTO;
import com.example.courierms.dto.CustomDTO;
import com.example.courierms.dto.DeliveryDetailsDTO;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public boolean placeOrder(CustomDTO dto) throws ClassNotFoundException, SQLException, Exception;
    public int getNoOfBills() throws ClassNotFoundException,SQLException;
    public int getNoOfDeliveries()throws ClassNotFoundException,SQLException;
}
