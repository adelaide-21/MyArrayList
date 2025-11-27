# MyArrayList - Custom ArrayList Implementation

Реализация собственного класса динамического массива MyArrayList на Java.

## Особенности
- Generic-типы для типобезопасности
- Динамическое расширение массива
- Полная обработка исключений
- Аналогично стандартному ArrayList

## Структура
- `MyList<T>` - интерфейс списка
- `MyArrayList<T>` - основная реализация
- `MyArrayListDemo` - демонстрация работы

## Использование
java
MyArrayList<String> list = new MyArrayList<>();
list.add("Hello");
list.add("World");
System.out.println(list.get(0)); // "Hello"
