import java.util.List;

public class TreatingProcess {
    private UserInputInterface inputService;
    private Hospital hospital;
    public TreatingProcess(UserInputInterface inputService, Hospital hospital) {
        this.inputService = inputService;
        this.hospital = hospital;
    }

    public String treatAnIllness() {
        int ailmentChosenInt = inputService.receiveIntInput("Which Ailment would you like to treat?: \n 1. Common Cold \n 2. Body Trauma \n 3. Lime Disease");
        String ailmentChosen = evaluateSpecialty(ailmentChosenInt);
        return ailmentChosen;
    }

    public int chooseDoctorOfSpecialty(String ailmentChosen) {
        int chosenDoctorIndex = 0;
        if (hospital.getExpertiseMap().containsKey(ailmentChosen)) {
            List<Doctor> doctorsAvailableToTreat = hospital.getDoctorList(ailmentChosen);
            int indexLocale = 0;
            for (Doctor doctor : doctorsAvailableToTreat) {
                indexLocale += 1;
                System.out.println(indexLocale + ". " + doctor.getDoctorName());
            }
            chosenDoctorIndex = inputService.receiveIntInput("Please choose a number associated with the applicable doctor: ");
        }
        return chosenDoctorIndex;
    }

    public void haveSpecifiedDoctorTreatPatient(int doctorIndex, String ailmentChosen) {
        int revisedDoctorIndex = (doctorIndex - 1);
        List<Doctor> doctorsAvailableToTreat = hospital.getDoctorList(ailmentChosen);

        if (doctorIndex == 0) {
            System.out.println("There is no doctor with the specializations needed");
        } else {
            Doctor doctorChosen = doctorsAvailableToTreat.get(revisedDoctorIndex);

            doctorChosen.getSimplePatientList();
            int patientOfDoctorNum = inputService.receiveIntInput("Please choose a patient of this doctor: ");

            Patient treatedPatient = doctorChosen.getPatientAtIndexLocale(patientOfDoctorNum);
            treatingPatientNotice(treatedPatient, doctorChosen);
            int newHealthValueAfterTreatment = treatedPatient.getHealthValue() + doctorChosen.getHealingPower(); // <- Treatment operation effect on health
            treatedPatient.setHealthValue(newHealthValueAfterTreatment); // <- Setting new Health value on patient
            postTreatmentStats(treatedPatient); // <- Print New Health value Post treatment
        }


    }
// STATIC METHODS
    private String evaluateSpecialty(int specialtyRef) {
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

    private void treatingPatientNotice(Patient patient, Doctor doctor) {
        System.out.println(patient.getPatientName() + " will now be treated by Dr." + doctor.getDoctorName());
        System.out.println("Treating patient ... ");
    }

    private void postTreatmentStats(Patient patient) {
        System.out.println("Patient " + patient.getPatientName() + " now has a health value of " + patient.getHealthValue() + "/100 after this treatment");
    }
}
