import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class GivePatientToDoctor {
    Doctor doctor;
    Patient patient;

    Hospital hospital;

    //CONSTRUCTOR
    public GivePatientToDoctor(Patient patient, Hospital hospital) {
//        this.doctor = doctor;
        this.patient = patient;
        this.hospital = hospital;
    }


    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Doctor> findApplicableDoctors() {
        List<Doctor> returnList = new ArrayList<>();
        if (hospital.getExpertiseMap().containsKey(patient.getMedicalNeeds())) {
            List<Doctor> doctorsOfSpecialty = hospital.getDoctorList(patient.getMedicalNeeds());
            System.out.println("Available Doctors with required specialization (Please Pick a number associated with a doctor!)");
            int indexLocale = 0;
            for (Doctor doctor : doctorsOfSpecialty) {
                indexLocale += 1;
                System.out.println(indexLocale + ". " + doctor.getDoctorName());
            }

            returnList = doctorsOfSpecialty;

        }
        return returnList;

    }

    public void retrieveDoctorForPatientViaSelection(int chosenDoctorIndex, List<Doctor> doctorsOfSpecialty) {
        if (chosenDoctorIndex == 0) {
            System.out.println("There is no doctor with the specializations needed");
        }
        else {
            Doctor doctorChosen = doctorsOfSpecialty.get((chosenDoctorIndex - 1));
            setDoctor(doctorChosen);
        }
    }

        public void addingPatient() {
            doctor.addPatientToDoctor(patient);
            System.out.println(patient.getPatientName() + " has been give to " + doctor.getDoctorName() + " as a patient!");
        }
}
