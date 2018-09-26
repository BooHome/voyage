package com.ihere.voyage.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengshibo
 * @create 2018-07-13 18:41
 * @desc ${DESCRIPTION}
 **/
public class TestEntity {

    private String s;

    private List<String> ss=new ArrayList<>();

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<String> getSs() {
        return ss;
    }

    public void setSs(List<String> ss) {
        this.ss = ss;
    }
}
