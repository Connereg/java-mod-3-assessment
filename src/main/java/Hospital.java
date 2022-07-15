import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hospital {
    // Has a name, and list of patients, and a list of doctors
    private String hospitalName;
    // Define location of Map
    private Map<String, List<Doctor>> doctorExpertiseMap;

    //Constructor
    public Hospital(String hospitalNameInput) {
        this.hospitalName = hospitalNameInput;
        // Build the map for location
        this.doctorExpertiseMap = new HashMap<>();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public List<Doctor> getDoctorList(String expertiseName) {
        return doctorExpertiseMap.get(expertiseName);
    }

    public void addDoctorToExpertiseMap(String expertise, Doctor doctor) {
        List<Doctor> specialtyList = doctorExpertiseMap.get(expertise);
        //   Checking to see if there is a key designated to specialty
        if (specialtyList == null) {
            specialtyList = new ArrayList<Doctor>();
            specialtyList.add(doctor);
            this.doctorExpertiseMap.put(expertise, specialtyList);
        }
        else {
            specialtyList.add(doctor);
        }
        // Check if expertise exists
        // EXISTS: Get list of doctors from expertise
        // Add doctor to the list of doctors
        // NO EXIST (Map.get(expertise) returns null): Adding to the map new expertise(key) with an empty list of doctors
        // - Add doctor to the empty list
    }


    public void addDoctorsToHospital( String expertise, List<Doctor> doctorList ) {
        doctorExpertiseMap.put(expertise, doctorList);
    }

    public Map<String, List<Doctor>> getExpertiseMap() {
        return this.doctorExpertiseMap;
    }

}
