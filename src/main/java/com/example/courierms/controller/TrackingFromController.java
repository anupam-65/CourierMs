package com.example.courierms.controller;

import com.example.courierms.bo.BOFactory;
import com.example.courierms.bo.custom.DeliveryDetailsBO;
import com.example.courierms.dto.DeliveryDetailsDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class TrackingFromController {
    public Label lblDID;
    public Label lblFirstName;
    public Label lblSecondName;
    public Label lblTp;
    public Label lblAddress;
    public Label lblDueDate;
    public Label lblOnAction;
    public Label lblBID;
    public TextField txtDID;
    public ProgressBar progressbar;

    DeliveryDetailsBO deliveryDetailsBO = (DeliveryDetailsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DELIVERY);

    public void btnTrackOnAction(ActionEvent actionEvent) {

        String did = txtDID.getText();
        //VALIDATION-->
        if (Pattern.compile("^(D0)[0-9]{1,}$").matcher(did).matches()) {
            //TRACKING START-->
            try {
                DeliveryDetailsDTO detailsDTO = deliveryDetailsBO.searchDeliveryDetails(did);
                if (detailsDTO != null) {
                    lblDID.setText(detailsDTO.getDID());
                    lblBID.setText(detailsDTO.getBID());
                    lblFirstName.setText(detailsDTO.getDFirstName());
                    lblSecondName.setText(detailsDTO.getDSecondName());
                    lblTp.setText(detailsDTO.getDTelephoneNO());
                    lblAddress.setText(detailsDTO.getDAddress());
                    lblDueDate.setText(detailsDTO.getDueDate());
                    lblOnAction.setText(detailsDTO.getOrderAction());

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();


            }
        } else {
            txtDID.setStyle("-fx-border-color: red;");
            txtDID.requestFocus();
        }
        //PROGRESSBAR CONTROLLER
        String liveLocation = lblOnAction.getText();
        switch (liveLocation) {
            case "Accepted":
                progressbar.setProgress(0.2);
                break;
            case "InProgress":
                progressbar.setProgress(0.4);
                break;
            case "Shipped":
                progressbar.setProgress(0.6);
                break;
            case "Delivered":
                progressbar.setProgress(0.8);
                break;
            case "Completed":
                progressbar.setProgress(1.0);
                break;
        }


    }


}
