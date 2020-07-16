# Don't forget

_My first personal project. It is much more than a reminder app. There are several benefits that make it a mandatory application in anyone's life._

You can check it here: https://don-t-forget-v2.herokuapp.com


## **I. Project presentation:**

The idea of the project would be to be able to enter perishable products and services in a database (some details / information about them and most importantly, the expiration date).
Let me know by SMS and / or email when it expires.

In the application to be able to put them all in categories so that I can manage them more easily.

Foods with a predefined time (vegetables, fruits, eggs, milk, cheese, yeast, wine, borscht, etc.). For the foods just listed, because they do not have a fixed expiration date, we can select these products from a list and automatically depending on the selected food to appear 2/3 predefined time suggestions in which those products could last (an example : you choose tomatoes, it automatically appears that you can select that they can expire in 3 days / 6 days / 9 days).

To be able to select how long before each product / service is notified to me expires and how many times or at what time to repeat until I receive the notice that it has expired. From the "general settings" I can set a "default mode" to the repeat functionality, including whether the expiration notice should be repeated until I manually delete the product / service or notify me once, and it will be deleted automatically.

Another feature would be to be able to enter the products through the barcode (first to enter it manually, then in the future, a possibility to scan with the camera) and the next time you enter that code, this product will appear and you will not fill in all the information about him again, but just put the expiration date.

When he notified me by e-mail / sms that a product / service has expired, to have directly from there 2/3 links that depending on what I access, to produce an action in the application (examples: Delete product | Delete product and add it to your shopping list)

When I delete a product, there should be the possibility to create a shopping list with these products, this action should take place only if I tick a box when deleting.

For the category "perishable products", I would like to be able to make a report with the products that have expired, what consumption I had over a certain period, how many products were consumed on time and how many were consumed just not to throw them away (here a to be taken after a check mark that I will put when I delete the product).

For the “services” category, for example insurance, when it notifies me that it is about to expire, suggest a contact person / company for this service.

Have a user account, password and be able to log in from multiple devices or add what other users can access your data and can add or modify so that the whole family can access.

I would also have an idea for suggesting recipes based on the products I have; food recipes by categories (staple foods, optional foods, spices, etc.); to suggest my recipes in order according to how many foods I already have from what I need; when I choose the recipe, to automatically add to my shopping list the products we still need to prepare the recipe; to be able to rate the recipe after I have done it and then to appear in my suggestions according to this criterion, but first to make the beginning.

The application is not only about perishable products, but also about services: insurance, guarantees and more. A reminder application could be easily cataloged and in its basic form it is.

## **II. The first steps:**

-Create the diagram with classes how they communicate with each other;

-Page with product / service introduction form with name, description, expiration date;

-Possibility to create a new category;

-Creating a database;

-Method to notify me that the product has expired (e-mail / sms).

## **III. Technologies for:**

#### BACKEND:

JAVA with SPRING BOOT;

#### FRONTEND:

THYMELEAF with BOOTSTRAP.

## **IV. Dependencies for Spring:**

### Spring Web:
 Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
 
### Thymeleaf
 
 A modern server-side Java template engine for both web and standalone environments. Allows HTML to be correctly displayed in browsers and as static prototypes.
 
### Spring Data JPA SQL
 
 Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
 
 
 