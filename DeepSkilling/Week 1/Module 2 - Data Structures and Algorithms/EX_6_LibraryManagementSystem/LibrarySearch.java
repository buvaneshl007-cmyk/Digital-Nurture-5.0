import java.util.Arrays;
import java.util.Comparator;

public class LibrarySearch {

    /**
     * Linear search by title. Works regardless of order.
     * Time complexity: O(n)
     */
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Binary search by title. Requires books to already be sorted by title.
     * Time complexity: O(log n)
     */
    public static Book binarySearchByTitle(Book[] sortedBooks, String title) {
        int low = 0;
        int high = sortedBooks.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sortedBooks[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return sortedBooks[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void sortByTitle(Book[] books) {
        Arrays.sort(books, Comparator.comparing(Book::getTitle, String::compareToIgnoreCase));
    }
}
