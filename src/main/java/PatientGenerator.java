public class PatientGenerator {

    private UserInputInterface inputService;

    //CONSTRUCTOR

    public PatientGenerator(UserInputInterface inputService) { this.inputService = inputService; }
    // NEED TO PROCESS:
    // 1. Patients Name (String)
    // 2. Patients Ailemnt (String)

    Patient createPatient() {
        String patientName = inputService.receiveStringInput("What is the patients name? : ");
        int intResult = inputService.receiveIntInput("What is the Patient's ailment?: \\n 1. Common Cold \\n 2. Body Trauma \\n 3. Lime Disease");
        String evaluatedAilment = evaluateSpecialty(intResult);

        Patient patient = new Patient(patientName, evaluatedAilment);
        return patient;
    }

    public String evaluateSpecialty(int specialtyRef) {
        String returnedSpec = new String();
        switch (specialtyRef) {
            case 1:
                returnedSpec = "Common Cold";
                break;
            case 2:
                returnedSpec = "Body Trauma";
                break;
            case 3:
                returnedSpec = "Lime Disease";
                break;
        }
        return returnedSpec;

    }


}
