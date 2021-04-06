# Don't forget
(under development)

_My first personal project. It is much more than a reminder app. 
There are several benefits that make it a mandatory application in anyone's life._

You can check it here: https://app.vladutit.ro


## **I. Project presentation:**

The project is a web application that can be accessed from any device. 
This idea of the application was born from personal experiences. 
At the beginning of the pandemic, I bought a lot of products, and then they expired because there were many, 
it was easy for me to forget about them and another example, I forgot to do the mandatory car overhaul on time.
What can you do with this app? You will save money and energy, because you will never forget. 
You can enter any expired product or service. 
When an item is due to expire, we will receive an email/ SMS notification. 
To access the application from anywhere in the world, from any device, you will need to create a username and password. 
For easy use, we can create categories. Each item will contain a name, expiration date and a description. 
If a product has expired, you will receive a notification, with a joke, so as not to be sad. 
I'll take them from a public API.

## **II. Technologies used:**

#### BACKEND:
JAVA with SPRING BOOT;

#### FRONTEND:
THYMELEAF with BOOTSTRAP, Jquery, Javascript;

#### DATABASE:
MySQL.

## **III. Dependencies from Spring:**

### Spring Data JPA
Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
### Thymeleaf TEMPLATE ENGINES
A modern server-side Java template engine for both web and standalone environments. 
Allows HTML to be correctly displayed in browsers and as static prototypes.
### Spring Web
Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
### Spring Security
Highly customizable authentication and access-control framework for Spring applications.
### Spring Boot DevTools
Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
### Validation I/O
Bean Validation with Hibernate validator.
### MySQL Driver
MySQL JDBC and R2DBC driver.
### Java Mail Sender I/O
Send email using Java Mail and Spring Framework's JavaMailSender.
### Tomcat
Deploy a Spring Boot WAR into a Tomcat Server.

## **IV. Third part dependencies for Spring:**

### Twilio
Twilio Java client makes sending messages incredibly easy.
https://www.twilio.com/blog/sms-spring-boot-app
### Thymeleaf extras
The Spring Security Dialect is a Thymeleaf extras module which, naturally, helps integrate both of these together.
https://github.com/thymeleaf/thymeleaf-extras-springsecurity
### Thymeleaf layout dialect
A dialect for Thymeleaf that lets you build layouts and reusable templates in order to improve code reuse.
https://github.com/ultraq/thymeleaf-layout-dialect
