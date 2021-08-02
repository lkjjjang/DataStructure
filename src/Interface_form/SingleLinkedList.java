package Interface_form;

public class SingleLinkedList<E> implements List{

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addFirst(E value) {

        Node<E> newNode = new Node<E>(value);
        newNode.next = this.head;	// 다음 노드로 head 노드를 연결
        this.head = newNode;	// head가 가리키는 노드를 새 노드로 변경
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드다. 즉 tail = head 다.
         */
        if (this.head.next == null) {
            tail = head;
        }
    }
    
    // 특정 위치의 노드를 반환하는 메소드
    private Node<E> search(int index) {

        // 범위 밖(잘못된 위치)일 경우 예외 던지기
        if(index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> x = this.head;	// head가 기리키는 노드부터 시작

        for (int i = 0; i < index; i++) {
            x = x.next;	// x노드의 다음 노드를 x에 저장한다
        }
        return x;
    }
}
