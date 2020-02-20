# task3, task 4
CRUD приложение. Tomcat,maven, JSP, сервлеты, JDBC. У User имеются следующие поля: login, password, country. 
В приложении должна быть страница на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера. 
Конфигурация сервлетов через аннотации.
В resources содержится файл congig.properties, который содержит ключ "тип дао" и значение, соответствующее одному из DAO. Так же есть фабрика, которая генерирует на основе файла congig.properties обьект определенного типа дао.
У всех пользователей есть поле - role(user/admin).
Имеются 2 сервлетных фильтра, которые после авторизации пользователя отправляют пользователей с ролью user на страницу /user,
 а пользователей с ролью admin на страницу /admin.
Пользователь с ролью user, НЕ может заходить на страницы, которые начинаются с /admin.
Пользователь с ролью admin может заходить на страницы, начинающиеся с /user
