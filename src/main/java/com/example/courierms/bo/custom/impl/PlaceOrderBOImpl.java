package com.example.courierms.bo.custom.impl;

import com.example.courierms.bo.custom.PlaceOrderBO;

import com.example.courierms.dao.DAOFactory;
import com.example.courierms.dao.custom.BillDetailsDAO;
import com.example.courierms.dao.custom.CustomerDAO;
import com.example.courierms.dao.custom.DeliveryDetailsDAO;
import com.example.courierms.db.DBConnection;
import com.example.courierms.dto.BillDetailsDTO;
import com.example.courierms.dto.CustomDTO;
import com.example.courierms.dto.DeliveryDetailsDTO;
import com.example.courierms.entity.BillDetails;
import com.example.courierms.entity.DeliveryDetails;

import java.sql.Connection;
import java.sql.SQLException;


public class PlaceOrderBOImpl implements PlaceOrderBO {


    BillDetailsDAO billDetailsDAO = (BillDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BILL);
    DeliveryDetailsDAO deliveryDetailsDAO = (DeliveryDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DELIVERY);


    @Override
    public boolean placeOrder(CustomDTO dto) throws ClassNotFoundException, SQLException, Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            BillDetails billDetails = new BillDetails(dto.getBID(),dto.getCID(),dto.getItemType(),dto.getPaymentType(),dto.getRates(),dto.getTodayDate());
            boolean add = billDetailsDAO.add(billDetails);

            if (add) {
                DeliveryDetails deliveryDetails = new DeliveryDetails(dto.getDID(), dto.getBID(),
                        dto.getdFirstName(), dto.getdSecondName(), dto.getdTelephoneNO(), dto.getdAddress(),
                        dto.getDueDate(), dto.getOrderAction());
                boolean response = deliveryDetailsDAO.add(deliveryDetails);
                if (response) {
                    connection.setAutoCommit(true);
                    return true;
                } else {
                    connection.rollback();
                    return false;

                }

            } else {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }


    }

    @Override
    public int getNoOfBills() throws ClassNotFoundException, SQLException {
        return billDetailsDAO.getNoBills();
    }

    @Override
    public int getNoOfDeliveries() throws ClassNotFoundException, SQLException {
        return deliveryDetailsDAO.getNoOfDeliveries();
    }
}
