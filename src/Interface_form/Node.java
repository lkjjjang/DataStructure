package Interface_form;

public class Node<E> {

    E data;
    Node<E> next; //다음 개체 주소
    Node<E> prev; //이전 개체 주소

    Node(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
