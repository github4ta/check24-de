# check24-de - Test Automation Framework

Фреймворк для тестовой автоматизации с использованием Selenium, JUnit 5 и Allure отчетов.

## ✅ Требования

- **Java 21+** - [Скачать](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.6+** (опционально, есть wrapper)

## 🚀 Быстрый старт

### 1. Запуск тестов

#### Через Maven напрямую (если установлен Maven)
```bash
mvn clean test
```

### 2. Генерация Allure отчета

```bash
mvn allure:report
```

### 3. Открыть Allure отчет

После генерации отчета откройте:
```
target/allure-report/index.html
```

## 📁 Структура проекта

```
check24-de/
├── src/
│   ├── main/java/de/check24/
│   │   ├── config/
│   │   │   └── ConfigLoader.java      - загрузка конфигурации
│   │   ├── driver/
│   │   │   └── DriverFactory.java     - фабрика WebDriver
│   │   └── ui/pages/
│   │       └── HomePage.java          - PageObject для домашней страницы Check24
│   └── test/java/de/check24/tests/
│       └── ui/
│           ├── BaseUITest.java        - базовый класс UI тестов
│           ├── DemoTest.java          - демо тесты домашней страницы Check24
│           └── HomeTest.java          - комплексные тесты HomePage ✅
├── target/
│   ├── allure-results/    (результаты тестов)
│   └── allure-report/     (сгенерированный отчет)
├── pom.xml
└── run-tests.bat          (запуск UI тестов)
```

## 📦 Установленные зависимости

- **Selenium 4.25.0** - веб-автоматизация
- **JUnit 5.11.0** - фреймворк тестирования
- **Allure 2.29.0** - отчеты и статистика
- **REST-Assured 5.5.0** - API тестирование
- **DataFaker 2.4.0** - генерация тестовых данных
- **AssertJ 3.26.0** - удобные проверки
- **WebDriverManager 5.9.3** - управление браузер-драйверами
- **Logback 1.5.8** - логирование

## ⚙️ Конфигурация

### Allure конфигурация

Конфигурационные файлы Allure находятся в `src/main/resources/`:

- **`allure.properties`** - основные настройки Allure (пути, заголовки, ссылки)
- **`allure-categories.json`** - категории дефектов для группировки в отчетах

### Пример настройки allure.properties:

```properties
# Директории
allure.results.directory=target/allure-results
allure.report.directory=target/allure-report

# Заголовки отчетов
allure.report.title=Check24 Test Automation Report
allure.report.name=Check24 Test Suite

# Ссылки на системы
allure.link.issue.pattern=https://example.com/issue/{}
allure.link.tms.pattern=https://example.com/tms/{}
```

## ⚠️ Решение проблем

### Ошибка: "Directory target/allure-results not found"

✅ **Решение**: 
- Директория создается автоматически при запуске `mvn clean test`
- Убедитесь, что тесты запустились успешно
- Проверьте логи в консоли

### Ошибка: "Java не установлена"

✅ **Решение**: 
1. Установите Java 21+ ([ссылка](https://www.oracle.com/java/technologies/downloads/#java21))
2. Добавьте JAVA_HOME в переменные окружения Windows

### Ошибка при запуске батника

✅ **Решение**:
1. Убедитесь, что Java установлена: `java -version`
2. Запустите батник от администратора
3. Проверьте пути в файле `run-tests.bat`
