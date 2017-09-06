package com.bb.taold.pay;

/**
 * Created by Administrator on 2017/3/9.
 */

public class PayFactory {

    private Pay pay;


    public PayFactory() {

    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public <T> void factory(T t) {
        if (pay != null) {
            pay.pay(t);
        }
    }

}
