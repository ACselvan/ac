package com.e.myapplication;

class Employportalupload {

    String name; String qualification; String address; String dttm; String num;String exp;String city;


    public Employportalupload(String namee, String qualificationn, String addresss, String dttmm, String numm,String exp,String city) {
        this.name=namee;
        this.qualification=qualificationn;
        this.address=addresss;
        this.dttm=dttmm;
        this.num=numm;
        this.exp=exp;
        this.city=city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
