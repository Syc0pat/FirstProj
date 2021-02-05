// Java program to demonstrate working of HashTable 

/*Hash tables are implemented where

constant time lookup and insertion is required
cryptographic applications
indexing data is requireds*/

import java.util.*; 

class HashTable { 
  public static void main(String args[]) 
  {
    Hashtable<Integer, Integer> 
      ht = new Hashtable<Integer, Integer>(); 
  
    ht.put(123, 432); 
    ht.put(12, 2345);
    ht.put(15, 5643); 
    ht.put(3, 321);

    ht.remove(12);

    System.out.println(ht); 
  } 
} 



/*

# Python program to demonstrate working of HashTable 

hashTable = [[],] * 10

def checkPrime(n):
    if n == 1 or n == 0:
        return 0

    for i in range(2, n//2):
        if n % i == 0:
            return 0

    return 1


def getPrime(n):
    if n % 2 == 0:
        n = n + 1

    while not checkPrime(n):
        n += 2

    return n


def hashFunction(key):
    capacity = getPrime(10)
    return key % capacity


def insertData(key, data):
    index = hashFunction(key)
    hashTable[index] = [key, data]

def removeData(key):
    index = hashFunction(key)
    hashTable[index] = 0

insertData(123, "apple")
insertData(432, "mango")
insertData(213, "banana")
insertData(654, "guava")

print(hashTable)

removeData(123)

print(hashTable)

*/