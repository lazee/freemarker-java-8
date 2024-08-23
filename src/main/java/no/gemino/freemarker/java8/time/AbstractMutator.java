package no.gemino.freemarker.java8.time;

/**
 * Abstract mutator class.
 * <p>
 * Adapters supporting mutators will extend this class.
 *
 * @param <E> The <code>java.time</code> class this mutator handles.
 */
public abstract class AbstractMutator<E> {

    private final E obj;

    public AbstractMutator(E obj) {
        this.obj = obj;
    }

    public E getObject() {
        return obj;
    }
}