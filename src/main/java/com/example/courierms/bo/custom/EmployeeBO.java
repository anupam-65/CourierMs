package com.example.courierms.bo.custom;

import com.example.courierms.bo.SuperBO;
import com.example.courierms.dto.CustomerDTO;
import com.example.courierms.dto.EmployeeDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface EmployeeBO extends SuperBO {

    boolean addEmployee(EmployeeDTO employee) throws ClassNotFoundException, SQLException;

    ObservableList<EmployeeDTO> getAllEmployee() throws ClassNotFoundException, SQLException;

    boolean deleteEmployee(String id) throws ClassNotFoundException, SQLException;

    EmployeeDTO searchEmployee(String id) throws ClassNotFoundException, SQLException;

    boolean updateEmployee(EmployeeDTO employee) throws ClassNotFoundException, SQLException;
}
