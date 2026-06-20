public class LibraryTest {
    public static void main(String[] args) {
        Book[] books = {
                new Book("B003", "The Pragmatic Programmer", "David Thomas"),
                new Book("B001", "Clean Code", "Robert Martin"),
                new Book("B004", "Effective Java", "Joshua Bloch"),
                new Book("B002", "Design Patterns", "Erich Gamma")
        };

        System.out.println("Linear search for 'Effective Java':");
        System.out.println(LibrarySearch.linearSearchByTitle(books, "Effective Java"));

        LibrarySearch.sortByTitle(books);
        System.out.println("\nBooks sorted by title:");
        for (Book b : books) {
            System.out.println(b);
        }

        System.out.println("\nBinary search for 'Design Patterns':");
        System.out.println(LibrarySearch.binarySearchByTitle(books, "Design Patterns"));

        System.out.println("\nBinary search for 'Nonexistent Book':");
        System.out.println(LibrarySearch.binarySearchByTitle(books, "Nonexistent Book"));
    }
}
