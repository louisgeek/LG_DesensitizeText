package com.louis.mykttest;

import android.graphics.Rect;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by louisgeek on 2021/6/15.
 */
public class DesensitizeTransformationMethod implements TransformationMethod {
    private static final String TAG = "DesensitizeTransformati";
    //*
    private static final char DOT_CHAR = '*';
    // private static final char DOT_CHAR = '#';
    // private static final char DOT_CHAR = '\u2022';
    private int startIndex = Integer.MAX_VALUE;
    private int endIndex = Integer.MAX_VALUE;

    public DesensitizeTransformationMethod() {
    }

    public DesensitizeTransformationMethod(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * [startIndex,endIndex)
     * <p>
     * DesensitizeTransformationMethod() => 朱 -> 朱
     * DesensitizeTransformationMethod(0, 1) => 朱凯 -> *凯
     * DesensitizeTransformationMethod(1, 2) => 方凯翔 -> 方*翔
     * DesensitizeTransformationMethod(1, 3) => 诸葛孔明 -> 诸**明
     * DesensitizeTransformationMethod(4, 14) => 330741199908295219 -> 3307**********5219
     * DesensitizeTransformationMethod(3, 7) => 18458234508 -> 184****4508
     *
     * @param startIndex
     * @param endIndex
     */
    public DesensitizeTransformationMethod(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }


    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        desensitizeCharSequence = new DesensitizeCharSequence(source);
        return desensitizeCharSequence;
    }

    @Override
    public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {

    }


    private DesensitizeCharSequence desensitizeCharSequence;

    private class DesensitizeCharSequence implements CharSequence {
        private final CharSequence rawCharSequence;

        public DesensitizeCharSequence(CharSequence rawCharSequence) {
            this.rawCharSequence = rawCharSequence;
        }

        @Override
        public int length() {
            return rawCharSequence.length();
        }

        @Override
        public char charAt(int index) {
            if (index >= startIndex && index < endIndex) {
                return DOT_CHAR;
            }
            return rawCharSequence.charAt(index);
        }

        @NonNull
        @Override
        public CharSequence subSequence(int start, int end) {
            //return rawCharSequence.subSequence(start, end);
            char[] tempCharArr = new char[end - start];
            int destoff = 0;
            TextUtils.getChars(rawCharSequence, start, end, tempCharArr, destoff);
            for (int i = start; i < end; i++) {
                tempCharArr[i - start + destoff] = charAt(i);
            }
            return new String(tempCharArr);
        }

    }

    public CharSequence getText() {
        return desensitizeCharSequence.subSequence(0, desensitizeCharSequence.length()).toString();
    }

    public CharSequence getRawText() {
        CharSequence rawCharSequence = desensitizeCharSequence.rawCharSequence;
        return rawCharSequence.subSequence(0, rawCharSequence.length());
    }
}