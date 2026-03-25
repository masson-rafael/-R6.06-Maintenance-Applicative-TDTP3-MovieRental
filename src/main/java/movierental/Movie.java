package movierental;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    private String _title;
    private int _priceCode;

    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int arg) {
        _priceCode = arg;
    }
    public String getTitle() {
        return _title;
    }

    public int getFrequentRenterPoints(int daysRented) {
        // add frequent renter points
        int result = 1;
        // add bonus for a two day new release rental
        if ((this.getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
            result++;
        return result;
    }

    public double getCharge(int daysRented) {
        double result = 0;
        //determine amounts for each line
        switch (this.getPriceCode()) {
            case REGULAR:
                result += 2;
                if (daysRented > 2)
                    result += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                result += daysRented * 3;
                break;
            case CHILDRENS:
                result += 1.5;
                if (daysRented > 3)
                    result += (daysRented - 3) * 1.5;
                break;
        }
        return result;
    }
}
