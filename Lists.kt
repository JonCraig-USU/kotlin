// Do not remove or rename the package
package lists

// Imports
import kotlin.math.*

/*
* The following functions are helper functions that I am providing
*/

/*
* Extend the List class with a "tail" getter to get the tail of a list.
* Below is an example of how you would use tail
*    val a = listOf(1,2,3)
*    val t = a.tail
*    println("tail of $a is $t") // prints [2,3]
*/
val <T> List<T>.tail: List<T>
get() = drop(1)

/*
* Extend the List class with a "head" getter to get the head of a list.
* Below is an example of how you would use head
*    val a = listOf(1,2,3)
*    val h = a.head
*    println("head of $a is $h") // prints 1
*/
val <T> List<T>.head: T
get() = first()

/* 
* The isPrime function takes as input an Int
*      x : an Int object to test
* and returns a Boolean
*      true  if x is a prime
*      false if x is not a prime
*/
fun isPrime(x : Int) : Boolean {
    for (i in 2..(x-1)) {
        if (x % i == 0) {
            return false
        }    
    }
    return true
}

/* The compose function takes as input
*     f - A function that takes as input a value of type T and returns a value of type T
*     g - A function that takes as input a value of type T and returns a value of type T
*  and returns as output the composition of the functions
*     f(g(x))
*/
fun<T> compose(f: (T)->T,  g:(T) -> T) : (T) -> T = { f(g(it)) }

/* Be sure to document 
   your functions
   describing inputs and outputs and what the function does
*/

// check for a null value then build a list using a for loop
fun countingNumbers(limit : Int?) : List<Int>? {
    var ans = mutableListOf<Int>()
    if (limit != null) {
        for (i in 1..limit) {
        ans.add(i)
    	}
    	return ans
    }
    else {
        return null
    }    
}

// cane reuse the counting numbers to generate 1 - n then do a filter to keep only the even numbers based
// on n mod 2
fun evenNumbers(n : Int?) : List<Int>? {
    var ans = countingNumbers(n)
    if (ans == null) {
        return null
    }
    else {
        return ans.filter {it.mod(2) == 0}
    }
}

// prime numbers I never realized that there was given code for answering if is prime
// generate my own list that starts from 2 to avoid the 1 cunundrom 
// only did to check the divisibilty up to the sqrt(n) to all numbers past that being the partners to earlier used divisors
fun primeNumbers(n : Int?) : List<Int>? {
    var ans = mutableListOf<Int>()
    if (n == null){
        return null
    }
    else {
        for (i in 2..n) {
            ans.add(i)
        }
        var sqr = sqrt(n.toDouble()).toInt()
        for (j in 2..sqr){
            ans = ans.filter { (it == j) or (it.mod(j) != 0)}.toMutableList()
        }
        return ans
    }
}

// helper function for smashing lists together in a prettier way
fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}

// check for all the null values then set up 2 modable lists to use for the rest
// the while will continue until both the lists are empty comparing the heads and setting the madable lists
// x and y up for the next iteration. If one list finishes first then it slaps the rest of the non empty list onto the results
fun<T : Comparable<T>> merge (a : List<T>?, b : List<T>?) : List<T>?{
    if (a == null && b == null) return null
            
    else if (a==null) return b
    
    else if (b==null) return a
    
    var x = a.filterNotNull().toMutableList()
    var y = b.filterNotNull().toMutableList()
    
    var result = mutableListOf<T>()
    while (x.isNotEmpty() || y.isNotEmpty()){
        if (x.size == 0) {
            result = concatenate(result, y).toMutableList()
            y.clear()
        }
    	else if (y.size == 0) {
            result = concatenate(result, x).toMutableList()
            x.clear()
        }
    	else if (x.head >= y.head){
     	   result.add(y.head)
           y = y.tail.toMutableList()
   		}
   		else if (x.head < y.head){
            result.add(x.head)
            x = x.tail.toMutableList()
        }
    }
    return result.toList()
}

// this took for ever due to the syntax being very different than expected
// using a mutable list of lists a for loop iterates through all the indexes and adds a new list onto it
// made from the slice from 0 to the current iteration
fun <T> subLists(a : List<T>?) : List<List<T>>? {
    if (a == null) {
        return null
    }
    else {
        var x = a.toMutableList()
    	var s = x.size
    	var result = mutableListOf<List<T>>()
        for (i in 0..s-1) {
       		result += (x.slice(0..i))
        }
        
    	return result.toList()

    }
}

// start by checking for null
// flatten the list then iterate through with a counter to track how many
fun <T> countElements(a : List<List<T>>?) : Int? {
    if (a == null) {
        return null
    }
    var x = a.flatten()
    var total = 0:
    for (i in x){
        total += 1
    }
    return total
}


fun listApply(::f, a : List<List<T>>) : List<T> {

}

fun composeList(a) {

}