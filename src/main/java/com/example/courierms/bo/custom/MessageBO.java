package com.example.courierms.bo.custom;

import com.example.courierms.bo.SuperBO;
import com.example.courierms.dto.CustomerDTO;
import com.example.courierms.dto.MessageDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface MessageBO extends SuperBO {
    public String getMessage() throws ClassNotFoundException, SQLException;
    boolean addMessageBox(MessageDTO message) throws ClassNotFoundException, SQLException;
    ObservableList<MessageDTO> getAllMessages() throws ClassNotFoundException, SQLException;
    public int getNoOfMessages() throws ClassNotFoundException,SQLException;
}
