package com.mycompany.hospital.models;

import java.util.List;

public class HospitalData {
        private List<Patient> patients;
        private List<Doctor> doctors;
        private List<Appointment> appointments;
        
        public HospitalData() {}
        
        public HospitalData(List<Patient> patients, List<Doctor> doctors, List<Appointment> appointments) {
            this.patients = patients;
            this.doctors = doctors;
            this.appointments = appointments;
        }
        
        public List<Patient> getPatients() { return patients; }
        public void setPatients(List<Patient> patients) { this.patients = patients; }
        
        public List<Doctor> getDoctors() { return doctors; }
        public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }
        
        public List<Appointment> getAppointments() { return appointments; }
        public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
