package com.mycompany.hospital.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mycompany.hospital.models.Appointment;
import com.mycompany.hospital.models.Doctor;
import com.mycompany.hospital.models.HospitalData;
import com.mycompany.hospital.models.Patient;
import com.mycompany.hospital.utils.TextUtils;

public class AppointmentService {
    private HospitalData data;
    private Scanner scanner;

    public AppointmentService(HospitalData data, Scanner scanner) {
        this.data = data;
        this.scanner = scanner;
    }

    public void manageAppointments() throws IOException, InterruptedException, ParseException {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Управление приёмами ===");

            ArrayList<String> menu = new ArrayList<>();
            menu.add("Записать пациента на приём");
            menu.add("Показать все приёмы");
            menu.add("Удалить приём");
            menu.add("Назад");

            int choice = TextUtils.createMenu(menu);

            switch (choice) {
                case 1:
                    createAppointment();
                    break;
                case 2:
                    listAppointments();
                    break;
                case 3:
                    deleteAppointment();
                    break;
                case 4:
                    back = true;
                    break;
            }
        }
    }

    private void createAppointment() throws IOException, InterruptedException, ParseException {
        System.out.println("\nВыбор пациента:");
        Patient patient = pickPatient();

        System.out.println("\nВыбор врача:");
        Doctor doctor = pickDoctor();

        System.out.print("\nВведите дату приёма (ГГГГ-ММ-ДД HH:MM): ");
        String dateStr = scanner.nextLine();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr);

        Appointment appointment = new Appointment(patient.getId(), doctor.getId(), date);
        data.getAppointments().add(appointment);

        TextUtils.message = "Пациент успешно записан на приём!";
    }

    private void listAppointments() throws IOException {
        if (data.getAppointments().isEmpty()) {
            TextUtils.message = "Приёмов нет!";
            return;
        }        
        TextUtils.message = data.getAppointments().toString();
    }

    private void deleteAppointment() throws IOException, InterruptedException {
        if (data.getAppointments().isEmpty()) {
            TextUtils.message = "Нет назначений для удаления.";
            return;
        }

        Appointment appointment = pickAppointment();
        data.getAppointments().remove(appointment);

        TextUtils.message = "Назначение удалено.";
    }

    private Appointment pickAppointment() throws IOException, InterruptedException {
        if(data.getAppointments().isEmpty()){
            TextUtils.message = "Назначений нет!";
        }

        int idx = TextUtils.createMenu(TextUtils.objArrToStrArray(data.getAppointments()));
        return data.getAppointments().get(idx - 1);
    }

    private Patient pickPatient() throws IOException, InterruptedException {
        if (data.getPatients().isEmpty()) {
            TextUtils.message = "Пациентов нет в больнице!";
            throw new IOException();
        }

        int idx = TextUtils.createMenu(TextUtils.objArrToStrArray(data.getPatients()));
        return data.getPatients().get(idx - 1);
    }

    private Doctor pickDoctor() throws IOException, InterruptedException {
        if (data.getDoctors().isEmpty()) {
            TextUtils.message = "Врачей нет в больнице!";
            throw new IOException();
        }

        int idx = TextUtils.createMenu(TextUtils.objArrToStrArray(data.getDoctors()));
        return data.getDoctors().get(idx - 1);
    }

    private String findPatientName(String id) throws IOException {
        for(Patient patient : data.getPatients()){
            if(patient.getId().equals(id))
                return patient.getFullName();
        }
        TextUtils.message = "Пациента с данным id не существует!";
        throw new IOException();
    }

    private String findDoctorName(String id) throws IOException {
        for(Doctor doctor : data.getDoctors()){
            if(doctor.getId().equals(id))
                return doctor.getFullName();
        }
        TextUtils.message = "Доктора с данным id не существует!";
        throw new IOException();
    }
}

