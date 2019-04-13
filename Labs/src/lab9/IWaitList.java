package lab9;

import java.util.*;

public interface IWaitList<E> {
  public void add(E element);
  public E remove();
  public boolean contains(E element);
  public boolean containsAll(Collection<E> c);
  public boolean isEmpty();
}
