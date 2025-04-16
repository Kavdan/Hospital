package com.mycompany.hospital.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mycompany.hospital.models.HospitalData;
import com.mycompany.hospital.models.Patient;
import com.mycompany.hospital.utils.TextUtils;

public class HospitalService {
    private JsonService jsonDataService;
    private HospitalData data;
    private Scanner scanner;
    private PatientService patientService;
    private DoctorService doctorService;
    private AppointmentService appointmentService;

    public HospitalService() throws IOException {
        this.jsonDataService = new JsonService();
        this.data = jsonDataService.loadData();
        this.scanner = new Scanner(System.in);

        this.patientService = new PatientService(data, scanner);
        this.doctorService = new DoctorService(data, scanner);
        this.appointmentService = new AppointmentService(data, scanner);
    }

    public void run() throws IOException, InterruptedException {
        boolean running = true;
        while (running) {
            System.out.println("\r\n=== Система управления больницей ===");

            ArrayList<String> menu = new ArrayList<String>();
            menu.add("Управление пациентами");
            menu.add("Управление докторами");
            menu.add("Управление приёмами");
            menu.add("Сохранить данные");
            menu.add("Выход");

            int choice = TextUtils.createMenu(menu);

            try {
                switch (choice) {
                    case 1: {
                        patientService.managePatients();
                        break;
                    }
                    case 2: {
                        doctorService.manageDoctors();
                        break;
                    }
                    case 3: {
                        appointmentService.manageAppointments();
                        break;
                    }
                    case 4: {
                        saveData();
                        break;
                    }
                    case 5: {
                        running = false;
                        TextUtils.clearConsole();
                        break;
                    }
                    default: {
                        System.out.println("Данной опции не существует!");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при работе с файлом: " + e.getMessage());
            }
        }
    }

    public void atExit() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                TextUtils.disableRawMode();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            TextUtils.showCursor();
        }));
    }

    private void saveData() throws IOException {
        jsonDataService.saveData(data);
        System.out.println("\rДанные успешно сохранены.");
    }
}
