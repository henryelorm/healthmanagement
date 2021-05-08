/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.controllers.DailyLogListController;
import healthmanagement.controllers.FXMLDocumentController;
import healthmanagement.entity.Department;
import healthmanagement.entity.Patient;
import healthmanagement.entity.Records_Patient;
import healthmanagement.entity.Records_patient_NurseLog;
import healthmanagement.entity.Staff;
import healthmanagement.entityManager.DailyRecordManager;
import healthmanagement.entityManager.DepartmentManager;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.persistence.EntityManager;

/**
 *
 * @author Elorm
 */
public class Table_List_Population {

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        send Template functions
     */
    public static TableColumn<Staff, String> lastName_sendDoctor(TableColumn<Staff, String> lastname) {

        lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        return lastname;
    }

    public static TableColumn<Staff, String> firstName_sendDoctor(TableColumn<Staff, String> firstname) {

        firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        return firstname;
    }

    public static TableColumn<Staff, String> otherName_sendDoctor(TableColumn<Staff, String> othername) {

        othername.setCellValueFactory(new PropertyValueFactory<>("otherName"));

        return othername;
    }

    public static TableColumn<Department, String> department_send(TableColumn<Department, String> department) {

        department.setCellValueFactory(new PropertyValueFactory<>("name"));

        return department;
    }

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        fileview2 functions
     */
    public static void medicals_loadListView(ListView<String> list, String string) {

        if (StringSplit.splitGammaString(string) != null) {
            List<String> findList = StringSplit.splitGammaString(string);
            for (int i = 0; i < findList.size(); i++) {
                if (!findList.get(i).equals("")) {
                    list.getItems().add(findList.get(i));
                }
            }
        }

    }

    public static TableColumn<Records_patient_NurseLog, String> date_nurseLog_fileView2(TableColumn<Records_patient_NurseLog, String> recordTable) {

        recordTable.setCellValueFactory(new PropertyValueFactory<>("date"));

        return recordTable;
    }

