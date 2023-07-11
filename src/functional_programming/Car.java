package functional_programming;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Car {
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> truckContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> truckContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.truckContents = truckContents;
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers){
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, null);
        return self;
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String  color, String... passengers){
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, Arrays.asList("jack","wrench","spare wheel"));
        return self;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTruckContents() {
        return truckContents;
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                (truckContents!=null ? ", truckContents=" + truckContents : " no trunk") +
                '}';
    }

    static class RedCarCriterion implements CarCriterion {

        @Override
        public boolean test(Car c) {
            return c.getColor().equalsIgnoreCase("Red");
        }
    }

    static class GasLevelCarCriterion implements  CarCriterion {

        private final int threshold;

        public GasLevelCarCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.getGasLevel() >= threshold;
        }
    }

    static class PassengerCountOrder implements Comparator<Car> {

        @Override
        public int compare(Car o1, Car o2) {
            return o1.getPassengers().size() -  o2.getPassengers().size();
        }
    }

}
