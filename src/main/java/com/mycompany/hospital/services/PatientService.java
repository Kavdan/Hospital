package com.mycompany.hospital.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mycompany.hospital.models.Doctor;
import com.mycompany.hospital.models.HospitalData;
import com.mycompany.hospital.models.Patient;
import com.mycompany.hospital.utils.TextUtils;

public class PatientService {
    private HospitalData data;
    private Scanner scanner;

    public PatientService(HospitalData data, Scanner scanner) {
        this.data = data;
        this.scanner = scanner;
    }

    public void managePatients() throws IOException, InterruptedException, ParseException {
        boolean back = false;
        while (!back) {
            System.out.println("\r\n=== Управление пациентами ===");

            ArrayList<String> menu = new ArrayList<String>();
            menu.add("Добавить пациента");
            menu.add("Просмотр всех пациентов");
            menu.add("Найти пациента по ID");
            menu.add("Обновить данные пациента");
            menu.add("Удалить пациента");
            menu.add("Назад");

            int choice = TextUtils.createMenu(menu);

            switch (choice) {
                case 1: {
                    addPatient();
                    break;
                }
                case 2: {
                    listPatients();
                    break;
                }
                case 3: {
                    findPatientByFullName();
                    break;
                }
                case 4: {
                    updatePatient();
                    break;
                }
                case 5: {
                    deletePatient();
                    break;
                }
                case 6: {
                    back = true;
                    break;
                }
            }
        }
    }

    private void addPatient() throws ParseException {
        System.out.println("\r\n=== Добавление нового пациента ===");

        System.out.print("ФИО: ");
        String fullname = scanner.nextLine();

        System.out.print("Дата рождения (ГГГГ-ММ-ДД): ");
        String date = scanner.nextLine();
        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        System.out.print("Номер телефона: ");
        String number = scanner.nextLine();

        Patient patient = new Patient(fullname, number, birthDate);
        data.getPatients().add(patient);

        TextUtils.message = "Пациент успешно добавлен с ID: " + patient.getId();
    }

    private void listPatients() {
        if (data.getPatients().isEmpty()) {
            TextUtils.message = "Пациентов нет.";
        } else {
            TextUtils.message = data.getPatients().toString();
        }
    }

    private void findPatientByFullName() {
        System.out.print("\r\nВведите Ф/И/О пациента: ");
        String searchQuery = scanner.next();

        List<String> patients = new ArrayList<>();

        for (Patient patient : data.getPatients()) {
            if (patient.getFullName().contains(searchQuery)) {
                patients.add(patient.toString());
            }
        }

        if (patients.size() > 0) {
            TextUtils.message = patients.toString();
        } else {
            TextUtils.message = "Пациет с данным id не найден!";
        }
    }

    private void updatePatient() throws IOException, InterruptedException, ParseException {
        System.out.print("\r\nВвыберите пациента: ");
        Patient patient = pickPatient();

        System.out.print("\rНовое ФИО пациента " + patient.getFullName() + ": ");
        patient.setFullName(scanner.next());

        System.out.print("\rНовая дата рождения (ГГГГ-ММ-ДД) " + patient.getBirthDate() + ": ");
        String date = scanner.nextLine();
        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        patient.setBirthDate(birthDate);

        System.out.print("\rНовый номер (" + patient.getNumber() + "): ");
        patient.setNumber(scanner.next());

        TextUtils.message = "Новые данные пациента: " + patient.toString();
    }

    private void deletePatient() throws IOException, InterruptedException {
        System.out.print("\r\nВвыберите пациента для удаления: ");
        Patient patient = pickPatient();

        if (data.getPatients().remove(patient)) {
            data.getAppointments().removeIf(a -> a.getPatientId().equals(patient.getId()));
            TextUtils.message = "Пациент и его история удалены!";
        } else {
            TextUtils.message = "Ошибка при удалении пациента!";
        }
    }

    private Patient pickPatient() throws IOException, InterruptedException {
        List<Patient> patients = data.getPatients();
        if (patients.size() == 0) {
            TextUtils.message = "В больнице нет пациентов!";
            throw new IOException();
        }

        int patientNum = TextUtils.createMenu(TextUtils.objArrToStrArray(patients));
        if (patientNum > patients.size()) {
            TextUtils.message = "Такого пациента не существует!";
            throw new IOException();
        }

        return patients.get(patientNum - 1);
    }
}
