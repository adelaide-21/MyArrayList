`java
import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Создает список с емкостью по умолчанию
     */
    public MyArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    /**
     * Создает список с указанной начальной емкостью
     * @throws IllegalArgumentException если initialCapacity < 0
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }
    
    /**
     * Добавляет элемент в конец списка
     */
    @Override
    public void add(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }
    
    /**
     * Вставляет элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс вне диапазона [0, size]
     */
    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        
        // Сдвигаем элементы вправо
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }
    
    /**
     * Возвращает элемент по индексу
     * @throws IndexOutOfBoundsException если индекс вне диапазона [0, size)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }
    
    /**
     * Заменяет элемент по индексу
     * @throws IndexOutOfBoundsException если индекс вне диапазона [0, size)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) data[index];
        data[index] = element;
        return oldValue;
    }
    
    /**
     * Удаляет элемент по индексу
     * @throws IndexOutOfBoundsException если индекс вне диапазона [0, size)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) data[index];
        
        // Сдвигаем элементы влево
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        
        data[--size] = null; // Помогаем сборщику мусора
        return removedElement;
    }
    
    /**
     * Удаляет первое вхождение элемента
     */
    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }
    
    /**
     * Возвращает количество элементов
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Проверяет, пуст ли список
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Проверяет наличие элемента
     */
    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }
    
    /**
     * Очищает список
     */
    @Override
    public void clear() {
        // Обнуляем все ссылки для помощи сборщику мусора
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
    
    /**
     * Возвращает индекс первого вхождения элемента
     */
    @Override
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
     if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /**
     * Увеличивает емкость массива при необходимости
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * Проверяет индекс для операций get/set/remove
     * @throws IndexOutOfBoundsException если индекс невалидный
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    /**
     * Проверяет индекс для операции add
     * @throws IndexOutOfBoundsException если индекс невалидный
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    /**
     * Возвращает текущую емкость внутреннего массива
     */
    public int capacity() {
        return data.length;
    }
    
    /**
     * Уменьшает емкость до текущего размера
     */
    public void trimToSize() {
        if (size < data.length) {
            data = Arrays.copyOf(data, size);
        }
    }
    
    /**
     * Возвращает строковое представление списка
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
    
    /**
     * Преобразует в массив
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) Arrays.copyOf(data, size);
    }
    
    /**
     * Проверяет равенство двух списков
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        MyArrayList<?> that = (MyArrayList<?>) o;
        if (size != that.size) return false;
        
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(data[i], that.data[i])) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Возвращает хэш-код списка
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        for (int i = 0; i < size; i++) {
            result = 31 * result + (data[i] != null ? data[i].hashCode() : 0);
        }
        return result;
    }
}         
