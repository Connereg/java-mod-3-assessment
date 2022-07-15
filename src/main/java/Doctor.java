import java.util.ArrayList;
import java.util.List;


public class Doctor {
    //Doctor needs a name and a medical specialization
    private String doctorName;
    private String doctorSpecialization;
    private int healingPower;
    private List<Patient> patientList;



    //Constructor
    public Doctor(String doctorNameInput, String doctorSpecializationInput, int healingPower) {
        this.doctorName = doctorNameInput;
        this.doctorSpecialization = doctorSpecializationInput;
        this.healingPower = healingPower;
        this.patientList = new ArrayList<>();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public void addPatientToDoctor(Patient patientToAdd) {
        patientList.add(patientToAdd);
    }


    public void getPatientList() {
        System.out.println("The doctor is: " + doctorName);
        for (Patient patient : patientList) {
            String gotName = patient.getPatientName();
            String gotSpecialty = patient.getMedicalNeeds();
            int gotHealthValue = patient.getHealthValue();
            String result = ("Patient Name: " + gotName + " \n Medical needs are: " + gotSpecialty + " \n Current Health Value: " + gotHealthValue + " / 100" );

            System.out.println(result);
        }
    }

    public void getSimplePatientList() {
        int i = 1;
        for (Patient patient : patientList) {
            System.out.println(i + ". " + patient.getPatientName() + " - Health Value: " + patient.getHealthValue() + " /100" );
            i++;
        }
    }

    public void listPatientsToTreat() {
        System.out.println(doctorName + " can treat the following patients: ");
        int i = 1;
        for (Patient patient : patientList) {
            System.out.println(i + ". " + patient.getPatientName());
            i++;
        }
    }
}