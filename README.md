# Практика
## _**Задание №1 - Создание клиент-серверного приложения используя Spring Framework Java**_ <br>
Устанавливаем Intellij <br>
 ![Intellij](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок1.png)
 ![Intellij](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок2.png) 
 ![Intellij](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок3.png) 
 ![Intellij](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок4.png)<br>
Заходим на https://start.spring.io/<br>
  ![spring](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок5.png)<br>
<br>

## _**Задание №2 - Создание контроллера**_

<br>
В папке src/main/java/ru/neoflex/practice создаём папку controller. В ней (/controller) создаём файл CalcController.java <br>
Над классом указываем аннотацию @RestController<br>
Создаём 2 public метода с аннотациями @GetMapping("/plus/{a}/{b}") и @GetMapping("/minus/{a}/{b}"), которые принимают 2 аргумента, типа Integer, а возвращают их сумму/разность соответственно. Перед каждым аргументом метода необходимо поставить @PathVariable("<a или b соответственно для каждого аргумента>")
<br>
 
```
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
```
<br>
Запускаем сервис, по зеленой кнопке сверху справа в окне Intellij IDEA <br>
Тестируем своё приложение по адресу http://localhost:8080/<адрес_и_параметры_для_2х_созданных_АПИ> <br>
Сложение<br>
  ![work+](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок6.png)<br>
Вычитание<br>
  ![work-](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок7.png)<br>


 ## _**Задание №3 - Подключение Swagger 3.0.0**_ 
 
 <br>
Добавляем в pom.xml необходимую зависимость<br>
 
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```
Результат неправильной ссылки<br>
![Wrong](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок8.png)<br>
Результат правильной ссылки http://localhost:8080/swagger-ui/index.html <br>
![Right](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок9.png)<br>
Как выглядит класс в документации swagger <br>
![example](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок10.png)<br>
Сложение в swagger<br>
![Swork+](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок11.png)<br>
Вычитание в swagger<br>
![Swork-](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок12.png)<br>
<br>


## _**Задание №4 - Создание тестов на проект**_  


<br>
Добавляем в pom.xml необходимую зависимости <br>

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-test-autoconfigure</artifactId>
</dependency>
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>RELEASE</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

<br>
Код тестов
<br>

```
package ru.neoflex.practice.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(CalcController.class)
@AutoConfigureMockMvc
public class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalcController calcController;

    @Test
    public void sum() throws Exception {
        int a = 15;
        int b = 20;
        int expectedSum = 35;//a+b;
        BDDMockito.given(calcController.Sum(a,b)).willReturn(a+b);
        mockMvc.perform(MockMvcRequestBuilders.get("/plus/{a}/{b}",a, b)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedSum)));
    }

    @Test
    public void min() throws Exception {
        int a = 20;
        int b = 15;
        int expectedMin = 5;//a-b;
        BDDMockito.given(calcController.Min(a,b)).willReturn(a-b);
        mockMvc.perform(MockMvcRequestBuilders.get("/minus/{a}/{b}", a, b)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedMin)));
    }
}
```
Результаты тестов<br>
![Test](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок13.png)<br>
<br> 


## _**Задание №5 - Подключение in-memory БД**_


<br>
Добавляем в pom.xml необходимую зависимости <br>

```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```
<br>
Добавляем в файл application.properties строки 
<br>

```
spring.application.name=practice

spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=savina
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
```
<br>
Создаём файлы базы данных (DatabaseRes) и репозитория (RepositoryRes) <br>
![Repository](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок14.png)<br>
Код базы данных<br>

```
package ru.neoflex.practice.DataBase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "DatabaseRes")
public class DatabaseRes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private int ID;

    @Column(name = "first_number")
    private int firstNum;

    @Column(name = "action")
    private String action;

    @Column(name = "second_number")
    private int secondNum;

    @Column(name = "result")
    private int result;

    public DatabaseRes(int firstNum, String action, int secondNum, int result) {
        this.firstNum = firstNum;
        this.action = action;
        this.secondNum = secondNum;
        this.result = result;
    }
}
```
<br>
Код репозитория
<br>

```
package ru.neoflex.practice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.neoflex.practice.DataBase.DatabaseRes;

import java.util.List;

@Repository
public interface RepositoryRes extends JpaRepository<DatabaseRes, Integer> {
@Query("Select db from DatabaseRes db")
List<DatabaseRes> findAllRes();
}
```
<br>
Изменнёный код контроллера
<br>

```
package ru.neoflex.practice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.DataBase.DatabaseRes;
import ru.neoflex.practice.Repository.RepositoryRes;

import java.util.List;

@RestController
public class CalcController {

    @Autowired
    public RepositoryRes RepositoryRes;

    @GetMapping("/plus/{a}/{b}")
    public Integer Sum(@PathVariable("a") Integer a, @PathVariable("b") Integer b) {
        RepositoryRes.save(new DatabaseRes(a,"+",b,a+b));
        return a+b;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Min(@PathVariable("a") Integer a, @PathVariable("b") Integer b) {
        RepositoryRes.save(new DatabaseRes(a,"-",b,a-b));
        return a-b;
    }
    @GetMapping("/TableAll")////////////
    public List<DatabaseRes> GetAllRes() {
        return RepositoryRes.findAllRes();
    }
}
```
<br>
Результат без записей<br>

![Result1](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок15.png)<br>
Результат с записями<br>

![Result2](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок16.png)<br>
<br> 


## _**Задание №6 - Реализация GET-запроса**_ 


<br>
Переходим по ссылке http://localhost:8080/h2-console и вводим данные из application.properties<br>

![Registration](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок17.png)<br>
Выполняем Get-запрос<br>

![Get](https://gitlab.com/Pomelogranate/Practice/raw/main/images/Рисунок18.png)<br>
<br>
