package dao;

import dao.PatientDao;
import model.Patient;
import model.PatientStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
public class PatientDaoImpl implements PatientDao {
    private static List<Patient> coronaPatients = new ArrayList<>();
    @Override
    public int addPatient(UUID caseId, Patient patient) {
        coronaPatients.add(new Patient(caseId, patient.getName(), patient.getGender(), patient.getPatientStatus()));
        return 1; // Assuming successful addition
    }
    @Override
    public List<Patient> getAllPatients() {
        return coronaPatients;
    }
    @Override
    public Optional<Patient> getPatientById(UUID caseId) {
        return coronaPatients.stream()
                .filter(patient -> patient.getCaseId().equals(caseId))
                .findFirst();
    }
    @Override
    public List<Patient> getAllNegativeStatusPatients() {
        return coronaPatients.stream()
                .filter(patient -> patient.getPatientStatus().equals(PatientStatus.NEGATIVE))
                .collect(Collectors.toList());
    }
    @Override
    public List<Patient> getAllPositiveStatusPatients() {
        return coronaPatients.stream()
                .filter(patient -> patient.getPatientStatus().equals(PatientStatus.POSITIVE))
                .collect(Collectors.toList());
    }

    @Override
    public int removePatientById(UUID caseId) {
        Optional<Patient> patient = getPatientById(caseId);
        if (patient.isPresent()) {
            coronaPatients.remove(patient.get());
            return 1; // Assuming successful removal
        }
        return 0; // Patient not found
    }

    @Override
    public int removeAllNegativeStatusPatients() {
        List<Patient> negativePatients = getAllNegativeStatusPatients();
        coronaPatients.removeAll(negativePatients);
        return negativePatients.size(); // Number of removed patients
    }

    @Override
    public int updatePatientDetailById(UUID caseId, Patient newDetail) {
        Optional<Patient> patient = getPatientById(caseId);
        if (patient.isPresent()) {
            Patient existingPatient = patient.get();
            existingPatient.setName(newDetail.getName());
            existingPatient.setGender(newDetail.getGender());
            existingPatient.setPatientStatus(newDetail.getPatientStatus());
            return 1; // Assuming successful update
        }
        return 0; // Patient not found
    }
}


