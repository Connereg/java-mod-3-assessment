
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
    }



