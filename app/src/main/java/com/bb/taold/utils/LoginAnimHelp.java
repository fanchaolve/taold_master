package com.bb.taold.utils;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by fancl on 2016/12/29.
 */

public class LoginAnimHelp {


    AnimatorSet set = new AnimatorSet();

    private ArrayList<Animator> list = new ArrayList<>();

    public LoginAnimHelp() {

    }

    public void addTranslationY(View v, float start, float end) {
        list.add(ObjectAnimator.ofFloat(v, "translationY", start, end));

    }

    public void addScaleX(View v, float start, float end) {
        list.add(ObjectAnimator.ofFloat(v, "scaleX", start, end));
    }

    public void addScaleY(View v, float start, float end) {
        list.add(ObjectAnimator.ofFloat(v, "scaleY", start, end));
    }

    public void addRotation(View v, float start, float end) {
        list.add(ObjectAnimator.ofFloat(v, "rotation", start, end));
    }

    public void startAnim(int time) {
        set.playTogether(list);
        set.setDuration(time);
        set.start();
    }

}
