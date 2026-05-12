package com.example.courierms.bo.custom.impl;

import com.example.courierms.bo.BOFactory;
import com.example.courierms.bo.custom.ReturnBO;
import com.example.courierms.dao.DAOFactory;
import com.example.courierms.dao.custom.ReturnDAO;
import com.example.courierms.dto.CustomerDTO;
import com.example.courierms.dto.ReturnDetailsDTO;
import com.example.courierms.entity.Customer;
import com.example.courierms.entity.ReturnDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ReturnBOImpl implements ReturnBO {
    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN);

    @Override
    public boolean addReturn(ReturnDetailsDTO dto) throws ClassNotFoundException, SQLException {
        return returnDAO.add(new ReturnDetails(dto.getRID(), dto.getBID(), dto.getReason(), dto.getReturnDate()));
    }

    @Override
    public ObservableList<ReturnDetailsDTO> getAllReturnDetails() throws ClassNotFoundException, SQLException {
        ObservableList<ReturnDetails> all = returnDAO.getAll();
        ObservableList<ReturnDetailsDTO> allReturnDetails = FXCollections.observableArrayList();
        for (ReturnDetails r : all) {
            allReturnDetails.add(new ReturnDetailsDTO(r.getRID(), r.getBID(), r.getReason(), r.getReturnDate()));

        }

        return allReturnDetails;

    }

    @Override
    public int getNoOfReturn() throws ClassNotFoundException, SQLException {
        return returnDAO.getNoOfReturn();
    }

    @Override
    public int getDailyReturnCount(String id) throws ClassNotFoundException, SQLException {
        return returnDAO.getDailyReturnCount(id);
    }

    @Override
    public int getMonthlyReturnCount() throws ClassNotFoundException, SQLException {
        return returnDAO.getMonthlyReturnCount();
    }
}
