# Student Management & Grading System

A console-based **Student Management & Grading System** built in core Java, designed as a learning project to apply and demonstrate fundamental Java and Object-Oriented Programming (OOP) concepts in a single, cohesive codebase.

The system allows an admin/operator to create students and teachers, assign and update marks, calculate grades, and generate formatted reports — all through an interactive, menu-driven console application.

## Features

- Create students with marks across multiple subjects
- Create teachers and assign them to the students they teach
- Add or update a student's marks (via a teacher)
- Generate a detailed report card for any student (marks, total, average, grade, pass/fail)
- Generate a summary report for any teacher
- View all reports (students and teachers) at once
- Input validation throughout (handles invalid types, out-of-range selections, and empty lists gracefully)

## Concepts Demonstrated

This project was built to intentionally cover the following Java and OOP concepts:

**Core Java**
- Variables, data types, literals, and type conversion
- Arithmetic, relational, and logical operators
- Conditionals: if-else, if-else-if, switch
- Loops: for, while, do-while, enhanced for
- Arrays and collections (`List`, `Map`)
- Strings and `StringBuilder`

**Object-Oriented Programming**
- Classes and objects
- Encapsulation (private fields, getters/setters)
- Constructors (default and parameterized)
- Static variables, static methods, and static blocks
- Inheritance (`Person` → `Student`, `Person` → `Teacher`)
- Method overriding and dynamic method dispatch
- Interfaces and polymorphism (`Reportable`)
- Upcasting and downcasting
- Packages and access modifiers

## Project Structure

The project follows a simple package-by-responsibility structure:

```
src/
├── app/
│   └── Main.java              # Entry point, menu loop, user interaction
├── entity/
│   ├── Person.java            # Abstract base class
│   ├── Student.java           # Extends Person, implements Reportable
│   ├── Teacher.java           # Extends Person, implements Reportable
│   ├── Subject.java           # Represents a subject and its max marks
│   └── Reportable.java        # Interface contract for report generation
└── service/
    └── GradeCalculator.java   # Static utility for grade/percentage calculation
```

| Package | Responsibility |
|---|---|
| `entity` | Domain model — data-holding classes representing people and academic structures |
| `service` | Business logic decoupled from the entities (e.g., grading policy) |
| `app` | Application entry point and console menu orchestration |

## Class Diagram

![Class Diagram](docs/class.diagram.drawio.png)

The diagram shows the full relationship set: inheritance (`Person` → `Student`/`Teacher`), interface realization (`Reportable`), associations (`Student`/`Teacher` ↔ `Subject`, `Teacher` ↔ `Student`), and dependencies (`Student`/`Teacher` → `GradeCalculator`).

## How to Run

### Option 1: IntelliJ IDEA
1. Clone or download this repository
2. Open the project folder in IntelliJ IDEA
3. Locate `Main.java` under `src/app/`
4. Right-click and select **Run 'Main.main()'**

### Option 2: Command line
From the project root:
```bash
cd src
javac app/*.java entity/*.java service/*.java
java app.Main
```

## Sample Usage

On launch, the program displays a menu:
```
Please choose one of the following options:
0: Close application.
1: Create a student.
2: Create a teacher.
3: Add a mark (via a teacher).
4: Update a mark (via a teacher).
5: View a student's report.
6: View a teacher's report.
7: View all reports
```

The application starts with two sample students (Omar, Ahmed) and one sample teacher (Rezk) pre-loaded, so reports can be viewed immediately via option `5`, `6`, or `7`.

## Notes

- This project uses in-memory storage (`ArrayList`/`HashMap`) — no database is involved, as the focus is on core Java and OOP fundamentals rather than persistence.
- Grading logic is intentionally decoupled from `Student`/`Teacher` into `GradeCalculator`, so grading policy can be changed without modifying the entity classes.