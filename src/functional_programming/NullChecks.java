package functional_programming;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NullChecks {
    public static void main(String[] args) {
        Map<String, Car> owners = new HashMap<>();
        owners.put("Jim", Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"));
        owners.put("Ricully", Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"));
        owners.put("Ogg", Car.withGasColorPassengersAndTrunk(6, "Red", "Jim", "Sheila", "tyre"));


        String owner = "Ogg";

        //Find out the truck contents of a car
        Optional<Map<String, Car>> optionalCars = Optional.of(owners);
        optionalCars
                .map( m -> m.get(owner))
                .map( c -> c.getTruckContents())
                .ifPresent(t -> System.out.println(t));

        optionalCars
                .map( m -> m.get(owner))
                .map( c -> c.getTruckContentsOpt()
                                .map( t -> t.toString())
                                .orElse("has  nothing")
                )
                .ifPresent(t -> System.out.println(t));

    }
}
