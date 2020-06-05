package com.e.myapplication;

class Employportalupload {

    String name; String qualification; String address; String dttm; String num;


    public Employportalupload(String namee, String qualificationn, String addresss, String dttmm, String numm) {
        this.name=namee;
        this.qualification=qualificationn;
        this.address=addresss;
        this.dttm=dttmm;
        this.num=numm;

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
