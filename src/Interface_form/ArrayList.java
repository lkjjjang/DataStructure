package Interface_form;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private int size;
    Object[] array;

    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    public void addFirst(E value) {
        add(0, value);
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {

        if (this.size == this.array.length) {
            resize();
        }
        this.array[this.size] = value;
        this.size++;
    }

    @Override
    public void add(int index, E value) {

        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == this.size) {
            addLast(value);
        } else {
            if(this.size == this.array.length) {
                resize();
            }

            // index 기준 후자에 있는 모든 요소들 한 칸씩 뒤로 밀기
            for (int i = this.size; i > index; i--) {
                this.array[i] = this.array[i - 1];
            }

            this.array[index] = value;
            this.size++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if(index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return (E) this.array[index];
    }

    @Override
    public void set(int index, E value) {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        else {
            this.array[index] = value;
        }
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        for (i = 0; i < this.size; i++) {
            if (this.array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        for(int i = this.size - 1; i >= 0; i--) {
            if(this.array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {

        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) this.array[index];	// 삭제될 요소를 반환하기 위해 임시로 담아둠
        this.array[index] = null;

        // 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당겨옴
        for (int i = index; i < this.size; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.size--;
        resize();
        return element;
    }

    public boolean remove(Object value) {

        int index = indexOf(value);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
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
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
        this.size = 0;
        resize();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // 새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>)super.clone();

        // 새로운 객체의 배열도 생성해주어야 함 (객체는 얕은복사가 되기 때문)
        cloneList.array = new Object[this.size];

        // 배열의 값을 복사함
        System.arraycopy(this.array, 0, cloneList.array, 0, this.size);

        return cloneList;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.array, this.size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOf(this.array, this.size, a.getClass());
        }
        // 원본배열, 원본배열 시작위치, 복사할 배열, 복사할배열 시작위치, 복사할 요소 수
        System.arraycopy(this.array, 0, a, 0, this.size);
        return a;
    }

    private void resize() {
        int array_capacity = this.array.length;

        // if array's capacity is 0
        if (Arrays.equals(this.array, EMPTY_ARRAY)) {
            this.array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량이 꽉 찰 경우
        if (this.size == array_capacity) {
            int new_capacity = array_capacity * 2;

            // copy
            this.array = Arrays.copyOf(this.array, new_capacity);
            return;
        }
        // 용적의 절반 미만으로 요소가 차지하고 있을 경우
        if (this.size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            // copy
            this.array = Arrays.copyOf(this.array, Math.max(new_capacity, DEFAULT_CAPACITY));
        }
    }
}