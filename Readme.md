# Autoria Platform

## Короткий опис
Проект `Autoria Platform` є Spring Boot додатком, який керує користувачами, дозволами та завантаженням файлів до Amazon S3. Він включає сервіси для реєстрації та аутентифікації користувачів, управління дозволами та обробки файлів. Проект також містить контролери для обробки HTTP-запитів, пов'язаних з користувачами та файлами, а також різні об'єкти передачі даних (DTO) для даних користувачів та аутентифікації.

## Основні можливості

- **Управління користувачами**: Реєстрація нових користувачів, аутентифікація користувачів та управління ролями.
- **Управління дозволами**: Створення та отримання дозволів для користувачів.
- **Завантаження файлів до Amazon S3**: Завантаження та завантаження файлів з Amazon S3.
- **Обробка HTTP-запитів**: Контролери для обробки запитів, пов'язаних з користувачами та файлами.
- **Логування**: Логування дій для відстеження та налагодження.

## Інструкція по установці та підключенню

### Вимоги

- Java 17 або новіше
- Maven 3.6 або новіше
- Обліковий запис AWS з доступом до S3

### Установка

1. Клонуйте репозиторій:
    ```sh
    git clone https://github.com/F0rgger/AutoRiaCloneMarch-2024.git
    cd AutoRiaCloneMarch-2024
    ```

2. Налаштуйте файл `application.properties`:
    ```properties
    # src/main/resources/application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/autoria
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    aws.accessKeyId=your_aws_access_key_id
    aws.secretKey=your_aws_secret_key
    aws.s3.bucketName=your_bucket_name
    ```

3. Запустіть додаток за допомогою Maven:
    ```sh
    mvn spring-boot:run
    ```

### Підключення
1.
    ```
   
    ```



## Опис методів

### `AutoriaApplication`

Цей клас є точкою входу для Spring Boot додатку.

- **main(String[] args)**: Метод, який запускає додаток.

### `PermissionService`

Цей сервіс відповідає за управління дозволами.

- **createPermission(String name)**: Створює новий дозвіл з заданим ім'ям.
- **getPermissionByName(String name)**: Повертає дозвіл за його ім'ям або кидає виняток `PermissionNotFoundException`, якщо дозвіл не знайдено.

### `S3Service`

Цей сервіс відповідає за взаємодію з Amazon S3.

- **uploadFile(MultipartFile file)**: Завантажує файл до S3. Створює тимчасовий файл, копіює вміст з `MultipartFile` і завантажує його до S3.
- **downloadFile(String fileName)**: Завантажує файл з S3 за його ім'ям.

### `UserService`

Цей сервіс відповідає за управління користувачами.

- **registerUser(UserDto userDto)**: Реєструє нового користувача. Приймає об'єкт `UserDto`, створює нового користувача та зберігає його в базі даних.
- **authenticateUser(LoginDto loginDto)**: Аутентифікує користувача. Приймає об'єкт `LoginDto`, перевіряє облікові дані та повертає токен аутентифікації.

### `UserController`

Цей контролер обробляє HTTP-запити, пов'язані з користувачами.

- **register(@RequestBody UserDto userDto)**: Обробляє POST-запит для реєстрації нового користувача.
- **login(@RequestBody LoginDto loginDto)**: Обробляє POST-запит для аутентифікації користувача.

### `FileController`

Цей контролер обробляє HTTP-запити, пов'язані з файлами.

- **uploadFile(@RequestParam("file") MultipartFile file)**: Обробляє POST-запит для завантаження файлу до S3.
- **downloadFile(@PathVariable String fileName)**: Обробляє GET-запит для завантаження файлу з S3.

### `User`

Цей клас представляє сутність користувача.

- **id**: Унікальний ідентифікатор користувача.
- **username**: Ім'я користувача.
- **password**: Пароль користувача.
- **roles**: Список ролей користувача.

### `Role`

Цей клас представляє сутність ролі.

- **id**: Унікальний ідентифікатор ролі.
- **name**: Назва ролі.

### `Permission`

Цей клас представляє сутність дозволу.

- **id**: Унікальний ідентифікатор дозволу.
- **name**: Назва дозволу.

### `UserDto`

Цей клас використовується для передачі даних користувача.

- **username**: Ім'я користувача.
- **password**: Пароль користувача.

### `LoginDto`

Цей клас використовується для передачі даних аутентифікації.

- **username**: Ім'я користувача.
- **password**: Пароль користувача.

### `PermissionNotFoundException`

Цей клас представляє виняток, який кидається, коли дозвіл не знайдено.

- **PermissionNotFoundException(String name)**: Конструктор, який приймає ім'я дозволу, що не знайдено.