    public static TableColumn numberColumn_DailyLog_fileView2(TableColumn number) {

        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Records_patient_NurseLog, Records_patient_NurseLog>, ObservableValue<Patient>>() {
            @Override
            public ObservableValue<Patient> call(TableColumn.CellDataFeatures<Records_patient_NurseLog, Records_patient_NurseLog> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        number.setCellFactory(new Callback<TableColumn<Records_patient_NurseLog, Records_patient_NurseLog>, TableCell<Records_patient_NurseLog, Records_patient_NurseLog>>() {
            @Override
            public TableCell<Records_patient_NurseLog, Records_patient_NurseLog> call(TableColumn<Records_patient_NurseLog, Records_patient_NurseLog> param) {
                return new TableCell<Records_patient_NurseLog, Records_patient_NurseLog>() {
                    @Override
                    protected void updateItem(Records_patient_NurseLog item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        return number;
    }

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         Folder functions
     */
    public static TableColumn<Records_Patient, String> timeColumn(TableColumn<Records_Patient, String> time) {

        time.setCellValueFactory(new PropertyValueFactory<>("createdTime"));
        return time;
    }

    public static TableColumn<Records_Patient, String> dateColumn(TableColumn<Records_Patient, String> date) {

        date.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        return date;
    }


    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         Daily Log
     */
    public static void addDailyLog_to_List(ObservableList<Records_patient_NurseLog> deList, Records_patient_NurseLog rpn) {

        deList.add(0, rpn);

    }

    public static TableColumn<Records_patient_NurseLog, String> date_nurseLog(TableColumn<Records_patient_NurseLog, String> recordTable) {

        recordTable.setCellValueFactory(new PropertyValueFactory<>("date"));

        return recordTable;
    }

    public static TableColumn numberColumn_DailyLog(TableColumn number) {

        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Records_patient_NurseLog, Records_patient_NurseLog>, ObservableValue<Patient>>() {
            @Override
            public ObservableValue<Patient> call(TableColumn.CellDataFeatures<Records_patient_NurseLog, Records_patient_NurseLog> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        number.setCellFactory(new Callback<TableColumn<Records_patient_NurseLog, Records_patient_NurseLog>, TableCell<Records_patient_NurseLog, Records_patient_NurseLog>>() {
            @Override
            public TableCell<Records_patient_NurseLog, Records_patient_NurseLog> call(TableColumn<Records_patient_NurseLog, Records_patient_NurseLog> param) {
                return new TableCell<Records_patient_NurseLog, Records_patient_NurseLog>() {
                    @Override
                    protected void updateItem(Records_patient_NurseLog item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        return number;
    }

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     patient functions
     */
    public static ObservableList genderList = FXCollections.observableArrayList("Male", "Female");

    public static ObservableList maritalStatusList = FXCollections.observableArrayList("Married", "Single", "Widowed", "Divorced", "Separated", "Living common law");

    public static TableColumn<Patient, String> ID_patient(TableColumn<Patient, String> id) {

        id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        return id;
    }

    public static TableColumn<Patient, String> firstName_patient(TableColumn<Patient, String> fname) {

        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return fname;
    }

    public static TableColumn<Patient, String> lastName_patient(TableColumn<Patient, String> lname) {

        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lname;
    }

    public static TableColumn<Patient, String> otherName_patient(TableColumn<Patient, String> oname) {

        oname.setCellValueFactory(new PropertyValueFactory<>("otherName"));
        return oname;
    }

    public static TableColumn<Patient, String> phoneNo_patient(TableColumn<Patient, String> phoneNo) {

        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        return phoneNo;
    }

    public static TableColumn numberColumn_patients(TableColumn number) {

        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Patient, Patient>, ObservableValue<Patient>>() {
            @Override
            public ObservableValue<Patient> call(TableColumn.CellDataFeatures<Patient, Patient> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        number.setCellFactory(new Callback<TableColumn<Patient, Patient>, TableCell<Patient, Patient>>() {
            @Override
            public TableCell<Patient, Patient> call(TableColumn<Patient, Patient> param) {
                return new TableCell<Patient, Patient>() {
                    @Override
                    protected void updateItem(Patient item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        return number;
    }

    /*
    -----------------------------------------------------------------------------------------------------------------------------------------------------
         Department & Staff
     */
    public static ObservableList staffTitles = FXCollections.observableArrayList("Nurse", "Lab Technician", "Pharmacist", "Security");

    public static void addDepartment_to_List(ObservableList<Department> deList, Department de) {

        deList.add(0, de);

    }

    public static void loadDepartments(EntityManager em, ListView<String> list) {

        DepartmentManager dpm = new DepartmentManager();
        FXMLDocumentController.departmentList.setAll(dpm.findAllDepartment(em));
        int size = FXMLDocumentController.departmentList.size();

        for (int i = 0; i < size; i++) {
            list.getItems().add(FXMLDocumentController.departmentList.get(i).getName());
        }

    }

    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------
                          Doctor Tables 
     */
    public static TableColumn<Staff, String> ID_Doctor(TableColumn<Staff, String> id) {

        id.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        return id;
    }

    public static TableColumn<Staff, String> firstName_Doctor(TableColumn<Staff, String> fname) {

        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return fname;
    }

    public static TableColumn<Staff, String> lastName_Doctor(TableColumn<Staff, String> lname) {

        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lname;
    }

    public static TableColumn<Staff, String> otherName_Doctor(TableColumn<Staff, String> oname) {

        oname.setCellValueFactory(new PropertyValueFactory<>("otherName"));
        return oname;
    }

    public static TableColumn<Staff, String> phoneNo_Doctor(TableColumn<Staff, String> phoneNo) {

        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        return phoneNo;
    }

    public static TableColumn<Staff, String> task_Doctor(TableColumn<Staff, String> task) {

        task.setCellValueFactory(new PropertyValueFactory<>("title"));
        return task;
    }

    public static TableColumn numberColumn_Doctor(TableColumn number) {

        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Staff, Staff>, ObservableValue<Staff>>() {
            @Override
            public ObservableValue<Staff> call(TableColumn.CellDataFeatures<Staff, Staff> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        number.setCellFactory(new Callback<TableColumn<Staff, Staff>, TableCell<Staff, Staff>>() {
            @Override
            public TableCell<Staff, Staff> call(TableColumn<Staff, Staff> param) {
                return new TableCell<Staff, Staff>() {
                    @Override
                    protected void updateItem(Staff item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        return number;
    }


    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------
                          Staff Tables 
     */
    public static TableColumn<Staff, String> ID(TableColumn<Staff, String> id) {

        id.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        return id;
    }

    public static TableColumn<Staff, String> firstName(TableColumn<Staff, String> fname) {

        fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return fname;
    }

    public static TableColumn<Staff, String> lastName(TableColumn<Staff, String> lname) {

        lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return lname;
    }

    public static TableColumn<Staff, String> otherName(TableColumn<Staff, String> oname) {

        oname.setCellValueFactory(new PropertyValueFactory<>("otherName"));
        return oname;
    }

    public static TableColumn<Staff, String> phoneNo(TableColumn<Staff, String> phoneNo) {

        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        return phoneNo;
    }

    public static TableColumn<Staff, String> task(TableColumn<Staff, String> task) {

        task.setCellValueFactory(new PropertyValueFactory<>("title"));
        return task;
    }

    public static TableColumn numberColumn(TableColumn number) {

        number.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Staff, Staff>, ObservableValue<Staff>>() {
            @Override
            public ObservableValue<Staff> call(TableColumn.CellDataFeatures<Staff, Staff> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        number.setCellFactory(new Callback<TableColumn<Staff, Staff>, TableCell<Staff, Staff>>() {
            @Override
            public TableCell<Staff, Staff> call(TableColumn<Staff, Staff> param) {
                return new TableCell<Staff, Staff>() {
                    @Override
                    protected void updateItem(Staff item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        return number;
    }
    /*
    ---------------------------------------------------------------------------------------------------------------------------------------------------
                 Folders Received Table         
     */
    public static TableColumn<PatientMessage, String> folderReceived(TableColumn<PatientMessage, String> fr) {

        fr.setCellValueFactory(new PropertyValueFactory<>("name"));

        return fr;
    }

}
