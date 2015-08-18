package no.api.freemarker.java8.time;

public abstract class AbstractFormatter<E> {

    private E obj;

    public AbstractFormatter(E obj) {
        this.obj = obj;
    }

    public E getObject() {
        return obj;
    }
}
