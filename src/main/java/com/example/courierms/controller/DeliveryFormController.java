package com.example.courierms.controller;

import com.example.courierms.bo.BOFactory;
import com.example.courierms.bo.custom.DeliveryDetailsBO;
import com.example.courierms.dto.DeliveryDetailsDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class DeliveryFormController {
    public TableColumn colDID;
    public TableColumn colBID;
    public TableColumn colFName;
    public TableColumn colSName;
    public TableColumn colTP;
    public TableColumn colAddress;
    public TableColumn colDueDate;
    public TableColumn colOrderAction;
    public TableView tblDeliveryDetails;
    public ComboBox<String> cmbOrderAction;
    public Label lblDID;
    public Label lblBID;
    public Label lblFName;
    public Label lblSName;
    public Label lblAddress;
    public Label lblDueDate;
    public ComboBox<String> cmbDID;
    public Label lblOrderAction;
    public Label lblTp;

    DeliveryDetailsBO deliveryDetailsBO = (DeliveryDetailsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DELIVERY);

    public void initialize() throws SQLException, ClassNotFoundException {
        setValuesToCmbDID();
        loadAllDeliveryDetails();
        setValuesToCmbOrderOnAction();
        colDID.setCellValueFactory(new PropertyValueFactory<>("DID"));
        colBID.setCellValueFactory(new PropertyValueFactory<>("BID"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("dFirstName"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("dSecondName"));
        colTP.setCellValueFactory(new PropertyValueFactory<>("dTelephoneNO"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("dAddress"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        colOrderAction.setCellValueFactory(new PropertyValueFactory<>("OrderAction"));
    }

    private void loadAllDeliveryDetails() throws SQLException, ClassNotFoundException {
        ObservableList<DeliveryDetailsDTO> allDeliveryDetails = deliveryDetailsBO.getAllDeliveryDetails();
        tblDeliveryDetails.setItems(allDeliveryDetails);
    }

    public void setValuesToCmbDID() {
        try {
            ObservableList<DeliveryDetailsDTO> deliveryDetailList = deliveryDetailsBO.getAllDeliveryDetails();
            for (DeliveryDetailsDTO c : deliveryDetailList) {
                cmbDID.getItems().add(c.getDID());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectDIDOnAction(ActionEvent actionEvent) {
        try {
            DeliveryDetailsDTO detailsDTO = deliveryDetailsBO.searchDeliveryDetails(cmbDID.getSelectionModel().getSelectedItem());
            if (detailsDTO != null) {
                lblDID.setText(detailsDTO.getDID());
                lblBID.setText(detailsDTO.getBID());
                lblFName.setText(detailsDTO.getDFirstName());
                lblSName.setText(detailsDTO.getDSecondName());
                lblTp.setText(detailsDTO.getDTelephoneNO());
                lblAddress.setText(detailsDTO.getDAddress());
                lblDueDate.setText(detailsDTO.getDueDate());
                lblOrderAction.setText(detailsDTO.getOrderAction());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setValuesToCmbOrderOnAction() {
        cmbOrderAction.getItems().addAll("Accepted", "InProgress", "Shipped", "Delivered", "Completed");
    }

    public void updateOnAction(ActionEvent actionEvent) {
        // Implement your update logic here if needed
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String did = lblDID.getText();
        String bid = lblBID.getText();
        String fName = lblFName.getText();
        String lName = lblSName.getText();
        String tp = lblTp.getText();
        String address = lblAddress.getText();
        String date = lblDueDate.getText();
        String orderAction = cmbOrderAction.getSelectionModel().getSelectedItem();

        DeliveryDetailsDTO deliveryDetailsDTO = new DeliveryDetailsDTO(did, bid, fName, lName, tp, address, date, orderAction);

        try {
            boolean isAdded = deliveryDetailsBO.updateDeliveryDetails(deliveryDetailsDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successful!", ButtonType.OK).show();
                loadAllDeliveryDetails();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Customer Update Not Successful!", ButtonType.OK).show();
        }
    }
}
