package Test1.Pages;

public class Book {
        private String title;
        private String author;
        private String publisher;

        public Book(String title, String author, String publisher) {
            this.title = title;
            this.author = author;
            this.publisher = publisher;
        }

        public void printBook() {
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Publisher: " + publisher);
            System.out.println("-------------");
        }
    }


