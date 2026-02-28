package com.library.lab01.lab03;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String id;
    private String name;
    private List<LibraryItem> borrowedItems = new ArrayList<>();
    private final int BORROW_LIMIT = 3;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean canBorrow() {
        return borrowedItems.size() < BORROW_LIMIT;
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public String getName() {
        return name;
    }
}