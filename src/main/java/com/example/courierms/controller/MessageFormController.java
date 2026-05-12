package com.example.courierms.controller;

import com.example.courierms.bo.BOFactory;
import com.example.courierms.bo.custom.MessageBO;
import com.example.courierms.dto.MessageDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class MessageFormController {
    public TextField txtMsgId;
    public TextArea txtMessageBox;
    public TableView tblMessage;
    public TableColumn colMsgId;
    public TableColumn colMessage;
    MessageBO messageBO = (MessageBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MESSAGE);

    public void initialize() throws SQLException, ClassNotFoundException {
        loadMessages();
        generateMID();
        colMsgId.setCellValueFactory(new PropertyValueFactory<>("MID"));
        colMessage.setCellValueFactory(new PropertyValueFactory<>("Message"));
    }

    public void generateMID() {
        try {
            int count = messageBO.getNoOfMessages();
            if (count == 0) {
                txtMsgId.setText("M001");
            }
            if (count > 0 && count <= 8) {
                txtMsgId.setText("M00" + (count + 1));
            }
            if (count >= 9 && count <= 98) {
                txtMsgId.setText("M0" + (count + 1));
            }
            if (count >= 99) {
                txtMsgId.setText("M" + (count + 1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadMessages() throws SQLException, ClassNotFoundException {
        ObservableList<MessageDTO> allMessages = messageBO.getAllMessages();
        tblMessage.setItems(allMessages);
    }

    public void btnMsgPost(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String msgID = txtMsgId.getText();
        String message = txtMessageBox.getText();
        MessageDTO messageDTO = new MessageDTO(msgID, message);
        try {
            boolean isAdded = messageBO.addMessageBox(messageDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Message Posted!").show();
                loadMessages();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Message Not Posted!").show();
            loadMessages();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void rowClickOnAction(MouseEvent mouseEvent) {
        MessageDTO messageDTO = (MessageDTO) tblMessage.getSelectionModel().getSelectedItem();
        txtMsgId.setText(messageDTO.getMID());
        txtMessageBox.setText(messageDTO.getMessage());
    }
}
