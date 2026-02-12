package Lab02;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String memberId;
    private String name;
    private List<Book> borrowedBooks;
    private static final int MAX_BORROW = 3;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {

        if (borrowedBooks.size() >= MAX_BORROW) {
            System.out.println("Member " + name + " has reached the borrow limit (3).");
            System.out.println("Borrow request denied for member " + name + ".");
            return;
        }

        if ("Borrowed".equals(book.getStatus())) {
            book.checkOut(this); // จะไปเข้า error ใน Book
            return;
        }

        book.checkOut(this);
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        book.returnBook();
        borrowedBooks.remove(book);
    }
}
