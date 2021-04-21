# SmartCalculator
This program supports addition, subtraction, multiplication, division, powers, and calculations in parentheses.    

A general expression can contain many parentheses and operations with different priorities. Here is an example of an expression that contains all possible operations:
```
3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)
```
The result is 121.

The program should supports variables. 
The name of a variable (identifier) can contain only Latin letters. 
The case is also important. For example, n is not the same as N. The value can be an integer number or a value of another variable.
The assignment statement may look like the following:    
```
n = 3
m=4
a  =   5
b = a
v=   7
n =9
```
A variable can have a name consisting of more than one letter.
```
count = 10
```
To print the value of a variable you should just type its name.
```
N = 5
N
5
```
It is possible to set a new value to an existing variable.
```
a = 1
a = 2
a = 3
a
3
```
If an identifier or value of a variable is invalid, the program prints a message like the one below.
```
a1 = 8
Invalid identifier
n = a2a
Invalid assignment
a = 7 = 8
Invalid assignment
```
If a variable is not declared yet, the program prints "Unknown variable".
```
a = 8
b = c
Unknown variable
e
Unknown variable
```
The program supports arithmetic operations (+, -, *, /) with very large numbers as well as parentheses to change the priority within an expression.
```
112234567890 + 112234567890 * (10000000999 - 999)
1122345679012234567890
a = 800000000000000000000000
b = 100000000000000000000000
a + b
900000000000000000000000
/exit
Bye!
```
