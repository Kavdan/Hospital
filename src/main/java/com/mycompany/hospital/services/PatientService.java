package com.mycompany.hospital.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mycompany.hospital.models.Doctor;
import com.mycompany.hospital.models.HospitalData;
import com.mycompany.hospital.models.Patient;
import com.mycompany.hospital.utils.TextUtils;

public class PatientService {
    private HospitalData data;
    private Scanner scanner;

    public PatientService(HospitalData data, Scanner scanner){
        this.data = data;
        this.scanner = scanner;
    }

    public void managePatients() throws IOException, InterruptedException {
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

    private void addPatient() {
        System.out.println("\r\n=== Добавление нового пациента ===");

        System.out.print("ФИО: ");
        String fullname = scanner.nextLine();

        System.out.print("Дата рождения (ГГГГ-ММ-ДД): ");
        String birthDate = scanner.nextLine();

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
        } 
        else{
            TextUtils.message = "Пациет с данным id не найден!";
        }
    }

    private void updatePatient() {
        System.out.print("\r\nВведите ID пациента: ");
        String id = scanner.next();

        for (Patient patient : data.getPatients()) {
            if (patient.getId().equals(id)) {
                System.out.print("\rНовое ФИО пациента " + patient.getFullName() + ": ");
                patient.setFullName(scanner.next());

                System.out.print("\rНовая дата рождения (ГГГГ-ММ-ДД) " + patient.getBirthDate() + ": ");
                patient.setBirthDate(scanner.nextLine());

                System.out.print("\rНовый номер (" + patient.getNumber() + "): ");
                patient.setNumber(scanner.next());

                TextUtils.message = "Новые данные пациента: " + patient.toString();
                return;
            }
        }

        TextUtils.message = "Пациент с id = " + id + " не был найден!";
    }

    private void deletePatient() {
        System.out.print("\r\nВведите ID пациента для удаления: ");
        String id = scanner.next();

        if (data.getPatients().removeIf(p -> p.getId().equals(id))) {
            data.getAppointments().removeIf(a -> a.getPatientId().equals(id));
            TextUtils.message = "Пациент и его история удалены.";
        } else {
            TextUtils.message = "Пациент с id = " + id + " не найден.";
        }
    }
}
