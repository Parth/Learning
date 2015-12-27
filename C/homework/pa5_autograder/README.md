# Main data structures

The main structure I chose, as advised by the pdf was the [trie](https://en.wikipedia.org/wiki/Trie). I implemented it as such:

```
struct node { 
	struct node** children;
	char value;
	int isWord;
	int prefs;
	int occs;
};
```

I considered implementing the trie by storing a `string` at every node, but eventually concluded that storing a `char` would be more memory effecient. Each node stores 26 children (for conveneince), it's statistics, and whether it's a word or not. If a node isn't a word then it's stats are simply ignored. 

# Big O
Everything I did was character by character. So there's two opperations to consider here: 

### Creating the Tree
The tree was created in constant time O(n) where n is the number of characters in the dict file.

### Searching the Tree
The tree searches is what slows this program down, because after you find a node, you are stuck traversing all the sub-nodes to increment the prefixes. I was considering inmplementing a structure that somehow stores all the prefixes of it's children, but it would complicate my program, and risk me not finishing. Since I do have to visit everynode, this becomes polynomial runtime, O(n^2) where n is the number of nodes. 

# Challenges

The biggest challenge I encountered was the time frame that was given to us. My program flew through cases 1-13, but it hit a bottleneck at case 14. It seems like the system call to `fgetc` is really expensive. Given more time I would probably try to understand how I can load this into memory in chunks, and create a cache of sorts. I looked into using `mmap`, but I didn't have time to investigate any further, as I spent most of my time analyizing my algorithm for improvements.
