package com.zayh.test.bean;

/**
 * Created by guozhk on 2017/6/17.
 */

public class ActivityBrean {
    private Class cl;
    private String dre;

    public ActivityBrean() {
    }

    public ActivityBrean(Class cl, String dre) {
        this.cl = cl;
        this.dre = dre;
    }

    public Class getCl() {
        return cl;
    }

    public void setCl(Class cl) {
        this.cl = cl;
    }

    public String getDre() {
        return dre;
    }

    public void setDre(String dre) {
        this.dre = dre;
    }
}
