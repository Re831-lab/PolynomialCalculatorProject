# Polynomial Equation Solver

A mathematical equation solver that handles polynomial equations using a custom Linked List implementation.

## Project Description

This project is an advanced mathematical calculator that processes polynomial equations using a custom-built Linked List data structure implemented from scratch.

## Features

- **Equation Input**: Enter two polynomial equations
- **Mathematical Operations**: 
  - Addition
  - Subtraction
  - Multiplication
- **Variable Substitution**: Substitute specific values for x
- **Quadratic Equation Solver**: Solve second-degree equations
- **Email Validation**: Validate entered email addresses
- **User-Friendly Interface**: Simple and easy-to-use design

## Technologies Used

- **Java 22** with Preview Features
- **Maven** for project management
- **Custom Linked List** - Built from scratch
- **Swing GUI** - Graphical User Interface
- **Stack Implementation** - For expression transformation

## Project Architecture

### Core Components:

1. **Custom Linked List**
   - Terms sorted by exponent
   - Automatic merging of like terms

2. **Equation Processing**
   - Mathematical expression transformation
   - Stack-based conversion

3. **Quadratic Equation Solver**
   - Handles special cases (zero coefficients)
   - Manages negative discriminants

## How to Run

### Requirements:
- Java 22 or higher
- Maven

### Commands:

```bash
# Compile the project
mvn clean install

# Run the project
mvn exec:java
```

Or via NetBeans:
```bash
# Run
mvn process-classes org.codehaus.mojo:exec-maven-plugin:3.1.0:exec
```

## What I Learned

- Building custom data structures from scratch
- Sorting and merging elements in Linked Lists
- Designing effective user interfaces
- Converting mathematical expressions using Stacks
- Handling special cases in quadratic equations

## Development Period

Developed from May 12th to May 24th

## Developer

Educational project for Data Structures course
- Rasha Zreaq

---

## License

This is an educational project available for academic use