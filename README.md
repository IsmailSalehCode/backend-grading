**Features**: User Management (Registration, Authentication), Authorization, Validation Middleware & CRUD operations.

**PostMan collection ready for import**: https://drive.google.com/file/d/1dyDmaNWEgmzerXXbJPDG9JypXP6XkBe9/view?usp=sharing

**Final grade**: *Отличен (6)*

# Presentation notes

**Entity relationships**:
- Ученик към оценки: One-To-Many; Множество оценки могат да принадлежат към определен ученик.
- Курс към оценки: One-To-Many; Множество оценки могат да принадлежат към един курс.
- Ученик към курс: Many-To-Many; Един ученик може да кара няколко курса. Един курс може да бъде провеждан с множество ученици. Таблицата `course_student` обединява таблицата course с таблицата student.

В API-то всяка постъпваща заявка минава през `SecurityFilterChain`, имплементиран в **SecurityConfig.java**. `ExceptionHandlerFilter` хваща грешките, възникнали при обработката на заявки във филтрите на SecurityFilterChain-а.

За заявките са налични **3 пътеки**:

## Регистрация

Едно от правилата във веригата ни гласи, че регистрирането, което представлява POST заявка, е отворено за всекиго:

`.antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()`

Заявката не минава през security filters, обаче Exception-ите са си handle-нати. Ако е подаден невалиден JSON, приложението връща статус код 400.

При получен от клиентската страна статус код 201, можем да отворим h2 конзолата си и да видим новосъздадения user. Паролата е еднопосочно хеширана с bcrypt. Не можем да я декодираме, но и не ни трябва. Важното е единствено потребителят да си я знае.

## Вход

1. POST заявка към /authenticate
2. Инвокира се attemptAuthentication метода в AuthenticationFilter.
   `request.getInputStream()`- извличане на данните от тялото на заявката в двоичен формат.
   **ObjectMapper** десериализира съответните двоичните данни в Java обект от тип User.

```
 User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
```

3. Създава се обект от тип `Authentication` чрез класа `UsernamePasswordAuthenticationToken`, като се използват постъпилите потребителско име и парола.
4. Този обект за аутентикация се предава на нашия `CustomAuthenticationManager` за извършване на фактическата аутентикациа.
5. В `CustomAuthenticationManager` еднопосочно се хешира постъпилата парола.
6. Ако полученият хеш съвпада със съхранения за посочения user, то `AuthenticationManager` връща същия `Authentication` обект обратно на `AuthenticationFilter`.
7. Инвокира се `successfulAuthentication()`, където се създава и връща подписан JWT обратно на клиента заедно с положителен статус код.

Diagram of how a successful auth procedure would work:
![Diagram of how a successful auth procedure would work](https://github.com/IsmailSalehCode/backend-grading/assets/55927975/1eb04d62-772f-4062-b4e5-8bb16822923c)

## Оторизация

Нататък всички CRUD операции изискват клиентът да е оторизиран. Това означава в header-а на заявките му да присъства валиден JWT.

Дигиталният подпис в JWT е частта, която не може просто да се base64 декодира съответно и да се фалшифицира. Това е така, защото за подписа се използва секретен ключ, който е единствено известен на нашето API.

- При постъпването на заявка с JWT нашето API разбива токена на съставните му части- header, payload и дигитален подпис. Използвайки алгоритъма в header-a и server-side съхранения таен ключ, API-то създава тестов дигитален подпис и го сравнява с подадения.
- Иначе дигиталният подпис е комбинация от хедъра, пейлоуда и секретния ключ. Макар base64 кодирана (лесна за деокдиране и манипулация), ако някой се опита да промени expiration датата на токена, то дигиталният подпис ще стане невалиден -> JWTVerificationException се хвърля в JWTAuthFilter-а, хваща се в ExceptionHandlerFilter-а и клиентът получава статус код 403 и съобщ-е "JWT NOT VALID".
- Ако валидацията е успешна, API-то задава аутентикацията в SecurityContextHolder, така че Spring Security да знае кой потребител е оторизиран вмомента. След като информацията за удостоверяване е зададена в контекста на сигурността, другите части на приложението могат да я получат. Това позволява на приложението да взема решения за разрешение въз основа на правомощията на текущия потребител. Накратко, методът SecurityContextHolder.getContext().setAuthentication(authentication) се използва за свързване на текущия потребител с контекста на сигурността. Това позволява на Spring Security да управлява и разпространява информацията за удостоверяване в приложението.

---

Съответно в ExceptionHandlerFilter се хващат и други грешки, които е възможно да възникнат по време на работата на филтрите в SecurityFilterChain-а. Причината да не можем да създадем Exception Handler в Controller Advice е заради позицията на Dispatcher Servlet спрямо security филтрите в нашата архитектура:
![diagram-reason_for_before+after_filters](https://github.com/IsmailSalehCode/backend-grading/assets/55927975/262f52d4-6580-4a29-b2f6-9d679644450d)

---

```
//Откъс от SecurityConfig.java
http
    .headers(headers -> headers.frameOptions().disable())
    .antMatchers("/h2/**").permitAll()
```

The above are not spring security configurations we would want to use in production. These are only so that we can enable the H2 console.
In production we would never use H2. We'd rather use a DBMS so that data gets saved to disk. For dev H2 is alright cuz its a lightweight in-memory DB.
http://localhost:8080/h2



