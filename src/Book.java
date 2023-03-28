import java.util.Random;

public class Book {
    private String name;
    private String AuthorName;
    private int Id;
    private int stock;


    public Book(String name, String authorName, int stock) {
        this.name = name;
        this.AuthorName = authorName;
        Random random=new Random();
        this.Id= random.nextInt(1000);
        this.stock = stock;
    }

    public void lend(){
        this.stock-=stock;
    }

    public void retrieve(){
        this.stock+=stock;
    }

    public int getStock() {
        return stock;
    }
    public int getId(){
        return Id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + this.name + '\'' +
                ", AuthorName='" + this.AuthorName + '\'' +
                ", Id=" + this.Id +
                ", stock=" + this.stock +
                '}';
    }
}
