import java.util.ArrayList;
import java.util.List;


public class Doctor {
    //Doctor needs a name and a medical specialization
    private String doctorName;
    private String doctorSpecialization;
    private int healingPower;

    private List<Patient> patientList;



    //Constructor
    public Doctor(String doctorNameInput, String doctorSpecializationInput) {
        this.doctorName = doctorNameInput;
        this.doctorSpecialization = doctorSpecializationInput;
        this.healingPower = evaluateDoctorHealingPower(doctorSpecializationInput);
        this.patientList = new ArrayList<>();
    }

    //CONSTRUCTOR 2
    public Doctor() {

    }

    public int evaluateDoctorHealingPower(String specString) {
        int returnedHealPowerNum = 0;

        switch (specString) {
            case "Common Cold":
                // FOR COMMON COLD
                returnedHealPowerNum = 10;
                break;
            case "Body Trauma":
                // FOR BODY TRAUMA
                returnedHealPowerNum = 5;
                break;
            case "Lime Disease":
                //LIME DISEASE CAN ONLY GET WORSE, EVEN WHEN TREATED
                returnedHealPowerNum = -10;
                break;
        }
        return returnedHealPowerNum;
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


    public List<Patient> getPatientList() {
        return patientList;
    }

    public void printPatientList() {
        System.out.println("The doctor is: " + doctorName);
        List<Patient> patientList = getPatientList();
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

    public Patient getPatientAtIndexLocale(int indexLocale) {
        int trueIndex = indexLocale - 1;
        return patientList.get(trueIndex);
    }

    public void treatSpecifiedPatient(Patient patient) {

    }

    public void listPatientsToTreat() {
        System.out.println(doctorName + " can treat the following patients: ");
        int i = 1;
        for (Patient patient : patientList) {
            System.out.println(i + ". " + patient.getPatientName());
            i++;
        }
    }

    public int getHealingPower() {
        return this.healingPower;
    }


    public void doctorToString() {
        System.out.println("Doctor's Name: " + doctorName );
        System.out.println("Expertise: " + doctorSpecialization);
        System.out.println("This Doctor heals Patients by " + healingPower + "points every treatment" );
    }
}
