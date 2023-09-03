package functional_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class CarScratch {

    /**
     * This function to do compare of two elements and return an int
     * @param c
     * @param comp
     * @param <E>
     * @return
     */
    private static <E> ToIntFunction<E> compareWithThis(E c, Comparator<E> comp) {
        return x -> comp.compare(c, x);
    }

    private static <E> Predicate<E> checkwithThis(ToIntFunction<E> toIntFunction) {
        return e -> toIntFunction.applyAsInt(e) > 0;
    }

    //Write a predicate to find car have greater gasLvelThanGiven car
    //Predicate<Car> pred = car ->

    private static <E> void showAll(List<E> list){
        for(E e : list){
            System.out.println(e);
        }
        System.out.println("------------------------------");
    }

    private static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> criterion){
        List<E> output = new ArrayList<>();
        for(E e : in){
            if(criterion.test(e))
                output.add(e);
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
        showAll(getByCriterion(cars, Car.getRedCarCriterion()));
        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(6)));
        cars.sort(new Car.PassengerCountOrder());
        showAll(cars);
        cars.sort(Car.getGasComparator());
        showAll(cars);
        showAll(getByCriterion(cars, c -> c.getGasLevel()  > 6));

        Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
        showAll(getByCriterion(cars, level7));
        showAll(getByCriterion(cars, level7.negate()));

        showAll(getByCriterion(cars, Car.getRedCarCriterion().and(Car.getGasLevelCarCriterion(6))));

        Car bert = Car.withGasColorPassengers(5, "White");
        ToIntFunction<Car> changeToInt = compareWithThis(bert, (c1, c2) -> c1.getGasLevel() - c2.getGasLevel());
        Predicate<Car> isTrue = checkwithThis(changeToInt);
        showAll(getByCriterion(cars, isTrue));

    }
}

