package com.example.courierms.bo.custom.impl;

import com.example.courierms.bo.custom.DeliveryDetailsBO;
import com.example.courierms.dao.DAOFactory;
import com.example.courierms.dao.custom.DeliveryDetailsDAO;
import com.example.courierms.dto.CustomerDTO;
import com.example.courierms.dto.DeliveryDetailsDTO;
import com.example.courierms.entity.Customer;
import com.example.courierms.entity.DeliveryDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class DeliveryDetailsBOImpl implements DeliveryDetailsBO {

    DeliveryDetailsDAO deliveryDetailsDAO= (DeliveryDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DELIVERY);

    @Override
    public ObservableList<DeliveryDetailsDTO> getAllDeliveryDetails() throws ClassNotFoundException, SQLException {
        ObservableList<DeliveryDetails>all= deliveryDetailsDAO.getAll();
        ObservableList<DeliveryDetailsDTO>allDeliveryDetails= FXCollections.observableArrayList();
        for (DeliveryDetails d : all){
            allDeliveryDetails.add(new DeliveryDetailsDTO(d.getDID(),d.getBID(),
                    d.getDFirstName(),d.getDSecondName(),d.getDTelephoneNO(),d.getDAddress(),
                    d.getDueDate(),d.getOrderAction()));
        }
        return allDeliveryDetails;
    }

    @Override
    public DeliveryDetailsDTO searchDeliveryDetails(String id) throws ClassNotFoundException, SQLException {
        DeliveryDetails search=deliveryDetailsDAO.search(id);
        return new DeliveryDetailsDTO(search.getDID(),search.getBID(),search.getDFirstName(),search.getDSecondName(),search.getDTelephoneNO(),
                search.getDAddress(),search.getDueDate(),search.getOrderAction());
    }

    @Override
    public boolean updateDeliveryDetails(DeliveryDetailsDTO dto) throws ClassNotFoundException, SQLException {
        return deliveryDetailsDAO.update(new DeliveryDetails(dto.getDID(),dto.getBID(),dto.getDFirstName(),dto.getDSecondName(),
                dto.getDTelephoneNO(),dto.getDAddress(),dto.getDueDate(),dto.getOrderAction()));
    }
}
