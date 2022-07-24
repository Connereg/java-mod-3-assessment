public class Patient {
    // Patient has a name and a medical specialization need
    private String patientName;
    private String medicalNeeds;
    private int healthValue;

    //Constructor
    public Patient(String nameInput, String medicalNeedsInput) {
        this.patientName = nameInput;
        this.medicalNeeds = medicalNeedsInput;

        switch (medicalNeedsInput) {
            case "Common Cold":
                this.healthValue = 80;
                break;
            case "Body Trauma":
                this.healthValue = 40;
                break;
            case "Lime Disease":
                this.healthValue = 70;
        }
    }
    //Constructor 2
    public Patient(){

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

    public int getHealthValue() {
        return healthValue;
    }
    public void setHealthValue(int newHealthValue) {
        this.healthValue = newHealthValue;
    }

}