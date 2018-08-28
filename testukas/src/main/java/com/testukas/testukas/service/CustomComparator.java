package com.testukas.testukas.service;

import com.testukas.testukas.entity.Studentas;

import java.util.Comparator;

public  class CustomComparator implements Comparator<Studentas> {

    @Override
    public int compare(Studentas o1, Studentas o2) {

        String fn1 = ((Studentas) o1).getPavarde();
        String fn2 = ((Studentas) o2).getPavarde();

        int res = fn1.compareTo(fn2);
        if (res != 0) {
            return res;
        } else {
            String ln1 = ((Studentas) o1).getVardas();
            String ln2 = ((Studentas) o2).getVardas();
            return ln1.compareTo(ln2);

//        return o1.getPavarde().compareTo(o2.getPavarde());
        }

    }
}
