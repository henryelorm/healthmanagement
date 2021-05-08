/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

/**
 *
 * @author Elorm
 */
public class Paths {

    private static final String basic = "/healthmanagement/";

    /*
    ----------------------------------------------------------------------------------------------------------
        staff screen paths
     */
    public static String staffScreen1ID = "newStaffMain";
    public static String staffScreen1File = basic + "fxml/NewStafScreen1.fxml";
    public static String staffScreen2ID = "newStaffScreen2";
    public static String staffScreen2File = basic + "fxml/NewStaffScreen2.fxml";

    /*
    ----------------------------------------------------------------------------------------------------------
        patient screen paths
     */
    public static String patientScreen1ID = "newPatientMain";
    public static String patientScreen1File = basic + "fxml/NewPatientScreen1.fxml";
    public static String patientScreen2ID = "newPatientScreen2";
    public static String patientScreen2File = basic + "fxml/NewPatientScreen2.fxml";

    /*
    ----------------------------------------------------------------------------------------------------------
     */
    public static String loginInterface = basic + "fxml/Login.fxml";
    public static String mainInterface = basic + "fxml/mainInterFace.fxml";
    public static String addNewDepartments = basic + "fxml/Add_Department.fxml";
    public static String searchPatient = basic + "fxml/SearchPatients.fxml";
    public static String actionProgressInd = basic + "fxml/ActionLoader.fxml";
    public static String departmentlist = basic + "fxml/DeparmentsList.fxml";
    public static String searchDoctor = basic + "fxml/SearchDoctor.fxml";
    public static String dailyLogList = basic + "fxml/DailyLogList.fxml";
    public static String nurseLog = basic + "fxml/PatientNursekLog.fxml";
    public static String folder = basic + "fxml/FoldersView.fxml";
    public static String fileConfirmation = basic + "fxml/fileviewConfirmation.fxml";
    public static String fileTable = basic + "fxml/FileTable.fxml";
    public static String fileView = basic + "fxml/FileView.fxml";
    public static String fileView2 = basic + "fxml/FileView2.fxml";
    public static String fileView3 = basic + "fxml/FileView3.fxml";
    public static String fileView4 = basic + "fxml/Fileview4.fxml";
    public static String editFileTemplate = basic + "fxml/EditFileTemplate.fxml";
    public static String selectionTemplate = basic + "fxml/SelectionTemplate.fxml";
    public static String dailyLogQuickView = basic + "fxml/DailyLogQuickView.fxml";
    public static String dailyLogQuickViewTable = basic + "fxml/DailyLoqQuickViewTable.fxml";
    public static String sendTemplate = basic + "fxml/SendsTemplate.fxml";
    public static String viewStaffInfo = basic + "fxml/StaffViewInfo.fxml";

    /*
    ----------------------------------------------------------------------------------------------------------
         images
    */
    public static String sendImage = basic + "images/send.png";
    public static String noImage = basic + "images/noImage.gif";
}
