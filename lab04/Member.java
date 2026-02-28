package com.library.lab01.lab04;

public class Member {

    private String id;
    private String name;
    private int borrowedCount = 0;
    private final int BORROW_LIMIT = 3;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean canBorrow() {
        if (borrowedCount >= BORROW_LIMIT) {
            System.out.println("Member " + name + " has reached the borrow limit (3).");
            return false;
        }
        return true;
    }

    public void borrowItem() {
        borrowedCount++;
    }

    public void returnItem() {
        borrowedCount--;
    }

    public String getName() {
        return name;
    }
}