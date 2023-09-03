package functional_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private final Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    public SuperIterable<E> filter(Predicate<E> predicate){

        List<E> list = new ArrayList<>();
        self.forEach(e -> {
            if(predicate.test(e)) {
                list.add(e);
            }
        });
        return new SuperIterable<>(list);
    }

    public void forEvery(Consumer<E> consumer) {
        for(E e : self){
            consumer.accept(e);
        }
    }

    public <F> SuperIterable<F> map(Function<E,F> function) {
        List<F> list = new ArrayList<>();
        self.forEach(e -> list.add(function.apply(e)));
        return new SuperIterable<>(list);
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> function) {
        List<F> list = new ArrayList<>();
        self.forEach(e -> function.apply(e).forEach(  i -> list.add(i)));
        return new SuperIterable<>(list);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(Arrays.asList("Red","black","Green"));
        strings.forEach( s -> System.out.println(s));

        SuperIterable<String> filterdStrings = strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        filterdStrings.forEach(s -> System.out.println(s));

        strings.map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

        SuperIterable<Car> cars = new SuperIterable<>(Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Margrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gilian","Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red","Ender", "Hyrum", "Locke","Bonzo")
        ));

        cars
                .filter(car -> car.getGasLevel()>7)
                .flatMap(car -> new SuperIterable<>(car.getPassengers()))
                .map(s -> s.toUpperCase())
                .forEach( car -> System.out.println("> "+car));
    }
}
