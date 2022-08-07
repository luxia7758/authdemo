# Token-based user authentication Demo

## Purpose

A very simple authentication and authorization service.


## Build
use maven

## labs

- [Lombok](https://projectlombok.org/)
Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
- [JUnit5](https://junit.org/junit5/) JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM. This includes focusing on Java 8 and above, as well as enabling many different styles of testing.
- [Mockito](https://site.mockito.org/) Tasty mocking framework for unit tests in Java

## Project Directory

- domain (Business logic)
  - entity (main entities)
  - repository (repositories for main entities)
  - service (domain service)
- application (Application Logic)
  - dto (Data Transfer Object)
  - component (application service, and some other application level components)
- infrastructure(Component implementations)

## debug
As an incomplete application, use AuthenticationService.class as debug entry