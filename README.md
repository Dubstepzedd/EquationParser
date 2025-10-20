# EquationParser

A mathematical expression parser and interpreter for Java, built using the Interpreter design pattern.

## Features

- **Mathematical Operations**: Addition, subtraction, multiplication, division, modulo
- **Trigonometric Functions**: `sin()`, `cos()`
- **Variable Support**: Define and use custom variables in expressions
- **Expression Evaluation**: Parse and evaluate complex mathematical expressions at runtime
- **Tokenization**: Built-in lexical analyzer for expression parsing
- **Extensible Architecture**: Clean separation between operators, expressions, and tokenization

## Quick Start

```java
InterpreterContext context = new InterpreterContext();
context.setIdentifier("PI", (float) Math.PI);

ExpressionParser parser = new ExpressionParser();
AbstractExpression expr = parser.parse("sin(PI/2)*2 + cos(1-1) + 5*7");

System.out.println(expr.interpret(context)); // Output: 38.0
```

## Usage

### Basic Arithmetic

```java
InterpreterContext context = new InterpreterContext();
ExpressionParser parser = new ExpressionParser();

AbstractExpression expr = parser.parse("2 + 3 * 4");
float result = expr.interpret(context); // 14.0
```

### Using Variables

```java
InterpreterContext context = new InterpreterContext();
context.setIdentifier("x", 10.0f);
context.setIdentifier("y", 5.0f);

ExpressionParser parser = new ExpressionParser();
AbstractExpression expr = parser.parse("x * y + 20");
float result = expr.interpret(context); // 70.0
```

### Trigonometric Functions

```java
InterpreterContext context = new InterpreterContext();
context.setIdentifier("PI", (float) Math.PI);

ExpressionParser parser = new ExpressionParser();
AbstractExpression expr = parser.parse("sin(PI/2)");
float result = expr.interpret(context); // 1.0
```

## Architecture

This project implements the **Interpreter design pattern** with a clean, extensible architecture:

- **AbstractExpression**: Base interface for all expression types
- **BinaryExpression**: Handles binary operations (addition, subtraction, multiplication, division, modulo)
- **UnaryExpression**: Handles unary operations (sin, cos)
- **NumberExpression**: Represents numeric literals
- **IdentifierExpression**: Represents variables
- **InterpreterContext**: Stores variables and their values
- **Tokenizer**: Lexical analyzer that breaks input strings into tokens
- **ExpressionParser**: Parses token streams into executable expression trees

### Operator System

The project uses an operator info pattern where each operator is defined by:

- **BinaryOperatorInfo**: Defines binary operators with precedence and associativity
- **UnaryOperatorInfo**: Defines unary operators (functions)

## License

This project is available as open source under the terms of the [MIT License](LICENSE).

## Contributing

This is a portfolio project, but suggestions and improvements are welcome! Feel free to open an issue or submit a pull request.
