# Practice
Устанавливаем Intellij
 ![Intellij](https://github.com/Pomelogranate/Practice/raw/main/Practice/images/Рисунок1.png)
![Intellij](https://github.com/Pomelogranate/Practice/raw/{branch}/{path}/image.png)
![Intellij](https://github.com/Pomelogranate/Practice/raw/{branch}/{path}/image.png)
![Intellij](https://github.com/Pomelogranate/Practice/raw/{branch}/{path}/image.png)

 
 
 
Заходим на https://start.spring.io/
 
В папке src/main/java/ru/neoflex/practice создаём папку controller. В ней (/controller) создаём файл CalcController.java 
Над классом указываем аннотацию @RestController
Создаём 2 public метода с аннотациями @GetMapping("/plus/{a}/{b}") и @GetMapping("/minus/{a}/{b}"), которые принимают 2 аргумента, типа Integer, а возвращают их сумму/разность соответственно. Перед каждым аргументом метода необходимо поставить @PathVariable("<a или b соответственно для каждого аргумента>")
package ru.neoflex.practice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a+b;
    }
    @GetMapping("/minus/{a}/{b}")
    public Integer Min(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        return a-b;
    }
}

Запускаем сервис, по зеленой кнопке сверху справа в окне Intellij IDEA
Тестируем своё приложение по адресу http://localhost:8080/<адрес_и_параметры_для_2х_созданных_АПИ> 
Сложение
 
Вычитание
 
Подключить swagger 
