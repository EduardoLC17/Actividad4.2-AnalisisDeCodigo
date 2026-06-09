package video_store;

import java.util.Vector;
import java.util.Enumeration;

public class Customer {
    private static final int REGULAR_RENTAL_DAYS = 2;
    private static final int CHILDRENS_RENTAL_DAYS = 3;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentalsEnum = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentalsEnum.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentalsEnum.nextElement();

            // determines the amount for each line
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > REGULAR_RENTAL_DAYS)
                        thisAmount += (each.getDaysRented() - REGULAR_RENTAL_DAYS) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > CHILDRENS_RENTAL_DAYS)
                        thisAmount += (each.getDaysRented() - CHILDRENS_RENTAL_DAYS) * 1.5;
                    break;
            }

            frequentRenterPoints++;

            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE
                    && each.getDaysRented() > 1)
                frequentRenterPoints++;

            result += "\t" + each.getMovie().getTitle() + "\t"
                    + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;

        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

        return result;
    }


    private String name;
    private Vector rentals = new Vector();
}
