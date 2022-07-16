import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.List;
import java.util.HashSet;


public class HospitalWorld {
    public static void main(String[] args) throws JsonProcessingException {
        //    1.  INITIALIZE HOSPITAL WORLD (Runner: readFromJson, writeHospitalToJson(Hospital)
        Scanner scanner = new Scanner(System.in); // <- 1.2: Initialize Scanner to Recieve User Input
        HospitalService hospitalService = new HospitalService(); //  <- 1.1:  Initialize Hospital Service to be able to issue instructions
        hospitalService.restoreHospitalDataInstruction();
        String restoreSessionInput = scanner.nextLine();

        Hospital hospital = new Hospital();

        if (restoreSessionInput.equals("y")) {
            try {
                 hospital = readJson();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // HOSPITAL WORLD PRINTER (PESSIMISTIC)
            System.out.println((hospital.getHospitalName()).toUpperCase() + " HOSPITAL DETAILS: ");
            for (String key : (hospital.getExpertiseMap().keySet())) {
                System.out.println(key.toUpperCase() + " Doctors: ");
                for (Doctor doctor : hospital.getExpertiseMap().get(key)) {
                    doctor.printPatientList();
                }
            }
        }
        else if (restoreSessionInput.equals("n")) {
            // 2. NAME YOUR HOSPITAL (Hospital Class: Map<String, List<Doctor> Expertise Map,  METHODS: createPatient, createDoctor, assignDoctorToSpecialty, assignPatientToDoctor(Patient)
            hospitalService.hospitalMakerInstructions();  // <- 2.1: Issue Instruction to name Hospital
            String hospitalNameInput = scanner.nextLine(); // <- 2.2: Collect User Inout to Name Hospital World
            hospital.setHospitalName(hospitalNameInput);

//      Ailment cold = new Ailment("Cold", 20);     //  <- Initializing instance of Ailment, LIME WILL NOT BE TREATABLE
//      Ailment bodyTrauma = new Ailment("Body Trauma", 50);
//      Ailment lime = new Ailment("Lime Disease", 30);
//        boolean runAgain = true;

            //  3. USER INPUT DEFINES 3 DOCTOR OBJECTS -> Doctor class( Doctor Class: List<Patient> patients, Specialty mySpecialty, int healingPower, METHODS: treatPatient(patient), removePatient(patient)
            for (int i = 0; i < 3; i++) {
                // 3. Ask to create 3 doctors, each with a name and specialty
                hospitalService.doctorMakerInstructions();
                String doctorNameInput = scanner.nextLine();  // <- Recieve Doctor Name
                hospitalService.doctorSpecialtyInstructions();
                int doctorSpecialtyInput = scanner.nextInt(); // <- Recieve Doctor Specialty Input
                String specialtyString = hospitalService.evaluateSpecialty(doctorSpecialtyInput); // Get Name of Ailment from Selections Provided
                int docHealingPower = hospitalService.evaluateDoctorHealingPower(specialtyString);
                scanner.nextLine(); // <- TO CLEAR SCANNER FOR LOOP

                // 4. Generate 3 doctors with the properties given by user
                Doctor doctorObject = new Doctor(doctorNameInput, specialtyString, docHealingPower);

                // 5. Add the 3 doctors to the Hospital
                hospital.addDoctorToExpertiseMap(specialtyString, doctorObject);
                hospitalService.separatorLine();
            }
            System.out.println("All Doctors have been added to the Hospital");
            System.out.println(hospital.getExpertiseMap());
            hospitalService.separatorLine();

            // 4. USER INPUT DEFINES 5 PATIENT OBJECTS -> Patient Class( Patient Class: int healthIndex, Ailment myDisease, METHODS: recieveTreatment(int healed), healed(), died()
            for (int k = 0; k < 5; k++) {
                // 6. Ask the user to create 5 Patients, each with a name and specialization need
                hospitalService.patientMakerInstructions();
                String patientNameInput = scanner.nextLine();
                hospitalService.patientMedicalNeedsInstructions();
                int patientAilmentInput = scanner.nextInt();
                String patientAilmentString = hospitalService.evaluateSpecialty(patientAilmentInput);
                int patientStartingHealthValue = hospitalService.evaluatePatientStartingHealthValue(patientAilmentString);

                // 7. Generate 5 patients with the properties given by the user
                Patient patientObject = new Patient(patientNameInput, patientAilmentString, patientStartingHealthValue);

                // 8. Add the patients to the DOCTOR
                if (hospital.getExpertiseMap().containsKey(patientObject.getMedicalNeeds())) {
                    List<Doctor> doctorsOfSpecialty = hospital.getDoctorList(patientObject.getMedicalNeeds());
                    System.out.println("Available Doctors with required specialization (Please Pick a number associated with a doctor!)");
                    int indexLocale = 0;
                    for (Doctor doctor : doctorsOfSpecialty) {
                        indexLocale += 1;
                        System.out.println(indexLocale + ". " + doctor.getDoctorName());
                    }
                    System.out.println("Please choose a doctor to assign the patient to: (Use the number associated with that Doctor)");
                    int chosenDoctorIndex = 0;
                    try {
                        chosenDoctorIndex = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Please use a number to pick your doctor");
                    }
                    scanner.nextLine();

                    if (chosenDoctorIndex == 0) {
                        System.out.println("There is no doctor with the specializations needed");
                    } else {

                        Doctor doctorChosen = doctorsOfSpecialty.get((chosenDoctorIndex - 1));
                        doctorChosen.addPatientToDoctor(patientObject);
                        System.out.println(patientObject.getPatientName() + " has been give to " + doctorChosen.getDoctorName() + " as a patient!");
                    }
                } else {
                    System.out.println("The Medical system can't support this individuals needs :( ");
                }
                hospitalService.separatorLine();
            }
            System.out.println("All Patients have been added to the hospital");

            // HOSPITAL WORLD PRINTER (OPTIMISTIC)
            System.out.println((hospital.getHospitalName()).toUpperCase() + " HOSPITAL DETAILS: ");
            for (String key : new HashSet<String>(hospital.getExpertiseMap().keySet())) {
                System.out.println(key.toUpperCase() + " Doctors: ");
                for (Doctor doctor : hospital.getExpertiseMap().get(key)) {
                    doctor.printPatientList();
                }
            }
        } else {

            System.out.println("You must use a 'y' to restore a Hospital Session, or a 'n' to make a NEW Hospital Session");
        }

        hospitalService.separatorLine();
        hospitalService.separatorLine();



        hospitalService.separatorLine();

        // CHOOSE AILMENT TO TREAT
        hospitalService.chooseAilmentToTreat();
        int ailmentToTreatChoice = scanner.nextInt();
        String ailmentChosen = hospitalService.evaluateSpecialty(ailmentToTreatChoice);
        hospitalService.chosenAilmentReported(ailmentChosen);

        // CHOOSE PATIENT TO TREAT FOR SPECIFIED DOCTOR
        hospitalService.chooseDoctorForTreatmentInstructions();
        if (hospital.getExpertiseMap().containsKey(ailmentChosen)) {
            List<Doctor> doctorsAvailableToTreat = hospital.getDoctorList(ailmentChosen);
            int indexLocale = 0;
            for (Doctor doctor : doctorsAvailableToTreat) {
                indexLocale += 1;
                System.out.println(indexLocale + ". " + doctor.getDoctorName());
            }
            int chosenDoctorIndex = 0;
            try {
                chosenDoctorIndex = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please use a number to pick your doctor");
            }
            scanner.nextLine();

            if (chosenDoctorIndex == 0) {
                System.out.println("There is no doctor with the specializations needed");
            } else {
                Doctor doctorChosen = doctorsAvailableToTreat.get((chosenDoctorIndex - 1));
                hospitalService.choosePatientForDoctorToTreatInstructions();
                doctorChosen.getSimplePatientList();
                int patientOfDoctorNum = scanner.nextInt();
                Patient treatedPatient = doctorChosen.getPatientAtIndexLocale(patientOfDoctorNum);
                hospitalService.treatingPatientNotice(treatedPatient, doctorChosen);
                int newHealthValueAfterTreatment = treatedPatient.getHealthValue() + doctorChosen.getHealingPower(); // <- Treatment operation effect on health
                treatedPatient.setHealthValue(newHealthValueAfterTreatment); // <- Setting new Health value on patient
                hospitalService.postTreatmentStats(treatedPatient); // <- Print New Health value Post treatment
            }

            //6. SAVE HOSPITAL WORLD TO JSON (Rerun?)
            // JSONIFY THE HOSPITAL

            writeJson(hospital);

            scanner.close();
        }
    }

    // WORLD METHODS

    public static void writeJson(Hospital hospital) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(hospital);
        System.out.println(json);

        try (FileWriter fw = new FileWriter("Hospital.json");){
            fw.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

 // 1. RETRIEVE JSON FROM FILE
    // 2. TRANSLATE JSON TO OBJECT
    // 3. RETURN OBJECT
    public static Hospital readJson() throws IOException {
        Path filePath = Path.of("Hospital.json");

        String content = Files.readString(filePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Hospital.class);
    }
}

//  PSUEDOCODE:
/*
   1.  INITIALIZE HOSPITAL WORLD (Runner: readFromJson, writeHospitalToJson(Hospital)

   2. NAME YOUR HOSPITAL (Hospital Class: Map<String, List<Doctor> Expertise Map,  METHODS: createPatient, createDoctor, assignDoctorToSpecialty, assignPatientToDoctor(Patient)

   3. USER INPUT DEFINES 3 DOCTOR OBJECTS -> Doctor class( Doctor Class: List<Patient> patients, Specialty mySpecialty, int healingPower, METHODS: treatPatient(patient), removePatient(patient)

   4. USER INPUT DEFINES 5 PATIENT OBJECTS -> Patient Class( Patient Class: int healthIndex, Ailment myDisease, METHODS: recieveTreatment(int healed), healed(), died()

   5. PRINT WORLD AS IS AFTER DISTRIBUTING USER INPUT (OPTIMISTIC)

   6. SAVE HOSPITAL WORLD TO JSON (Rerun?)


 */