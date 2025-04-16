package com.mycompany.hospital;

import java.io.IOException;

import com.mycompany.hospital.services.HospitalService;

public class App {
    public static void main(String[] args) {

        HospitalService hospitalService;
        try {
            hospitalService = new HospitalService();
            hospitalService.atExit();
            hospitalService.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
