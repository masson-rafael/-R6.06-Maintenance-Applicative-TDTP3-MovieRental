package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String _name;
    private List<Rental> _rentals = new ArrayList<Rental>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {

        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : _rentals) {
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";

        return result;
    }

    public String HTMLStatement() {

        String result = "<h1>Rental Record for <em>" + getName() + "</em> </h1>\n";

        result += "<ol>\n";
        for (Rental each : _rentals) {
            // show figures for this rental
            result += "<li><em>" + each.getMovie().getTitle() + "</em>: " + String.valueOf(each.getCharge()) + "</li>\n";
        }
        result += "</ol>\n";

        // add footer lines
        result += "<p>";
        result += "Amount owed is <em>" + String.valueOf(getTotalCharge()) + "</em>";
        result += "</p>\n";

        result += "<p>";
        result += "You earned <em>" + String.valueOf(getTotalFrequentRenterPoints()) + "</em> frequent renter points";
        result += "</p>";

        return result;
    }

    public double getTotalCharge() {
        double totalAmount = 0;
        for (Rental each : _rentals) {
            totalAmount += each.getCharge();
        }
        return totalAmount;
    }

    public int getTotalFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental each : _rentals) {
            frequentRenterPoints += each.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

}
