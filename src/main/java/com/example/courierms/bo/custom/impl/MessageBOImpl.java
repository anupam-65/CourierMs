package com.example.courierms.bo.custom.impl;

import com.example.courierms.bo.custom.MessageBO;
import com.example.courierms.dao.DAOFactory;
import com.example.courierms.dao.custom.MessageDAO;
import com.example.courierms.dto.CustomerDTO;
import com.example.courierms.dto.MessageDTO;
import com.example.courierms.entity.Customer;
import com.example.courierms.entity.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MessageBOImpl implements MessageBO {

    MessageDAO messageDAO= (MessageDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MESSAGE);


    @Override
    public String getMessage() throws ClassNotFoundException, SQLException {
        return messageDAO.getMessage();
    }

    @Override
    public boolean addMessageBox(MessageDTO message) throws ClassNotFoundException, SQLException {
        return messageDAO.add(new Message(message.getMID(),message.getMessage()));
    }

    @Override
    public ObservableList<MessageDTO> getAllMessages() throws ClassNotFoundException, SQLException {
        ObservableList<Message>all=messageDAO.getAll();
        ObservableList<MessageDTO>allMessages=FXCollections.observableArrayList();
        for (Message m : all){
            allMessages.add(new MessageDTO(m.getMID(),m.getMessage()));
        }
        return allMessages;
    }

    @Override
    public int getNoOfMessages() throws ClassNotFoundException, SQLException {
        return messageDAO.getNoMessages();
    }
}
