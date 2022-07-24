import java.util.Scanner;

public class DoctorMaker {
    private UserInputInterface inputService;


    //Constructor


    public DoctorMaker(UserInputInterface inputService) {
        this.inputService = inputService;
    }

    // NEEDED TO PROCESS:
    // 1. Doctors Name (String)
    // 2. Doctor Spec (String)
    Doctor createDoctor(){
        String doctorsName = inputService.receiveStringInput("Doctors name?: ");
        int intResult = inputService.receiveIntInput("Doctors expertise?: \n 1. Common Cold \n 2. Body Trauma \n 3. Lime Disease");
        String evaluatedSpecialty = evaluateSpecialty(intResult);

        Doctor doctor = new Doctor(doctorsName, evaluatedSpecialty);

        return doctor;
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


