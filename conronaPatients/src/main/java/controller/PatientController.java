package controller;

import com.example.conronaPatients.service.PatientService;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping
@RestController
public class PatientController{
   private final PatientService patientService;
   @Autowired
   public PatientController(PatientService patientService) {
       this.patientService = patientService;
   }
    @PostMapping
    public void  addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @GetMapping("/{caseId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID caseId) {
        Optional<Patient> patient = patientService.getPatientById(caseId);
        return patient.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/negative")
    public ResponseEntity<List<Patient>> getAllNegativeStatusPatients() {
        List<Patient> patients = patientService.getAllNegativeStatusPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/positive")
    public ResponseEntity<List<Patient>> getAllPositiveStatusPatients() {
        List<Patient> patients = patientService.getAllPositiveStatusPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @DeleteMapping("/{caseId}")
    public ResponseEntity<Void> removePatientById(@PathVariable UUID caseId) {
        int rowsDeleted = patientService.removePatientById(caseId);
        return rowsDeleted > 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/negative")
    public ResponseEntity<Void> removeAllNegativeStatusPatients() {
        int rowsDeleted = patientService.removeAllNegativeStatusPatients();
        return rowsDeleted > 0 ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{caseId}")
    public ResponseEntity<Patient> updatePatientDetailById(@PathVariable UUID caseId, @RequestBody Patient newDetail) {
        int rowsUpdated = patientService.updatePatientDetailById(caseId, newDetail);
        return rowsUpdated > 0 ? new ResponseEntity<>(newDetail, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
