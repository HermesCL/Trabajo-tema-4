
import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String statement() {
        double totalAmount = 0;
        //Ya que hemos definido la variable dentro del metodo getFrecuentRenterPoint podemos eliminarla
        // int frequentRenterPoints = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            // determine amounts for each line
            Rental rental1 = new Rental();
            rental1.getGetCharge(thisAmount, each);

            // add frequent renter points
            //Ya que hemos sumado dentro del metodo podemos eliminar esta parte
            //frequentRenterPoints++;
            
            // add bounus for a two day new release rental
            getFrecuentRenterPoint(each);
            
            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    public String getName() {
        return _name;
    }

    public int getFrecuentRenterPoint(Rental each) {
        int frequentRenterPoints = 1;
        
        //El if comprueba que la pelicula sea nueva y se haya alquilado mas de un dia
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
            //si se cumple se le añadira 1 a la variable frequentRenterPoint con lo cual se retornara 2
            //en caso contrario se retornara 1 que es el valor de inicializacion de la variable
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

}
