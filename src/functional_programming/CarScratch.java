package functional_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

interface CarCriterion {
    boolean test(Car c);
}

public class CarScratch {

    public static void showAll(List<Car> cars){
        for(Car car : cars){
            System.out.println(car);
        }
        System.out.println("------------------------------");
    }

    public static List<Car> getCarsByCriterion(Iterable<Car> in, CarCriterion carCriterion){
        List<Car> output = new ArrayList<>();
        for(Car c : in){
            if(carCriterion.test(c))
                output.add(c);
        }
        return output;
    }

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Margrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gilian","Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red","Ender", "Hyrum", "Locke","Bonzo")
        );
        showAll(cars);
        //use factory method instead of constants
        showAll(getCarsByCriterion(cars, Car.getRedCarCriterion()));
        showAll(getCarsByCriterion(cars, new Car.GasLevelCarCriterion(6)));
        cars.sort(new Car.PassengerCountOrder());
        showAll(cars);
    }
}

