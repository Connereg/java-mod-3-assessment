import javax.print.Doc;

public class GivePatientToDoctor {
    Doctor doctor;
    Patient patient;

    //CONSTRUCTOR
    public GivePatientToDoctor(Patient patient, Doctor doctor) {
        this.doctor = doctor;
        this.patient = patient;
    }

    public void addingPatient() {
        doctor.addPatientToDoctor(patient);
        System.out.println();
    }
}
