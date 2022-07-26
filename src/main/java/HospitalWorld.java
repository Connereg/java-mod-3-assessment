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

        // COLLECTING USER INPUT
        UserInputInterface userInputInterface = new UserInputImplement();

        RunAgain runAgain = new RunAgain(userInputInterface);

        while (runAgain.isRunAgain()) {
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
                HospitalWorldPrinter hwPrinter = new HospitalWorldPrinter(hospital);
                hwPrinter.printTheHospital();

            } else if (restoreSessionInput.equals("n")) {
                // 2. NAME YOUR HOSPITAL (Hospital Class: Map<String, List<Doctor> Expertise Map,  METHODS: createPatient, createDoctor, assignDoctorToSpecialty, assignPatientToDoctor(Patient)
                hospitalService.hospitalMakerInstructions();  // <- 2.1: Issue Instruction to name Hospital
                String hospitalNameInput = scanner.nextLine(); // <- 2.2: Collect User Inout to Name Hospital World
                hospital.setHospitalName(hospitalNameInput);


                //  3. USER INPUT DEFINES 3 DOCTOR OBJECTS -> Doctor class( Doctor Class: List<Patient> patients, Specialty mySpecialty, METHODS: treatPatient(patient), removePatient(patient)

                for (int i = 0; i < 3; i++) {
                    // 3. Ask to create 3 doctors, each with a name and specialty
                    hospitalService.doctorMakerInstructions();
                    DoctorMaker doctorMaker = new DoctorMaker(userInputInterface);
                    // 4. Generate 3 doctors with the properties given by user
                    Doctor newDoctor = doctorMaker.createDoctor();

                    // 5. Add the 3 doctors to the Hospital
                    hospital.addDoctorToExpertiseMap(newDoctor.getDoctorSpecialization(), newDoctor);
                    hospitalService.separatorLine();
                }
                System.out.println("All Doctors have been added to the Hospital");
                System.out.println(hospital.getExpertiseMap());
                hospitalService.separatorLine();

                // 4. USER INPUT DEFINES 5 PATIENT OBJECTS -> Patient Class( Patient Class: int healthIndex, Ailment myDisease, METHODS: recieveTreatment(int healed), healed(), died()
                for (int k = 0; k < 5; k++) {
                    // 6. Ask the user to create 5 Patients, each with a name and specialization need
                    hospitalService.patientMakerInstructions();
                    PatientGenerator patientGenerator = new PatientGenerator(userInputInterface);
                    // 7. Generate 5 patients with the properties given by the user
                    Patient patientObject = patientGenerator.createPatient();

                    // 8. Add the patients to the DOCTOR
                    GivePatientToDoctor givePatientToDoctor = new GivePatientToDoctor(patientObject, hospital);
                    List<Doctor> applicableDoctors = givePatientToDoctor.findApplicableDoctors();

                    System.out.println("Please choose a doctor to assign the patient to: (Use the number associated with that Doctor)");
                    int chosenDoctorIndex = 0;
                    try {
                        chosenDoctorIndex = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Please use a number to pick your doctor");
                    }
                    scanner.nextLine();

                    givePatientToDoctor.retrieveDoctorForPatientViaSelection(chosenDoctorIndex, applicableDoctors);
                    givePatientToDoctor.addingPatient(); // <- DONE INTERNALLY IN ABOVE CLASS FUNCTION

                    hospitalService.separatorLine();
                }
                System.out.println("All Patients have been added to the hospital");

                // HOSPITAL WORLD PRINTER (OPTIMISTIC)
                HospitalWorldPrinter hwPrinter2 = new HospitalWorldPrinter(hospital);
                hwPrinter2.printTheHospital();

            } else {
                System.out.println("You must use a 'y' to restore a Hospital Session, or a 'n' to make a NEW Hospital Session");
            }

            hospitalService.separatorLine();
            hospitalService.separatorLine();


            // CHOOSE AILMENT TO TREAT
            TreatingProcess treatingProcess = new TreatingProcess(userInputInterface, hospital);

            String illnessTypeString = treatingProcess.treatAnIllness();

            // CHOOSE A DOCTOR TO DO TREATMENT
            int doctorIndexInt = treatingProcess.chooseDoctorOfSpecialty(illnessTypeString);

            // CHOOSE PATIENT TO TREAT FOR SPECIFIED DOCTOR
            treatingProcess.haveSpecifiedDoctorTreatPatient(doctorIndexInt, illnessTypeString);


            //6. SAVE HOSPITAL WORLD TO JSON (Rerun?)
            // JSONIFY THE HOSPITAL

            writeJson(hospital);

            // RUN AGAIN PROMPT
            runAgain.runAgainPrompt();
            // END OF WHILE LOOP
        }
        scanner.close();
    }


        // WORLD METHODS

        public static void writeJson (Hospital hospital) throws JsonProcessingException {
            String json = new ObjectMapper().writeValueAsString(hospital);

            try (FileWriter fw = new FileWriter("Hospital.json");) {
                fw.write(json);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 1. RETRIEVE JSON FROM FILE
        // 2. TRANSLATE JSON TO OBJECT
        // 3. RETURN OBJECT
        public static Hospital readJson () throws IOException {
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