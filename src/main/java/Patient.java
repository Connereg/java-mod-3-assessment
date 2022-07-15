public class Patient {
    // Patient has a name and a medical specialization need
    private String patientName;
    private String medicalNeeds;
    private int healthValue;

    //Constructor
    public Patient(String nameInput, String medicalNeedsInput, int healthValue) {
        this.patientName = nameInput;
        this.medicalNeeds = medicalNeedsInput;
        this.healthValue = healthValue;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicalNeeds() {
        return medicalNeeds;
    }

    public int getHealthValue() { return healthValue; }

}