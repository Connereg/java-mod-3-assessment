public class HospitalWorldPrinter {
    //PROPS
    private Hospital hospital;

    //Constructor
    public HospitalWorldPrinter(Hospital hospital) {
        this.hospital = hospital;
    }

    //METHODS
    public void printTheHospital() {
        System.out.println((hospital.getHospitalName()).toUpperCase() + " HOSPITAL DETAILS: ");
        for (String key : (hospital.getExpertiseMap().keySet())) {
            System.out.println(key.toUpperCase() + " Doctors: ");
            for (Doctor doctor : hospital.getExpertiseMap().get(key)) {
                doctor.printPatientList();
            }
        }


    }

}
