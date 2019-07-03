package no.api.freemarker.java8.time;

/**
 * Abstract checker class. Adapters supporting checkers will extend this class.
 *
 * @param <E> The java.time class this formatter handles.
 */
public abstract class AbstractChecker<E> {

    private final E obj;

    public AbstractChecker(final E obj) {
        this.obj = obj;
    }

    public E getObject() {
        return this.obj;
    }
}
