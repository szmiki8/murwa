# Mini URL Web Application

A project for demonstration.

**Table of Contents**

[Purpose](#purpose)  
[Requirements](#requirements)  
[Specification](#specification)  
[Testing and Running](#testing_running)

<a name="purpose"></a>
## Purpose

This project is a demonstration of my actual
skills including but not limited to Java, Spring Framework,
TypeScript, Angular, Angular Material.

<a name="requirements"></a>
## Requirements

Murwa (Mini URL Web Application) is a server-client
application which makes its users able to shorten any URLs.

* The system makes long URLs short
* Users can request exact URL shortening (which is not in use) 
* Two arbitrary URLs have different short formats
* The system provides a navigable preview
* The system does not handle users
* The system persist the long and short URLs
* The system provides a list of all the URLs handled

<a name="specification"></a>
## Specification

The user is able to create a shortened URL using a form and
able to view the shortened URLs in a list using a web browser.
Picking one shortened URL from the list the user can watch
a preview and navigate to the URL. The system automatically
generates a short URL (containing a unique token). The user
is able to provide this unique token.  

The system stores all shortened URL in relational database.
Every URL gets an incrementing decimal ID. This ID is converted
to base 62 using the following characters:
- 0..9 -> 0..9
- 10..35 -> a..z
- 36..61 -> A..Z

E.g. "AgL" string represents 139423.

<a name="testing_running"></a>
## Testing and Running

#### Requirements

Java Runtime Environment 1.8 or later is required to test
and run the application. 

#### Testing

To run the unit and integration tests please clone the repository
or download the source code directly. In the root of the source
code you may execute one of the following commands:

- ./mvnw test (On Linux/Unix systems)
- mvnw.cmd test (On Windows systems)

#### Running

To run the application you should execute one of the following
commands in the root of the source directory.

- ./mvnw spring-boot:run (On Linux/Unix systems)
- mvnw.cmd spring-boot:run (On Windows systems)

You can access the application in your browser on the following
address:

<a href="http://localhost:8080/">http://localhost:8080/</a>
