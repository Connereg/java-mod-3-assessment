
    public class HospitalService {

        public void restoreHospitalDataInstruction() {
            System.out.println("Hello User, would you like to restore your last Hospital Session?");
            System.out.println("" +
                    "y / n");
        }

        public void separatorLine() {
            System.out.println("****************************************************");
        }

        public void hospitalMakerInstructions() {
            System.out.println("Welcome to the Hospital Constructor Application");
            System.out.println("Please name your new hospital below: ");

        }

        public void doctorMakerInstructions() {
            System.out.println("Please create a doctor to add to the hospital");

        }

        public void doctorSpecialtyInstructions() {
            System.out.println("Now give your new doctor addition a specialty");
            System.out.println("Please Choose from one of the Specialties Listed ");
            System.out.println("1. Common Cold \n 2. Body Trauma \n 3. Lime Disease");

        }

        public void patientMakerInstructions() {
            System.out.println("Please create a Patient to add to the hospital");
            System.out.println("Please name the Patient below: ");
        }

        public void patientMedicalNeedsInstructions() {
            System.out.println("Now give your new patient addition an ailment");
            System.out.println("Please Choose from one of the Ailments Listed ");
            System.out.println("1. Common Cold \n 2. Body Trauma \n 3. Lime Disease");
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

//        public int evaluateDoctorHealingPower(String specString) {
//            int returnedHealPowerNum = 0;
//
//            switch (specString) {
//                case "Common Cold":
//                    // FOR COMMON COLD
//                    returnedHealPowerNum = 10;
//                    break;
//                case "Body Trauma":
//                    // FOR BODY TRAUMA
//                    returnedHealPowerNum = 5;
//                    break;
//                case "Lime Disease":
//                    //LIME DISEASE CAN ONLY GET WORSE, EVEN WHEN TREATED
//                    returnedHealPowerNum = -10;
//                    break;
//            }
//            return returnedHealPowerNum;
//        }



        public void chooseAilmentToTreat() {
            System.out.println("Choose an ailment to treat: ");
            System.out.println("1. Common Cold \n 2. Body Trauma \n 3. Lime Disease");
        }

        public void chooseDoctorForTreatmentInstructions() {
            System.out.println("Choose a doctor to treat a current patient of this ailment (Please Pick a number associated with a doctor!): ");
        }

        public void choosePatientForDoctorToTreatInstructions() {
            System.out.println("Please choose a patient of this doctor: ");

        }

        public void chosenAilmentReported(String ailmentString) {
            System.out.println("We will now perform a treatment for " + ailmentString);
        }

        public void treatingPatientNotice(Patient patient, Doctor doctor) {
            System.out.println(patient.getPatientName() + " will now be treated by Dr." + doctor.getDoctorName());
            System.out.println("Treating patient ... ");
        }

        public void postTreatmentStats(Patient patient) {
            System.out.println("Patient " + patient.getPatientName() + " now has a health value of " + patient.getHealthValue() + "/100 after this treatment");
        }
    }

