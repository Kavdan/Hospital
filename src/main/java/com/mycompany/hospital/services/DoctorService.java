package com.mycompany.hospital.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mycompany.hospital.models.Doctor;
import com.mycompany.hospital.models.HospitalData;
import com.mycompany.hospital.models.Patient;
import com.mycompany.hospital.utils.TextUtils;

public class DoctorService {
    private HospitalData data;
    private Scanner scanner;

    public DoctorService(HospitalData data, Scanner scanner) {
        this.data = data;
        this.scanner = scanner;
    }

    public void manageDoctors() throws IOException, InterruptedException {
        boolean back = false;
        while (!back) {
            System.out.println("\r\n=== Управление врачами ===");

            ArrayList<String> menu = new ArrayList<String>();
            menu.add("Добавить врача");
            menu.add("Вывести список врачей");
            menu.add("Найти врача по ФИО");
            menu.add("Обновить данные Врача");
            menu.add("Удалить Врача");
            menu.add("Назад");

            int choice = TextUtils.createMenu(menu);

            switch (choice) {
                case 1: {
                    addDoctor();
                    break;
                }
                case 2: {
                    listDoctors();
                    break;
                }
                case 3: {
                    findDoctorsByFullName();
                    break;
                }
                case 4: {
                    updateDoctor();
                    break;
                }
                case 5: {
                    deleteDoctor();
                    break;
                }
                case 6: {
                    back = true;
                    break;
                }
            }
        }
    }

    private void addDoctor() {
        System.out.println("\r\n=== Добавление нового врача ===");

        System.out.print("ФИО: ");
        String fullname = scanner.nextLine();

        System.out.print("Дата рождения (ГГГГ-ММ-ДД): ");
        String birthDate = scanner.nextLine();

        System.out.print("Номер телефона: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Специализация: ");
        String specialization = scanner.nextLine();

        System.out.print("График работы (пн: 09.00-18.00, ...): ");
        String schedule = scanner.nextLine();

        Doctor doctor = new Doctor(fullname, specialization, schedule, birthDate, phoneNumber);
        data.getDoctors().add(doctor);

        TextUtils.message = "Пациент успешно добавлен с ID: " + doctor.getId();
    }

    private void listDoctors() {
        if (data.getDoctors().isEmpty()) {
            TextUtils.message = "Докторов нет!";
        } else {
            TextUtils.message = data.getDoctors().toString();
        }
    }

    private void findDoctorsByFullName() {
        System.out.print("\r\nВведите Ф/И/О доктора: ");
        String searchQuery = scanner.next();

        List<String> doctors = new ArrayList<>();

        for (Doctor doctor : data.getDoctors()) {
            if (doctor.getFullName().contains(searchQuery)) {
                doctors.add(doctor.toString());
            }
        }

        if (doctors.size() > 0) {
            TextUtils.message = doctors.toString();
        } else
            TextUtils.message = "Врач с данным id не найден!";
    }

    private Doctor pickDoctor() throws IOException, InterruptedException{
        List<Doctor> doctors = data.getDoctors();
        if(doctors.size() == 0) {
            TextUtils.message = "В больнице нет докторов!";
            throw new IOException();
        }

        int doctorNum = TextUtils.createMenu(TextUtils.objArrToStrArray(doctors));
        if (doctorNum > doctors.size()) {
            TextUtils.message = "Такого доктора не существует!";
            throw new IOException(); 
        }

        return doctors.get(doctorNum - 1);
    }

    private void updateDoctor() throws IOException, InterruptedException {
        System.out.print("\r\nВыберите доктора: ");

        Doctor doctor = pickDoctor();

        System.out.print("\rНовое ФИО врача " + doctor.getFullName() + ": ");
        doctor.setFullName(scanner.nextLine());

        System.out.print("\rНовая дата рождения (ГГГГ-ММ-ДД) " + doctor.getBirthDate() + ": ");
        doctor.setBirthDate(scanner.nextLine());

        System.out.print("\rНовый номер (" + doctor.getPhoneNumber() + "): ");
        doctor.setPhoneNumber(scanner.nextLine());

        System.out.print("\rНовая специализация (" + doctor.getSpecialization() + "): ");
        doctor.setSpecialization(scanner.nextLine());

        System.out.print("\rНовый график работы (" + doctor.getSchedule() + "): ");
        doctor.setSchedule(scanner.next(doctor.getSchedule()));

        TextUtils.message = "Новые данные врача: " + doctor.toString();
        return;
    }

    private void deleteDoctor() throws IOException, InterruptedException {
        System.out.print("\r\nВыберите врача для удаления: ");
        Doctor doctor = pickDoctor();

        if (data.getDoctors().removeIf(p -> p.getId().equals(doctor.getId()))) {
            data.getAppointments().removeIf(a -> a.getDoctorId().equals(doctor.getId()));
            TextUtils.message = "Врач и его история удалены.";
        } else {
            TextUtils.message = "Врач с id = " + doctor.getId() + " не найден.";
        }
    }
}
