# Spring Boot MVC

## Task

Create a web application that allows :

1. registration of new users
2. user's autorization
3. CRUD operations on objects

##### Product
* UUID id
* String name
* BigDecimal price
##### Producer
* UUID id
* String name
* Set&lt;Product> products
##### Role
* UUID id
* String name
##### User
* UUID id
* String email
* String password
* String firstName
* String lastName
* Set&lt;Role> roles

###### Roles
1. Admin is allowed all CRUD operations on all oblects
2. User is allowed all CRUD operations on Product and Producer. CRUD operations on Users is forbidden

###### Pages

1. Producers - the list of producers and the ability to create, update, delete producers
2. Products - the list of products and the ability to create, update, delete producers
3. Users - the list of users and the ability to create, update, delete producers (only for Admin)

## Instructions

To deploy application on local computer you need:
1. Java 17
2. Postgres 10 or higher
3. Correct module8Spring/src/main/resources/application.yml:
* url: jdbc:postgresql://localhost:5432/warehouse - 'localhost:5432' replace with Postgres server, 'warehouse' replace with empty database name
* username: sa - 'sa' replace with user having access to defined database
* password: 1 - '1' replace with database user password
4. Credentials for default users (on start):
* Admin - login 'admin@admin.org', password 'P@ssword?654'
* User - login 'user@user.org', password 'user'

Link to video demostration <https://www.youtube.com/watch?v=4FaNt5RDgg8>
