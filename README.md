# Ecommerce Web Application
- Developed a web application based on Spring MVC to show integration with Stripe payment gateway.

## Table of contents
* [1. Project Description]()
* [2. Infrastructure Design]()
* [3. Spring MVC Design]()
* [4. Database Implementation]()
* [5. Requirements]()
* [6. Instructions on how to run for local development]()

## 1. Project Description 

- `Spring Web MVC` provides Model-View-Controller architecture to develop flexible web applications
   * **Model** carries application data
   * **View** renders User interface (UI)
   * **Controller** takes care of processing user request and calling back end services.
   
## 2. Infrastructure Design
- 3-tier architecture
   * Presentation tier: HTML
   * Data tier: MySQL
   * Logic tier: Java, Hibernate

## 3. MVC Implementation
- Controller
   * Product
- Model
   * BillingAddress
   * Product
   * SalesOrder

## 4. Database Implementation
- MySQL
   * **BillingAddress** - stores billing address
   * **SalesOrder** - stores sales order 
   * **Product** - stores product information
   


## 5 . Requirements
* Java >= 11 
* MySQL 
* [Maven 3](https://maven.apache.org)

## 6 . Instructions how to run for local development

To run this quickstart as a standalone project on your local machine:

. Download the project and extract the archive on your local filesystem or via git clone
. Navigate to the the resource folder (src/main/resources) and update stripe and database properties
. Build the project:
+[source,bash,options="nowrap",subs="attributes+"]
----
$ cd PROJECT_DIR
$ mvn clean package
----
. Run the service:

+
[source,bash,options="nowrap",subs="attributes+"]
----
$ mvn spring-boot:run
----

You can then access  directly from your Web browser, e.g.:
- <http://localhost:9013>

