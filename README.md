# Web Page Index

## Introduction
There are many web pages on the web. We want to be able to find the pages that are relevant to a certain phrase we are looking for. Does this sound familiar? For most of us, this is a task we perform many times a day with the help of  tool. Lets see how we can build such a tool with what we have learned.

A web page contains words, which make up phrases. Because the web is a collection of web pages it is essentially a collection of words divided into pages like a book. How do we quickly find out whether a book contains a certain word? We would use the index in the back of the book. It is much faster than flipping through the book from cover to cover because the index is ordered/sorted enabling binary search. The web is a huge "book" and many pages may contain the word/phrase we are searching for. We want to find the most relevant pages first. One way to measure this relevancy is to use the frequency in which a word appears on the pages. Presumably, there is some correlation between words on a page and that page's content. And a page that frequently contains a word is probably a better match than other pages that don't. 

Additionally, the locations of a word on a web page are also importance. For instance, one word appears the same number of times on two different web pages. The word appears in the title of the first page but the word only appears in the foot note of the second page. The first page is most likely more relevant to the word we are searching for. In order to search the Web, we can create such an index of the Web. We will start with creating an index of one web page (It is not hard to imagine that we can create indexes for all pages). Given a word/phrase the index can tell us how frequently the word/phrase appears on the page and at what locations. Then, we can use the frequencies and the locations to rank and report the pages in the order of relevancy and importance. This is essentially what Google does and the algorithm is called the PageRank algorithm.

What motivates today's search companies to spend so much time, effort, and money to give you good results to your search queries? Some of the first search engines were written by computer science folks doing nifty things and sharing it out of the goodness of their hearts (and some still do), but mostly it is done for profit these days. Along with your search results, the search engine also displays a bunch of advertisements related to your query. For every ad-click that you make, they get some money. Sometimes, they get paid just to display an URL at the top of the search results! By getting high-quality/relevant search results, you are more likely to continue using a particular search engine, thus increasing their chances to profit from you.

So the million dollar question is: How do the search engines produce their list of relevant URLs? They don't share all the specifics, but we know a number of basic ideas that most of them use.

## Building Indexes
We'll be trying out 2 techniques that are used by most search engines by building an index of the words on a page. For each word we encounter on a web page, we'll keep track of what order we encountered it in (0th, 1st, 2nd, 3rd, etc.) and keep a list of all such locations for each word.

The first query technique is related to the frequency of a query word on a given page. If you've got a page that has the word "monkey" occurring repeatedly, then it is quite likely to be about monkeys. And a page that has 10% of the words on it being "monkey" is probably more relevant to a query on "monkey" than a page that only has it there 0.5%.

The second technique will be to use the set of indexes to find phrases on a page. If some is looking for "robot ninjas", then you would need to know where the words "robot" and "ninjas" appear on the page. Some search engines also support the ability to search for words NEAR each other.

1. Given the following paragraph of text, we can go through and index the order of all the words (start from 0):
```
0   1    2    3     4 5         6      7  8 9         10    11    12
How much wood could a woodchuck chuck, if a woodchuck could chuck wood?
13 14   15   16 17 18        19     20 21 22        23    24    25
As much wood as a  woodchuck would, if a  woodchuck could chuck wood.
```
2. Then, we can fill in the following table of words with a list of their indexed locations.

| Word | Count | Frequency | Locations |
|:----|:----|:----|:----|
|a          |4  |0.154  |4, 8, 17, 21|
|as         |2  |0.077  |13, 16|
|chuck      |3  |0.115  |6, 11, 24|
|could      |3  |0.115  |3, 10, 23|
|how        |1  |0.038  |0|
|if         |2  |0.077  |7, 20|
|much       |2  |0.077  |1, 14|
|wood       |4  |0.154  |2, 12, 15, 25|
|woodchuck  |4  |0.154  |5, 9, 18, 22|
|would      |1  |0.038  |19|

3. Explain how you could use the information in this table to find out if a given phrase (e.g., "chuck wood") exists within a body of text. In this example, we know the answer is "yes" because `chuck` appears once at location `11` appears again at location `12`.
<details>
<summary>Click to see a hint</summary>

```
Start with the first word of the phrase
Loop through its list of locations.
For each locations loop through the rest of the words of the phrase looking for sequential positions.
```
</details>

