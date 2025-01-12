package dao;

import model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientDao {
    int addPatient(UUID caseId, Patient patient);
    default int addPatient(Patient patient) {
        UUID caseId = UUID.randomUUID();
        return addPatient(caseId, patient);
    }
    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(UUID caseId);

    List<Patient> getAllNegativeStatusPatients();

    List<Patient> getAllPositiveStatusPatients();

    int removePatientById(UUID caseId);
    int removeAllNegativeStatusPatients();
    int updatePatientDetailById(UUID caseId, Patient newDetail);
}

