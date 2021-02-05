/**
 * A generic bloom filter implementation that supports any hash function(s)
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.datastructures.bloomfilter;

public class BloomFilter {

  // The number of bitsets. This should be proportional
  // to the number of hash functions for this bloom filter
  private final int N_SETS;

  // A 2D array containing the bitsets
  private final long[][] bitsets;

  // Tracks the size of the bitsets in this bloom filter
  private final int[] SET_SIZES;

  // Doing 'n & 0x7F' is the same as modding by 64, but faster
  private static final long MOD64_MASK = 0x7F;

  // Doing 'n >> 6' is the same as dividing by 64, but faster
  private static final long DIV64_SHIFT = 6;

  // Create a bloom filter with a various bitsets of different sizes
  public BloomFilter(int[] bitSetSizes) {
    N_SETS = bitSetSizes.length;
    SET_SIZES = bitSetSizes.clone();
    bitsets = new long[N_SETS][];
    for (int i = 0; i < N_SETS; i++) {
      bitsets[i] = new long[SET_SIZES[i]];
    }
  }

  // Add a hash value to one of the bitsets in the bloom filter
  public void add(int setIndex, long hash) {
    hash = hash % SET_SIZES[setIndex];
    int block = (int) (hash >> DIV64_SHIFT);
    bitsets[setIndex][block] |= (1L << (hash & MOD64_MASK));
  }

  // Adds a group of related hash values to the bloom filter.
  // These hash values should be the hash values that were applied
  // to all the various hash functions on the same key.
  public void add(long[] hashes) {
    for (int i = 0; i < N_SETS; i++) {
      add(i, hashes[i]);
    }
  }

  // Checks if a particular key is found within the bloom filter
  public boolean contains(long[] hashes) {
    for (int i = 0; i < hashes.length; i++) {
      int block = (int) (hashes[i] >> DIV64_SHIFT);
      long MASK = 1L << (hashes[i] & MOD64_MASK);
      if ((bitsets[i][block] & MASK) != MASK) return false;
    }
    return true;
  }

  @Override
  public String toString() {

    int maxSz = 0;
    for (int setSize : SET_SIZES) maxSz = Math.max(maxSz, setSize);

    char[][] matrix = new char[N_SETS][maxSz];
    for (char[] ar : matrix) java.util.Arrays.fill(ar, '0');

    for (int k = 0; k < N_SETS; k++) {
      for (int i = 0; i < SET_SIZES[k]; i++) {
        int block = i / 64;
        int offset = i % 64;
        long mask = 1L << offset;
        if ((bitsets[k][block] & mask) == mask) {
          matrix[k][i] = '1';
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (char[] row : matrix) sb.append(new String(row) + "\n");
    return sb.toString();
  }
}




/*

----from StackOverflow-----

"From Wikipedia:"

Bloom filters have a strong space advantage over other data structures for representing sets, such as self-balancing binary search trees, tries, hash tables, or simple arrays or linked lists of the entries. Most of these require storing at least the data items themselves, which can require anywhere from a small number of bits, for small integers, to an arbitrary number of bits, such as for strings (tries are an exception, since they can share storage between elements with equal prefixes). Linked structures incur an additional linear space overhead for pointers. A Bloom filter with 1% error and an optimal value of k, on the other hand, requires only about 9.6 bits per element — regardless of the size of the elements. This advantage comes partly from its compactness, inherited from arrays, and partly from its probabilistic nature. If a 1% false positive rate seems too high, each time we add about 4.8 bits per element we decrease it by ten times.

Pretty clear to me.

A bloom filter doesn't store the elements themselves, this is the crucial point. You don't use a bloom filter to test if an element is present, you use it to test whether it's certainly not present, since it guarantees no false negatives. This lets you not do extra work for elements that don't exist in a set (such as disk IO to look them up).

And all in significantly less space than something like a hash table (which is likely going to be partially on disk for large data sets). Though you may use a bloom filter in conjunction with a structure like a hash table, once you're certain the element has a chance of being present.

So an example usage pattern might be:

You've got a lot of data, on disk -- you decide on what error bound you want (e.g. 1%), that prescribes the value of m. Then the optimal k is determined (from the formula given in the article). You populate your filter from this disk-bound data once.

Now you have the filter in RAM. When you need to process some element, you query your filter to see if it stands a chance of existing in your data set. If it doesn't, no extra work is done. No disk reads, etc. (Which you would have to do if it were a hash or tree, etc).

Otherwise, if the filter says "Yes, it's in there", there's a 1% chance that it's wrong, so you do the necessary work to find out. 99% of the time, it really will be there, so the work was not for naught.

﻿
==============================================================================================


------Use Case Example--------

Lets say I work for Google, in the Chrome team, and I want to add a feature to the browser which notifies the user if the url he has entered is a malicious URL. So I have a dataset of about 1 million malicious URLs, the size of this file being around 25MB. Since the size is quite big, (big in comparison to the size of the browser itself), I store this data on a remote server.

Case 1 : I use a hash function with a hash table. I decide on an efficient hashing function, and run all the 1 million urls through the hashing function to get hash keys. I then make a hash table (an array), where the hash key would give me the index to place that URL. So now once I have hashed and filled the hashing table, I check its size. I have stored all 1 million URLs in the hash table along with their keys. So the size is at least 25 MB. This hash table, due to its size will be stored on a remote server. When a user comes along and enters a URL in the address bar, I need to check if it malicious. Thus I run the URL through the hash function (the browser itself can do this) and I get a hash key for that URL. I now have to make a request to my remote server with that hash key, to check the if the particular URL in my hash table with that particular key, is the same as what the user has entered. If yes then it is malicious and if no then it is not malicious. Thus every time the user enters a URL, a request to the remote server has to be made to check if it is a malicious URL. This would take a lot of time and thus make my browser slow.

Case 2 : I use a bloom filter. The entire list of 1 million URLs are run through the bloom filter using multiple hash functions and the respective positions are marked as 1, in a huge array of 0s. Let's say we want a false positive rate of 1%, using a bloom filter calculator (http://hur.st/bloomfilter?n=1000000&p=0.01) , we get the size of the bloom filter required as only 1.13 MB. This small size is expected as, even though the size of the array is huge, we are only storing 1s or 0s and not the URLs as in case of the hash table.This array can be treated as a bit array. That is, since we have only two values 1 and 0, we can set individual bits instead of bytes. This would reduce the space taken by 8 times. This 1.13 MB bloom filter, due to its small size, can be stored in the web browser itself !! Thus when a user comes along and enters a URL, we simply apply the required hash functions (in the browser itself), and check all the positions in the bloom filter (which is stored in the browser). A value of 0 in any of the positions tells us that this URL is DEFINITELY NOT in the list of malicious URLs and the user can proceed freely. Thus we did not make a call to the server and hence saved time. A value of 1 tells us that the URL MIGHT be in the list of malicious URLs. In these cases we make a call to the remote server and over there we can use some other hash function with some hash table as in the first case to retrieve and check if the URL is actually present. Since most of the times, a URL is not likely to be a malicious one, the small bloom filter in the browser figures that out and hence saves time by avoiding calls to the remote server. Only in some cases, if the bloom filter tells us that the URL MIGHT be malicious, only in those cases we make a call to the server. That 'MIGHT' is 99% right.

So by using a small bloom filter in the browser, we have saved a lot of time as we do not need to make server calls for every URL entered.

We can see that hash table with a single hash function is used for a different purpose altogether than a bloom filter.

*/