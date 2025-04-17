package com.mycompany.hospital;

import java.io.IOException;
import java.text.ParseException;

import com.mycompany.hospital.services.HospitalService;
import com.mycompany.hospital.utils.TextUtils;

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
        } catch(ParseException e){
            TextUtils.message = e.getMessage();
        }
    }
}
