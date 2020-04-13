package no.api.freemarker.java8.time;

/**
 * Abstract mutator class.
 *
 * Adapters supporting mutators will extend this class.
 *
 * @param <E>
 *         The java.time class this mutator handles.
 */
public abstract class AbstractMutator<E> {

    private E obj;

    public AbstractMutator(E obj) {
        this.obj = obj;
    }

    public E getObject() {
        return obj;
    }
}