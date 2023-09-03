package functional_programming;

@FunctionalInterface
public interface Criterion<E> {

    boolean test(E e);

    default Criterion<E> negate() {
        return x -> !this.test(x);
    }

    default Criterion<E> and(Criterion<E> crit) {
        return x -> this.test(x) && crit.test(x);
    }

    default Criterion<E> or(Criterion<E> crit){
        return x -> this.test(x) || crit.test(x);
    }


}
