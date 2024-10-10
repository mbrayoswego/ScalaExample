# Recursive Descendant Parser Example in Scala 3

Recursive descent parsing is a parsing technique that constructs a parse tree from top to bottom, and is considered one of the simplest parsing techniques used in practice. It's also known as a top-down parser.
[Wiki for RDP](https://en.wikipedia.org/wiki/Recursive_descent_parser#:~:text=In%20computer%20science%2C%20a%20recursive,the%20nonterminals%20of%20the%20grammar)

This example featuring a recursive descent parser in Scala 3. It implements a tokenizer and parser for the following grammar:
```
E -: A '+' E | A
A -: '0'|'1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9'
```


The parser, when provided with a list of typed tokens, builds an [Abstract Syntax Tree (AST)](https://medium.com/basecs/leveling-up-ones-parsing-game-with-asts-d7a6fc2400ff) featuring Sum and Num nodes. The Sum nodes are pairs of nodes which are to be added, and Num nodes have integer values. 
Both are implemented by [Algebraic Data Types](https://docs.scala-lang.org/scala3/book/types-adts-gadts.html).
