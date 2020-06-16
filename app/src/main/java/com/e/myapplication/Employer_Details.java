package com.e.myapplication;

public class Employer_Details {
    private String address,dttm,name,num,qualification,exp;
    public Employer_Details() {
    }


    public Employer_Details(String address, String dttm, String name, String num, String qualification,String exp) {
        this.address = address;
        this.dttm = dttm;
        this.name = name;
        this.num = num;
        this.qualification = qualification;
        this.exp = exp;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDttm() {
        return dttm;
    }

    public void setDttm(String dttm) {
        this.dttm = dttm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
