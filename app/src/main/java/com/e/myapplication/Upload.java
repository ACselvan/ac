package com.e.myapplication;

class Upload {
String Name,Address,Timing,Contact_number,Category,Firmname,Description,Proprietor_name,horoscopedescription,imageurl;

    public Upload(String name) {
        this.Name=name;


    }

    public Upload() {
    }

    public Upload(String Firmname, String address, String contact_number, String Category,String Description,String Proprietor_name) {
        this.Firmname = Firmname;
        this.Category=Category;
        this.Address=address;
        this.Contact_number=contact_number;
        this.Description=Description;
        this.Proprietor_name=Proprietor_name;
    }

    public Upload(String horoscopedescription, String imageurl) {
        this.horoscopedescription = horoscopedescription;
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getFirmname() {
        return Firmname;
    }

    public void setFirmname(String firmname) {
        Firmname = firmname;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getProprietor_name() {
        return Proprietor_name;
    }

    public void setProprietor_name(String proprietor_name) {
        Proprietor_name = proprietor_name;
    }

    public String getContact_number() {
        return Contact_number;
    }

    public void setContact_number(String contact_number) {
        Contact_number = contact_number;
    }
}
