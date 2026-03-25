import movierental.Customer;
import movierental.Movie;
import movierental.Rental;

public class Main {

    public static void main(String[] args) {
        Movie movie01 = new Movie("Captain Marvel", Movie.NEW_RELEASE);
        Movie movie02 = new Movie("Aladdin", Movie.CHILDRENS);
        Movie movie03 = new Movie("Back to the future", Movie.REGULAR);

        Customer customer = new Customer("Roger Ebert");

        Rental rental01 = new Rental(movie01, 2);
        Rental rental02 = new Rental(movie02, 3);
        Rental rental03 = new Rental(movie03, 2);

        customer.addRental(rental01);
        customer.addRental(rental02);
        customer.addRental(rental03);

        System.out.println(customer.statement());
        System.out.println(customer.HTMLStatement());
    }
}