## Getting Started
To index a web page we need to be able to fetch a web page and extracts all the words. We will use an external library [Jsoup](https://jsoup.org/) to parse the HTML source of web pages for us (it simplifies things greatly). Jsoup is packaged as `jsoup-1.8.3.jar` (included in the starter code). To use classes in this jar file you need to compile and run classes using the `-cp` parameter.
```shell
javac -cp jsoup-1.8.3.jar:. HTMLScanner.java HTMLScannerTester.java
java -cp jsoup-1.8.3.jar:. HTMLScannerTester http://www.sbuniv.edu/
java -cp jsoup-1.8.3.jar:. HTMLScannerTester https://en.wikipedia.org/wiki/AVL_tree
```
### Getting Input
We will begin by experimenting with the `HTMLScanner` class and the associated test class `HTMLScannerTester`. `HTMLScanner` can read tokens/words one by one from a file, a web page (given its URL) or a string. `HTMLScannerTester` contains a main method designed to test the `HTMLScanner`.

`HTMLScanner` works in similar way as the normal [Scanner](https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html). You give the constructor a `String` representing the URL or file you want to read in, it then reads the file and lets you use `hasNext()` and `next()` to access the words on a page. I've also included features to iterate through the links on a page (`hasNextLink()` and `nextLink()`). The Jsoup HTML parser supports other features, such as, keywords, title, and etc., which you can read about in [Jsoup's API docs](https://jsoup.org/apidocs/overview-summary.html) if you want.

`HTMLScanner` currently only returns contiguous blocks of alpha-numeric characters -- so "sing-song95" on a page will return "sing" and then "song95".

`HTMLScannerTester` has one command-line argument, a string representing a URL. Try it out on a few URLs you are familiar with, such as "http://www.sbuniv.edu/" and "http://www.google.com/", or a filename such as "testscannerfile".
```shell
javac -cp jsoup-1.8.3.jar:. HTMLScanner.java HTMLScannerTester.java
java -cp jsoup-1.8.3.jar:. HTMLScannerTester testscannerfile
```
The content of the `testscannerfile` file is as follows:
```html
<html>
<head>
    <title>Lookee here!</title>
</head>
<body>
<h1>Hi there!</h1>

<p>What is happening?</p>

<p>
Is it important if it is
<em>t</em><b>a</b><a href="http://www.google.com/">gg</a><i>e</i><strong>d</strong>? 
</p>

</body>
</html>
```
The expected output should be:
```
the 0th token is #Hi#
the 1th token is #there#
the 2th token is #What#
the 3th token is #is#
the 4th token is #happening#
the 5th token is #Is#
the 6th token is #it#
the 7th token is #important#
the 8th token is #if#
the 9th token is #it#
the 10th token is #is#
the 11th token is #tagged#
```
Please make sure you understand why the result is correct before moving on.

## MyTreeMap
First you'll be completing an implementation of [TreeMap](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html) called `MyTreeMap`. To keep it simple `MyTreeMap` will not use generic types and will use our lab implementation of AVL tree as the internal data structure. The compiled AVL tree code is provided in the `tree.jar` file. We will use the following interface/public methods:
* `public void addElement(T element)`
* `public T find(T targetElement)`
* `public Iterator<Object> inOrderIterator()` this is newly added to support getting a list of keys from MyTreeMap.

`MyTreeMap` needs to store the mappings from unique words (keys) to their lists of locations (values) on a web page ordered by the words (keys). Each word is a key and its list of locations is the corresponding value. Recall that our AVL tree associates one object with each node and use the `compareTo` of the object to order them in the tree. To adapt AVL tree to our need, the new `Element` classes is implemented to encapsulate the mapping from a key to a value. Each `Element` object has two components: a `String` object as the key and a `LinkedList` as the value. The `Element` class implements `Comparable` and uses only the "key" component for comparing `Element` objects. Now, we can store "mappings" in the AVL tree, which takes care of the ordering and the balancing.

`MyTreeMap` is just an AVL in disguise, where the nodes in the tree contain "key-value" pairs. We place elements in the tree ordered by their key, and this key has an associated value tagging along with it. The key is of the `String` type representing each word and the value is a [`LinkedList`](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html) storing the locations (as `Integer` objects) where the word appears on the web page. 

`MyTreeMap` needs to implement the following methods:
* `public LinkedList<Integer> get(String word)`

  Return the current mapping of the given word, that is, the locations associated with the word.
  If no mapping exists, return `null`.

* `public void put(String word, Integer location)`

  If a mapping for this word already exists, the new location should be appended to the value (existing `Linkedlist` of locations) associated with the word.

  Otherwise, create a new `LinkedList` for locations, add the new location, and store the mapping from the word to the locations as a new `Element` in the underlying AVL tree. 

* `public Iterator<String> keys()`
  It is already implemented. It uses the `inOrderIterator` method in the AVL tree to retrieve a ordered list of `Element` objects, extract only the key values (words), and return the list as an `Iterator`.

Study and run `MyTreeMapTester` to make sure it works as expected:
```
javac -cp tree.jar:. MyTreeMapTester.java
java -cp tree.jar:. MyTreeMapTester
!: [5]
hello: [1, 3]
world: [2, 4]
```

## MyTreeSet
Next you will implement your own version of [`TreeSet`](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html) using a `MyTreeMap` as the backing storage.

Recall that in a Set, you only keep one copy of any item added. With a working `MyTreeMap`, implementing a `MyTreeSet` is pretty straightforward. Here are the methods you need to implement:

* `public void add(String key)`

  Add in item to the set if it isn't already in there.

* `public Iterator<String> keys()`

  Just return the inOrder iterator from `MyTreeMap`.

Study and run `MyTreeSetTester` to make sure it works as expected:
```
javac -cp tree.jar:. MyTreeSetTester.java
java -cp tree.jar:. MyTreeSetTester
!
hello
world
```

## WebPageIndex

Now that you have a working `MyTreeMap` and `MyTreeSet`, you will use it to implement a data structure that will contain the index representation of a web page. You will use a `MyTreeMap` to keep track of the indexes of each word on a page (String, LinkedList<Integer>), and a `MyTreeSet` to keep track of the links contained in the page. You should also keep track of the URL used to build the index and the total number of words on the page.

You will need to implement the following public methods:
* `public WebPageIndex(String baseUrl)`

  Create a `HTMLScanner` from baseUrl. Keep a running counter of the number of words you run into when stepping through the page using `next()` and `hasNext()`.

  When you first encounter a word, call the appropriate methods to add it to the `MyTreeMap` object. You can use the code in `MyTreeMapTester` as an example.

  Then you should step through the links using `nextLink()` and add them each into your `MyTreeSet`. Note that you don't need to re-initialize the `HTMLScanner`; `next()` and `nextLink()` make use of different `iterators()`.

  Hint: converting all words to lower case using [`String.toLowerCase()`](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#toLowerCase()) is highly recommended.

* `public String getUrl()`

  Return the URL used to create this index.

* `public int getWordCount()`

  Return the count of the total number of words on the page (not just unique words).

* `public boolean contains(String s)`

  Return true if the word "s" appeared as text anywhere on the page.

* `public int getCount(String s)`

  Return the number of times the word `s` appeared on the page.

* `public double getFrequency(String s)`

  Return the frequency the word `s` appeared on the page (i.e., the count for that word divided by the total number of words on the page).

  Be careful of integer division!

* `public List<Integer> getLocations(String s)`

  Return the List representing the locations where the word `s` appeared on the page (i.e., the value from `MyTreeMap`).

  If `s` does not appear on the page, return null.

* `public Iterator<String> words()`

  Return an iterator over all the words on the page in alphabetical order.

  Hint: your `MyTreeMap` already has something that will create this.

* `public String toString()`

  Just return the `MyTreeMap`'s `toString()` value.

  Once you have those methods working, you should go on and implement the ability to look for phrases. To do this, what you'll want to do is take a string and break it up along whitespace boundaries into individual words. Look to see if each word appears in the sequence provided.

  The suggestion is to either use `s.split("\\s+")` to turn the input into an array of Strings or a Scanner to step through s. The String method split( ) takes a regular expression and uses it to split the string into an array of substrings. The regular expression "\\s+" matches any sequence of one or more whitespace characters. (You might be tempted to use `s.split(" ")` but this runs into trouble if there are several whitespace characters in a row, which is not uncommon.) Either way, you need to find the individual words of s. For each word there, create a parallel structure of Lists using `getLocations` (I had an array). Loop through the values for the first word and see if the next has a value 1 greater, the next 2 greater, etc. You only have a phrase match if every one has an appropriate value.

  The `WebPageIndex`'s `main` method should take an argument from the command line and build a `WebPageIndex` from it. Your main method should handle all exceptions and not display stack traces to the user. Use `HTMLScannerTester` as an example. You should then display a list of all the words on the page, their frequencies, and their locations. Follow this up with a list of all the links that were on the page.

  Here are some sample outputs from my program:
  ```
  java -cp jsoup-1.8.3.jar:. WebPageIndex testscannerfile

  Frequency and index of words in testscannerfile
  happening       0.083333    [4]
  hi              0.083333    [0]
  if              0.083333    [8]
  important       0.083333    [7]
  is              0.250000    [3, 5, 10]
  it              0.166667    [6, 9]
  tagged          0.083333    [11]
  there           0.083333    [1]
  what            0.083333    [2]

  Links:
  http://www.google.com/
  ```
  
  ```
  java -cp jsoup-1.8.3.jar:. WebPageIndex http://www.cs.oberlin.edu/~rhoyle/16s-cs151/lab06/sample.html

  Frequency and index of words in http://www.cs.oberlin.edu/~rhoyle/16s-cs151/lab06/sample.html
  6               0.076923    [4, 11, 15]
  a               0.051282    [6, 23]
  be              0.025641    [17]
  book            0.025641    [26]
  children        0.025641    [24]
  cow             0.102564    [27, 30, 33, 36]
  for             0.051282    [2, 9]
  from            0.025641    [22]
  i               0.025641    [12]
  if              0.025641    [14]
  just            0.025641    [5]
  lab             0.051282    [3, 10]
  me              0.025641    [29]
  milk            0.025641    [35]
  moo             0.128205    [28, 31, 34, 37, 38]
  on              0.025641    [19]
  page            0.051282    [8, 21]
  popular         0.025641    [18]
  s               0.025641    [25]
  sample          0.025641    [0]
  short           0.025641    [7]
  text            0.025641    [1]
  that            0.025641    [20]
  will            0.025641    [16]
  wonder          0.025641    [13]
  you             0.025641    [32]

  Links:
  http://www.cs.oberlin.edu/~kuperman/csci151/lab06/index.html
  http://www.goodreads.com/book/show/926239.Cow_Moo_Me
  ```

## Reference

This assignment idea is adapted from [this assignment](https://www.cs.oberlin.edu/~rhoyle/15s-cs150/lab5/index.html) by Roberto Hoyle.
