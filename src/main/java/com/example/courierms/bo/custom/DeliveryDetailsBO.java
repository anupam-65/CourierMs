package com.example.courierms.bo.custom;

import com.example.courierms.bo.SuperBO;
import com.example.courierms.dto.CustomerDTO;
import com.example.courierms.dto.DeliveryDetailsDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DeliveryDetailsBO extends SuperBO {
    ObservableList<DeliveryDetailsDTO> getAllDeliveryDetails() throws ClassNotFoundException, SQLException;

    DeliveryDetailsDTO searchDeliveryDetails(String id) throws ClassNotFoundException, SQLException;

    boolean updateDeliveryDetails(DeliveryDetailsDTO deliveryDetails) throws ClassNotFoundException, SQLException;
}
