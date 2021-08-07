package Interface_form;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addFirst(E value) {

        // addFirst 항상 앞에만 추가하는 메소드
        // 새로 들어온 노드의 다음 노드 작업
        // 다음 노드의 앞 노드 작업
        // 리스트의 head 를 새로운 노드로 교체
        Node<E> newNode = new Node<E>(value);
        newNode.next = this.head; // 새로운 노드의 다음노드를 연결(현재의 head 가 새로운 노드의 다음 노드가 됨)

        if (this.head != null) {
            this.head.prev = newNode; //현재 노드의 앞노드를 연결(새로 들어온 노드가 현재노드의 앞 노드가 됨)
        }

        this.head = newNode; //현재 리스트의 head 는 새로운 노드가 됨
        this.size++;

       if (this.head.next == null) {
            this.tail = this.head;
        }
    }

    public void addLast(E value) {

        // addFirst 의 반대로 생각
        Node<E> newNode = new Node<E>(value);

        if (this.size == 0) {
            addFirst(value); // 끝이자 처음 이므로 addFirst 에서 작업
            return;
        }

        this.tail.next = newNode; // 앞노드의 꼬리에 새로운 노드연결
        newNode.prev = this.tail; // 새로운 노드의 앞노드에 현재의 꼬리를 연결
        this.tail = newNode; // 현재 리스트의 tail 은 새로운 노드가 됨
        this.size++;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return false;
    }

    @Override
    public void add(int index, E value) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(value);
        }

        if (index == this.size) {
            addLast(value);
        }

        Node<E> prev_Node = search(index - 1); // 앞 노드
        Node<E> next_Node = prev_Node.next; // 뒤 노드
        Node<E> newNode =  new Node<>(value); // 새 노드

        prev_Node.next = newNode; // 앞 노드 작업

        newNode.prev = prev_Node; // 새 노드 작업업
        newNode.next = next_Node;

        next_Node.prev = newNode; // 뒤 노드 작업
        this.size++;
    }

    public E remove() {
        Node<E> headNode = this.head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        E element = headNode.data; // 삭제후 반환할 데이터
        Node<E> nextNode = this.head.next;

        this.head.data = null; // 리스트의 가장 앞부분 삭제 처리
        this.head.next = null; // head 부분 이니 prev 가 없다

        if (nextNode != null) {
            nextNode.prev = null; // 삭제 된 노드 다음 노드가 null 이 아닌 경우에만 prev 삭제
        }

        this.head = nextNode; // 리스트의 head , size 수정
        this.size--;

        if (this.size == 0) {
            this.tail = null; //
        }

        return element;
    }

    @Override
    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            E element = this.head.data;
            remove();
            return element;
        }

        Node<E> prevNode = search(index - 1); // 삭제 대상의 앞 노드
        Node<E> removeNode = prevNode.next;         // 삭제 대상 노드
        Node<E> nextNode = removeNode.next;         // 삭데 대상의 뒤 노드
        E element = removeNode.data;                // 삭제후 반환할 데이터

        // 앞 노드의 뒤 노드 주소 삭제, 삭제대상 노드 삭제
        prevNode.next = null; // 해당 노드는 nextNode 가 null 일 경우가 있어 이부분에서 삭제
        removeNode.prev = null;
        removeNode.next = null;
        removeNode.data = null;

        // 삭제 후 뒤 노드 작업
        if (nextNode != null) {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        } else {
            this.tail = prevNode; // 삭제한 노드가 마지막 노드라는 조건이 성립 tail 을 삭제전 앞 노드로 교체체
        }

        this.size--;

       return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = this.head;
        Node<E> target = this.head;

        for (; target != null; target = target.next) { // 대상 노드와 일치하는 노드 찾기
            if (value.equals(target.data)) {
                break;
            }
            prevNode = target;
        }

        if (target == null) {
            return false;
        }

        if (target.equals(this.head)) {
            remove();
        } else {
            Node<E> nextNode = target.next;

            prevNode.next = null;
            target.data = null;
            target.prev = null;
            target.next = null;

            if (nextNode != null) {
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            } else {
                this.tail = prevNode;
            }

            this.size--;
        }
        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data; // 예외는 search() 에서 던짐
    }

    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> target = this.head; target != null; target = target.next) {
            if (value.equals(target.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object value) {
        int index = this.size;

        for (Node<E> target = this.tail; target != null; target = target.prev) {
            index--;
            if (value.equals(target.data)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> target = this.head; target != null;) {
            Node<E> nextNode = target.next;
            target.data = null;
            target.prev = null;
            target.next = null;
            target = nextNode;
        }

        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        // 찾고자 하는 인데스의 위치를 시작점, 끝점 중 가까운 곳을 선택해 탐색
        Node<E> target;
        if (index > this.size / 2) {
            target = this.tail;

            for (int i = this.size - 1; i > index; i--) {
                target = target.prev;
            }
        } else {
            target = this.head;

            for (int i = 0; i < index; i++) {
                target = target.next;
            }
        }

        return target;
    }





























}
