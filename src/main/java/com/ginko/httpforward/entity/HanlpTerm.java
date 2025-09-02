package com.ginko.httpforward.entity;

public class HanlpTerm {
    private String word;
    private String nature;
    private int offset;
    private int length;

    public HanlpTerm(String word, String nature, int offset, int length) {
        this.word = word;
        this.nature = nature;
        this.offset = offset;
        this.length = length;
    }

    // getter & setter
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}