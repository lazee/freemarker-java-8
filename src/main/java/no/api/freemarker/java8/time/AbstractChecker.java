package no.api.freemarker.java8.time;

/**
 * Abstract checker class.
 * <p>
 * Adapters supporting checkers will extend this class.
 *
 * @param <E> The java.time class this formatter handles.
 */
public abstract class AbstractChecker<E> {

    private E obj;


    public AbstractChecker(E obj) {
        this.obj = obj;
    }


    public E getObject() {
        return obj;
    }
}