public class MyArrayListDemo {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ MyArrayList ===");
        
        // Тест 1: Базовые операции
        System.out.println("\n--- Тест 1: Базовые операции ---");
        MyArrayList<String> list = new MyArrayList<>();
        
        System.out.println("Создан пустой список:");
        System.out.println("Размер: " + list.size());
        System.out.println("Пустой: " + list.isEmpty());
        System.out.println("Содержимое: " + list);
        
        // Добавление элементов
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("\nПосле добавления 3 элементов:");
        System.out.println("Список: " + list);
      System.out.println("Размер: " + list.size());
        
        // Вставка по индексу
        list.add(1, "Orange");
        System.out.println("\nПосле вставки 'Orange' на позицию 1:");
        System.out.println("Список: " + list);
        
        // Тест 2: Доступ и поиск
        System.out.println("\n--- Тест 2: Доступ и поиск ---");
        System.out.println("Элемент по индексу 0: " + list.get(0));
        System.out.println("Элемент по индексу 2: " + list.get(2));
        
        System.out.println("Содержит 'Banana': " + list.contains("Banana"));
        System.out.println("Содержит 'Grape': " + list.contains("Grape"));
        System.out.println("Индекс 'Cherry': " + list.indexOf("Cherry"));
        System.out.println("Индекс 'Mango': " + list.indexOf("Mango"));
        
        // Тест 3: Замена элементов
        System.out.println("\n--- Тест 3: Замена элементов ---");
        String oldValue = list.set(2, "Blueberry");
        System.out.println("Заменен элемент на позиции 2: " + oldValue + " -> Blueberry");
        System.out.println("Список после замены: " + list);
        
        // Тест 4: Удаление элементов
        System.out.println("\n--- Тест 4: Удаление элементов ---");
        
        // Удаление по индексу
        String removedByIndex = list.remove(0);
        System.out.println("Удален по индексу 0: " + removedByIndex);
        System.out.println("Список после удаления: " + list);
        
        // Удаление по значению
        boolean removedByValue = list.remove("Orange");
        System.out.println("Удален 'Orange' по значению: " + removedByValue);
        System.out.println("Список после удаления: " + list);
        
        // Попытка удалить несуществующий элемент
        boolean removedNonExistent = list.remove("Mango");
        System.out.println("Попытка удалить 'Mango': " + removedNonExistent);
        
        // Тест 5: Динамическое расширение
        System.out.println("\n--- Тест 5: Динамическое расширение ---");
        MyArrayList<Integer> numbers = new MyArrayList<>(5);
        System.out.println("Начальная емкость: " + numbers.capacity());
        
        for (int i = 0; i < 15; i++) {
            numbers.add(i * 10);
        }
        System.out.println("Добавлено 15 элементов:");
        System.out.println("Размер: " + numbers.size());
        System.out.println("Емкость после расширения: " + numbers.capacity());
        System.out.println("Содержимое: " + numbers);
        
        // Тест 6: Работа с null
        System.out.println("\n--- Тест 6: Работа с null ---");
        MyArrayList<String> listWithNulls = new MyArrayList<>();
        listWithNulls.add("First");
        listWithNulls.add(null);
        listWithNulls.add("Third");
        listWithNulls.add(null);
        
        System.out.println("Список с null: " + listWithNulls);
        System.out.println("Содержит null: " + listWithNulls.contains(null));
        System.out.println("Индекс первого null: " + listWithNulls.indexOf(null));
        
        // Удаление null
        listWithNulls.remove(null);
        System.out.println("После удаления первого null: " + listWithNulls);
        
        // Тест 7: Очистка списка
        System.out.println("\n--- Тест 7: Очистка списка ---");
        System.out.println("До очистки: " + list);
        System.out.println("Размер: " + list.size());
        
        list.clear();
        System.out.println("После очистки: " + list);
        System.out.println("Размер: " + list.size());
        System.out.println("Пустой: " + list.isEmpty());
        
        // Тест 8: Обработка исключений
        System.out.println("\n--- Тест 8: Обработка исключений ---");
        try {
            list.get(5); // Пустой список
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Поймано исключение при get(5): " + e.getMessage())
              }
        
        try {
            list.add(-1, "Invalid"); // Неверный индекс
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Поймано исключение при add(-1): " + e.getMessage());
        }
        
        try {
            new MyArrayList<>(-5); // Отрицательная емкость
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение при создании с capacity=-5: " + e.getMessage());
        }
        
        // Тест 9: trimToSize
        System.out.println("\n--- Тест 9: Оптимизация памяти ---");
        MyArrayList<String> optimizedList = new MyArrayList<>();
        for (int i = 0; i < 5; i++) {
            optimizedList.add("Item " + i);
        }
        System.out.println("Размер: " + optimizedList.size());
        System.out.println("Емкость до trim: " + optimizedList.capacity());
        
        optimizedList.trimToSize();
        System.out.println("Емкость после trim: " + optimizedList.capacity());
        
        // Тест 10: Преобразование в массив
        System.out.println("\n--- Тест 10: Преобразование в массив ---");
        MyArrayList<String> arrayList = new MyArrayList<>();
        arrayList.add("One");
        arrayList.add("Two");
        arrayList.add("Three");
        
        Object[] array = arrayList.toArray();
        System.out.print("Массив: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.println("\n=== ТЕСТИРОВАНИЕ ЗАВЕРШЕНО ===");
    }
}
