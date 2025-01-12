package com.example.conronaPatients.service;

import model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PatientService {
    public void addPatient(Patient patient) {
    }

    public List<Patient> getAllPatients() {
        return null;

    }

    public Optional<Patient> getPatientById(UUID caseId) {

        return null;
    }

    public List<Patient> getAllNegativeStatusPatients() {
        return null;

    }

    public List<Patient> getAllPositiveStatusPatients() {
        return null;

    }

    public int removePatientById(UUID caseId) {
        return 0;

    }

    public int removeAllNegativeStatusPatients() {
return 0;
    }

    public int updatePatientDetailById(UUID caseId, Patient newDetail) {

        return 0;
    }
}
