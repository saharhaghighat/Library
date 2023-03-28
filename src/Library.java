import java.util.Objects;
import java.util.Scanner;

public class Library {
    private static final int LOGIN = 1;
    private static final int REGISTER = 2;

    private static final int SHOW_BOOKS = 1;
    private static final int SHOW_BORROWED_BOOKS = 2;
    private static final int BORROW_BOOK = 3;
    private static final int RETURN_BOOK = 4;

    private static final int EXIT = 0;
    private static final int LOGOUT = 9;
    private boolean isExit = false;

    private final Book[] books = new Book[5];

    private User[] users = new User[100];


    private User currentUser;


    public void run() {
        while (Objects.isNull(currentUser)) {
            authenticationMenu();

        }
        while (!isExit) {
            showMenu();
        }

    }


    private  void bookInitializer() {
        books[0]= new Book("core java", "Cay S. Horstmann", 4);
        books[1] = new Book("Spring In Action", "Craig Walls", 4);
        books[2] = new Book("Spring Security In Action", " Laurentiu Spilca", 4);
        books[3] = new Book("Unit Testing", "Vladimir Khorikov", 4);
        books[4] = new Book("Head First Design Patterns", "Eric Freeman & Elisabeth Robson", 4);
    }

    private void showBooks() {
        bookInitializer();
        for (int i = 0; i < books.length; i++) {
            System.out.println(i + ": " + books[i]);
        }
    }


    private Book findBookById(int id) {
        Book book = null;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id)
                book = books[i];
        }
        if (Objects.isNull(book))
            System.err.println("there is no book by this id.");
        return book;
    }

    private void login() {
        String username = stringInput("Enter your username: ");
        String password = stringInput("Enter your password: ");
        boolean isAuthenticate = false;
        for (int i = 0; i < users.length; i++) {
            if (Objects.nonNull(users[i]) && users[i].Login(username, password))
                currentUser=users[i];
                isAuthenticate = true;
        }
        if (!isAuthenticate)
            System.err.println("your username or password is incorrect");

        }
        private void Register(){
            String firstName = stringInput("Enter your first name: ");
            String lastName = stringInput("Enter your last name: ");
            String nationalCode = stringInput("Enter your national code: ");
            String password = stringInput("Enter your password: ");
            User newUser = new User(firstName, lastName, nationalCode, password);
            for (int i = 0; i < users.length; i++) {
                if (Objects.isNull(users[i]) )
                    users[i]=newUser;
                break;
            }
        }

    private void authenticationMenu() {
        System.out.println("1- Login" + "\n" + "2- Register");
        int item = intInput("");
        switch (item) {
            case REGISTER: {
                Register();
                break;
            }
            case LOGIN: {
                login();
                break;
            }
        }
    }

    private void showMenu() {
        int selectItem = intInput("""
                    Select the item:
                    1. book list
                    2. your borrowed books
                    3. borrow a book
                    4. return a book
                    9. logout
                    0. Exit
                    """);
        switch (selectItem) {
            case SHOW_BOOKS: {
                showBooks();
                break;
            }
            case SHOW_BORROWED_BOOKS: {
                currentUser.showBorrowedBooks();
                break;
            }
            case BORROW_BOOK: {
                int id = intInput("Enter the id: ");
                Book book = findBookById(id);
                if (Objects.nonNull(book)) {
                    currentUser.borrow(book);
                    book.lend();
                }
                break;
            }
            case RETURN_BOOK: {
                int id = intInput("Enter the id: ");
                Book book = findBookById(id);
                if (Objects.nonNull(book)) {
                    currentUser.returnBook(book);
                    book.retrieve();
                }
                break;
            }
            case LOGOUT: {
                currentUser = null;
                run();
                break;
            }
            case EXIT: {
                System.out.println("thank you, bye!");
                isExit = true;
                break;
            }
        }
    }
















        private int intInput(String message) {
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }

        private String stringInput(String message) {
            System.out.print(message);
            Scanner scanner = new Scanner(System.in);
            return scanner.next();
        }


}
