package com.example.mtmi.firebaseexample.dataClasses;

/**
 * Created by glsmy on 6.09.2016.
 */
public class Person {

    String ad,soyad,tc_no,tel_no,sifre,email;

    public Person(String ad, String email, String sifre, String soyad, String tc_no, String tel_no) {
        this.ad = ad;
        this.email = email;
        this.sifre = sifre;
        this.soyad = soyad;
        this.tc_no = tc_no;
        this.tel_no = tel_no;
    }
}
