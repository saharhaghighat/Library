import java.util.Objects;
import java.util.regex.Pattern;

public class User  {
    private String FirstName;
    private String lastName;
    private String NationalCode;
    private Account account;

    private Book[] BorrowedBooks;

    public User(String firstName, String lastName, String NationalCode, String password) {
        this.account = new Account(NationalCode, password);
        setFirstName(firstName);
        setLastName(lastName);
        setNationalCode(NationalCode);
    }

    public void setFirstName(String FirstName) {
        if (FirstName.length() > 2)
            this.FirstName = FirstName;
        else
            throw new IllegalArgumentException("Invalid FirstName");
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 3)
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Invalid FirstName");
    }

    public void setNationalCode(String nationalCode) {
        if (nationalCode.length()>5)
            this.NationalCode = nationalCode;
        else
            throw new IllegalArgumentException("Invalid NationalCode");


    }


    public boolean Login(String username, String password) {
        if (Objects.equals(this.NationalCode, username))
            //check password
            return account.ValidatePassword(password);
        return false;

    }

    public void borrow(Book book) {
        if (this.BorrowedBooks == null)
            this.BorrowedBooks = new Book[3];
        if (book.getStock() < 1)
            System.err.println("this book is unavailable.");

        else
            AddBook(book);
    }

    public void returnBook(Book book) {
        if (!isBorrowed(book))
            System.err.println("this book is not in your borrowed list");
        else
            for (int i = 0; i < BorrowedBooks.length; i++) {
                if (BorrowedBooks[i].getId() == book.getId()) {
                    BorrowedBooks[i] = null;
                }
            }
    }

    private boolean isBorrowed(Book book) {
        for (int i = 0; i < BorrowedBooks.length; i++) {
            if (Objects.nonNull(BorrowedBooks[i]) && BorrowedBooks[i].getId() == book.getId()) {
                return true;
            }
        }
        return false;
    }

    public void showBorrowedBooks() {
        if (Objects.isNull(BorrowedBooks)) {
            System.err.println("you have no book in your list ");
        } else
            for (int i = 0; i < BorrowedBooks.length; i++) {
                if (Objects.nonNull(BorrowedBooks[i])) {
                    System.out.println(i + 1 + ":" + BorrowedBooks[i]);
                }
                }
            }
    public void AddBook (Book book){
        if (isBorrowed(book))
            System.err.println("this book is already exist in your borrowed list.");
        else {
            boolean isFull = true;
            for (int i = 0; i < 3; i++) {
                if (BorrowedBooks[i] == null) {
                    BorrowedBooks[i] = book;
                    isFull = false;
                    break;
                }
            }
            if (isFull)
                System.err.println("you should return a book to borrow another one.");
        }
    }
}