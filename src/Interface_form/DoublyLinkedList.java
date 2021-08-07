package Interface_form;

public class DoublyLinkedList<E> implements List{

    private Node<E> head;
    private Node<E> tail;
    private int size;


    @Override
    public boolean add(Object value) {
        return false;
    }

    @Override
    public void add(int index, Object value) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public void set(int index, Object value) {

    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
