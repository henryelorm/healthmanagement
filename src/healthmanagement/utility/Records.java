/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import healthmanagement.entity.Records_Patient;
import healthmanagement.entity.Records_patient_NurseLog;

/**
 *
 * @author Elorm
 */
public class Records {

    private Records_Patient records;

    private Records_patient_NurseLog recordsNurseLog;

    public Records_Patient getRecords() {
        return records;
    }

    public void setRecords(Records_Patient records) {
        this.records = records;
    }

    public Records_patient_NurseLog getRecordsNurseLog() {
        return recordsNurseLog;
    }

    public void setRecordsNurseLog(Records_patient_NurseLog recordsNurseLog) {
        this.recordsNurseLog = recordsNurseLog;
    }

}
