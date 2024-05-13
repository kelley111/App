package com.example.myapplication9;

public class RateItem {
    private int id;

    private  String cname;

    public void setId(int id) {
        this.id = id;
    }

    private String cval;

    public String getCval() {
        return cval;
    }

    public void setCval(String cval) {
        this.cval = cval;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getId() {
        return id;
    }

    public  RateItem(){
        cname = "";
        cval= "0f";
    }
    public RateItem(String cname,String cval){
        this.cname=cname;
        this.cval=cval;
    }
}
