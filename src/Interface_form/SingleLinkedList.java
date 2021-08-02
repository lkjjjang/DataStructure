package Interface_form;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SingleLinkedList<E> implements List<E>, Cloneable {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addFirst(E value) {

        Node<E> newNode = new Node<E>(value);
        newNode.next = this.head;	// 다음 노드로 head 노드를 연결
        this.head = newNode;	// head가 가리키는 노드를 새 노드로 변경
        this.size++;

        /**
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드다. 즉 tail = head 다.
         */
        if (this.head.next == null) {
            tail = head;
        }
    }

    public void sort() {
        /**
         *  Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
         *  정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable
         *  에러가 발생한다.
         *  만약 구현되어있을 경우 null로 파라미터를 넘기면
         *  Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Array.newInstance(컴포넌트 타입, 생성할 크기)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }
        return a;
    }


    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        SingleLinkedList<? super E> clone = (SingleLinkedList<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> targetNode = this.head; targetNode != null;) {
            Node<E> nextNode = targetNode.next;
            targetNode.data = null;
            targetNode.next = null;
            targetNode = nextNode;
        }
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {

        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> targetNode = this.head; targetNode != null; targetNode = targetNode.next) {
            if (value.equals(targetNode.data)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public E remove() {

        Node<E> headNode = head;

        if (headNode == null)
            throw new NoSuchElementException();

        // 삭제시 반환 값
        E element = headNode.data;

        Node<E> nextNode = this.head.next;

        // head 노드의 데이터들을 모두 삭제
        this.head.data = null;
        this.head.next = null;

        // 가장 앞부분이 삭제 됐으니 head 를 다음 노드로 교체
        this.head = nextNode;
        this.size--;

        if(this.size == 0) {
            this.tail = null;
        }
        return element;
    }

    @Override
    public E remove(int index) {

        if (index == 0) {
            return remove();
        }

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제할 요소, 앞, 뒤 모두 기억
        Node<E> prevNode = search(index - 1);
        Node<E> removedNode = prevNode.next;
        Node<E> nextNode = removedNode.next;

        E element = removedNode.data;	// 삭제시 반환 값

        // 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음노드로 변경
        prevNode.next = nextNode;

        removedNode.next = null;
        removedNode.data = null;
        this.size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = this.head;
        boolean hasValue = false;
        Node<E> targetNode = this.head;	// removedNode

        // targetNode 가 null 이 아닌 경우 실행
        // 한 회차 실행후 다음노드 검색
        for (; targetNode != null; targetNode = targetNode.next) {
            if (value.equals(targetNode.data)) {
                hasValue = true;
                break;
            }
            prevNode = targetNode;
        }

        if(targetNode == null) {
            return false;
        }

        if (targetNode.equals(this.head)) {
            remove(); //가장 앞부분을 지우는 remove
        } else {
            // 앞 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
            prevNode.next = targetNode.next;

            targetNode.data = null;
            targetNode.next = null;
            this.size--;
        }
        return true;
    }

    @Override
    public void add(int index, E value) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // 가장앞에 추가
        if (index == 0) {
            addFirst(value);
            return;
        }
        // 가장뒤에 추가
        if (index == this.size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prev_Node = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> next_Node = prev_Node.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<E>(value);

        /**
         * 이전 노드가 가리키는 노드를 끊은 뒤
         * 새 노드로 변경해준다.
         * 또한 새 노드가 가리키는 노드는 next_Node로
         * 설정해준다.
         */
        prev_Node.next = null;
        prev_Node.next = newNode;
        newNode.next = next_Node;
        size++;

    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);	// 새 노드 생성

        if (this.size == 0) {	// 처음 넣는 노드일 경우 addFisrt로 추가
            addFirst(value);
            return;
        }

        /**
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail이 가리키는 노드를 새 노드로 바꿔준다.
         */
        this.tail.next = newNode;
        this.tail = newNode;
        this.size++;
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
