# CS111 Cheatsheet

## Computer representation of numbers:

Due to the way hardware is designed computers represent numbers in binary. *Bi*nary, or *base 2* is just like our normal number system, except instead of counting to 10 (0-9), we count to 2 (0-1). Operations like addition, subtraction, multiplication, and division work exactly as expected in this (and all other) number systems. Why do we use hex? Mainly for convenience and brevity, 256 = 0b11111111 = 0xFF. `0b` represents binary, and `0x` represents hex. To learn more about converting between different number systems, check out (this video by KhanAcademy)[https://www.khanacademy.org/math/algebra-home/alg-intro-to-algebra/algebra-alternate-number-bases/v/number-systems-introduction].

## Basic Java Syntax

Comments - a way for us to leave notes for ourselves and other developers. There are three main types of comments in java, single line comments, multi line comments, and the niche javadoc style comments that help automatically generate (documentation like this)[http://docs.oracle.com/javase/7/docs/api/java/lang/String.html]

```
// Single line comments

/*
 * multiple 
 * line
 * comments
 */

/**
 * javadoc style comments
 * @param int - does this
 * @return ...
 */
```

Declaration - a way for us to tell the computer to make space for some information, and how we're going to refer to this information.

```
// this creates a variable named a
int a;
```

Declaration and Initialization - a way for us to declare a variable with an initial value;

```
// this creates a variable named a, and gives it the value 5
int a = 5;
```

## Primitive Types

Primitive types are the foundation of data structures in java, and all programming languages for that matter. Even the most complex class that handle networking, and multi threading are just a collection of primitives, functions, and other objects (which are also just collections of primitive types, functions, and other objects (which are also ... [this is how recursion will work in cs112])). Java's primitive objects are: 

* byte - smallest int
* short - smaller int
* int - ...integer
* long - bigger int
* float - less precise double
* double - ...double, a way to store floating point values
* boolean - true or false 
* char - used to store 16-but unicode characters. Everything from letters, to chess symbols. Cans sometimes just be used to store numbers too.

(Refer to this very useful place for: default values, ranges, and literal formats)[https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html]

## Casting 

When you try to do things like this: 

```
double pi = 3.1415;
int a = pi;
```

the compiler will tell you that this could possibly result in loss of information. We tell the compiler we know what we're doing by "Casting", as such: 

```
double pi = 3.1415;
int a = (int) pi;
```

we can also utilize casting when we want to treat a variable as another type, temporarily:

```
int studentsInClass = 20;
int slicesOfPizza = 200;

double averageSlicesPerStudent = (double) slicesOfPizza / (double) studentsInClass; //wouldn't that be great?
```

In reality we only need to cast one of these to a double, since double/int will result in a double.

For the details on casting, promotion and type conversions, (refer to this useful site)[http://docs.oracle.com/javase/specs/jls/se7/html/jls-5.html]

## Order of operations

Works as expected *for the most part* (NOTE: `>>>` != `>>` != `>` != `|` != `||`):

1. `num++`, `num--`
2. `++num`, `--num`, `~`, `!`
3. `*`, `/`, `%`
4. `+`, `-`
5. `<<`, `>>`, `>>>`
6. `==`, `!=`
7. `&`
8. `^` (NOTE: not exponent)
9. `|` 
10. `&&`
11. `||`
12. `?` `:`
13. `=`, `+=`, `-=`, `*=`, `/=`, `%=`, `&=`, `^=`, `|=`, `<<=`, `>>=`, `>>>=`

## Functions

Functions follow the following format:
```
ScopeDetails Type name(Arg1Type arg1, Arg2Type arg2, ...) {
	...
	return Type;
}
```

Functions give us a great way to organize code. They make code easier to test, read, and maintain.

The following function takes a string and tells us wether it's a palindrome or not.

```
public static boolean istPalindrom(String s){
	char[] word = s.toCharArray();
    int i1 = 0;
    int i2 = word.length - 1;
    while (i2 > i1) {
        if (word[i1] != word[i2]) {
            return false;
        }
        ++i1;
        --i2;
    }
    return true;
}
```

We can call this function simply by `isPalindrome("racecar")`. What would the result of that function call be?

If a function is written by someone else, and exists outside your class you can access it a few different ways. If it's a static function, then you mearly need to call `ClassName.functionName(args)`. This includes functions that exist in java.lang's `Math` library, and Rutger's `IO` library.

## Conditionals

The syntax for a conditional statment is:
```
if (statment) {
	do this
} else {
	do this instead
}
```

A few examples of this in action:

```
public void age(int a) {
	if (a < 18) {
		System.out.println("minor");
	} else {
		System.out.println("adult");
	}
}
```

Watch out for things that disrupt the flow of conditional statements, things such as `return` statements. (return statements exit the current function. If it's void it's just `return`, otherwise it's `return value`)
```
public void age(int a) {
	if (a < 18) {
		System.out.println("minor");
		return;
	} 
	System.out.println("adult");
}
```

These two blocks of code are identical. Follow the logic of the code to confirm yourself. Always verify.

There are also `else if` statements:

```
public void printGrade(int grade) {
	if (grade > 90) {
		System.out.println("A");
	} else if (grade > 80) {
		System.out.println("B");
	} else if (grade > 70) {
		System.out.println("C");
	} else {
		System.out.println("F");
	}
}
```

Where only one of these System.out.println's will execute. Compare the output of that with this:
```
public void printGrade(int grade) {
	if (grade > 90) {
		System.out.println("A");
	} 
	if (grade > 80) {
		System.out.println("B");
	} 
	if (grade > 70) {
		System.out.println("C");
	} else {
		System.out.println("F");
	}
}
```

Study the difference.

There are also `Switch` statements. These are not as common, you can see an example of one below. Read more about them (here)[https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html].
```
public class SwitchDemo {
    public static void main(String[] args) {

        int month = 8;
        String monthString;
        switch (month) {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);
    }
}
```
What does this program do?

## Looping 

### For Loop

A for loop that will print 1-10

```
         for(int i=1; i<11; i++){
              System.out.println("Count is: " + i);
         }
```

An infinite loop:

```
// infinite loop
for ( ; ; ) {
    
    // your code goes here
}
```

### While loop

The equilivilant while loop:

```
        int count = 1;
        while (count < 11) {
            System.out.println("Count is: " + count);
            count++;
        } 
```

Equivilant infinite loop:

```
        int count = 1;
        while (count < 11) {
            System.out.println("Count is: " + count);
            count++;
        }
```

### Do While loop

```
do {
     statement(s)
} while (expression);
```

Equivilent do-while loop:

```
        int count = 1;
        do {
            System.out.println("Count is: " + count);
            count++;
        } while (count < 11);
```

