Jack Flaherty HW9

medium-lex.txt moby.txt 20000 5 10

Lexicon: Texts/medium-lex.txt  Document: Texts/moby.txt  Class: MyTreeSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         201      94.5%
  400000         283      94.8%
  600000         495      94.7%
  800000         603      94.7%
 1000000         703      94.8%
Lexicon: Texts/medium-lex.txt  Document: Texts/moby.txt  Class: TrieSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         185      94.5%
  400000         180      94.8%
  600000         294      94.7%
  800000         382      94.7%
 1000000         432      94.8%
Lexicon: Texts/medium-lex.txt  Document: Texts/moby.txt  Class: java.util.HashSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000          49      94.5%
  400000          94      94.8%
  600000          90      94.7%
  800000         115      94.7%
 1000000         161      94.8%

medium-lex.txt random.txt 20000 5 10

Lexicon: Texts/medium-lex.txt  Document: Texts/random.txt  Class: MyTreeSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         150       0.6%
  400000         393       0.6%
  600000         485       0.6%
  800000         547       0.6%
 1000000         789       0.6%
Lexicon: Texts/medium-lex.txt  Document: Texts/random.txt  Class: TrieSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         135       0.6%
  400000         106       0.6%
  600000         195       0.6%
  800000         204       0.6%
 1000000         320       0.6%
Lexicon: Texts/medium-lex.txt  Document: Texts/random.txt  Class: java.util.HashSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         118       0.6%
  400000         184       0.6%
  600000          91       0.6%
  800000         125       0.6%
 1000000         150       0.6%

medium-lex.txt medium-lex.txt 8000 5 25

Lexicon: Texts/medium-lex.txt  Document: Texts/medium-lex.txt  Class: MyTreeSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         103     100.0%
  400000         232     100.0%
  600000         298     100.0%
  800000         399     100.0%
 1000000         482     100.0%
Lexicon: Texts/medium-lex.txt  Document: Texts/medium-lex.txt  Class: TrieSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         278     100.0%
  400000         302     100.0%
  600000         514     100.0%
  800000         594     100.0%
 1000000         863     100.0%
Lexicon: Texts/medium-lex.txt  Document: Texts/medium-lex.txt  Class: java.util.HashSet
==========================================
 words      elapsed    pct of
searched   time (ms) found words
  200000         118     100.0%
  400000         254     100.0%
  600000         131     100.0%
  800000         157     100.0%
 1000000         208     100.0%

1.) TrieSet runs best on medium vs random. TrieSet runs worst on medium vs medium. It runs best compared to
random because they have very few words in common, so it processed only very few of them. It runs worst when
compared to itself because this is the worst case (every word must be processed). TrieSet only has to check for the
nodes marked True in the trie, for medium vs random this is very few, for medium vs itself this is very many.

2.) MyTreeSet runs best on medium vs medium and worst on medium vs random. This is because the inputs in random all
have to be sorted into alphabetical order in the treemap. Since the random words are random, this takes longer. When
medium is compared to itself, they are already in alphabetical order and no sifting has to happen.

3.) First: HashSet, TrieSet, MyTreeSet
Second: HashSet, TrieSet, MyTreeSet
Third: HashSet, MyTreeSet, TrieSet

HashSet is always quickest because it does not guarantee our elements will be ordered.
For the first two, TrieSet is second because it is not worst case (medium vs medium) and it only
has to check elements marked true. It is third in the final because it is quicker for MyTreeSet to add
them all because they are already alphabetical.