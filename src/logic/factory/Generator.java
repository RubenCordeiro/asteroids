package logic.factory;

/**
 * Root class for all the concrete factory method generators.
 * @author Ruben Cordeiro
 *
 */
public interface Generator<T> {
public T generate();
public T generate(double scaleFactor);
public T generate(T prod, double scaleFactor);
}
