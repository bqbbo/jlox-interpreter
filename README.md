# jlox: The Java Implementation of Lox

_The project is not complete, but below will be the final README._

## About

jlox is the Java implementation of the programming language Lox, created by Robert Nystrom along with his interpreters' guidebook, [_Crafting Interpreters_](https://craftinginterpreters.com/). jlox is a Tree-Walk Interpreter written entirely with Java 7 and built-in libraries; none of its functionality uses any external helpers. jlox is Part One of a two-part series of implementations, the other being clox, a C implementation with the same features, but more complexity.

## Additional Features

A portion of my code differs from the book; my code is linted significantly better and has more extensive commenting on a per-method basis. Variables are renamed to make them slightly more clear to read.

I included multiple extra features not included in the main body of the book; most were additional challenges, but some were unique. Below is everything that is not necessarily in the original implementation.

-   Modulo Operator: `8 % 2 = 0`
-   Exponent Operator (Inspired by Python): `2 ** 3 = 8`
-   Multi-line, nesting comments: `/* /* Comment! */ */`
-   Extra Boolean Logic: `NAND NOR XOR XNAND`

I may also implement these features in the future:

-   Bitwise Operations: `& | < > ^ ~` etc.
-   Escape Sequences: `"\n" "\t"` etc.

After the project is complete, the remaining challenges will be slowly implemented into the project until all of them are complete.

## How?

I originally discovered _Crafting Interpreters_ from a friend on a forum post, and began to read the web version where I coughed up the Scanner and some of the formal grammar for the language's [AST](https://en.wikipedia.org/wiki/Abstract_syntax_tree/). This was in my sixth-grade year, but I ended up dropping the project to focus on web-based programming.

Earlier this year, I began developing the project once again, completely from scratch and ordered the physical 600-page book.

Throughout over **50-100 hours**, mostly made of commits in private repositories or reading theory, jlox has finally been completed after almost two and a half years!

## Experience Gain

jlox was great for expanding on the following topics:

-   Polymorphism & Inheritance (Interfaces, Abstract Classes, & More)
-   Design Patterns (Visitor Pattern)
-   Java and Static Programming Syntax
-   Project Organization & Scalability Techniques
-   Data Structures & Algorithms
-   Grammars, Syntax Trees
-   LOTS of Programming Language Design Theory

Jumping into such complexity and learning along the way was definitely challenging but very rewarding.

## Contributions

If you'd like to contribute to the project, please build from source using this specific repository and not the original implementation. Please use [Sun Checks](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/sun_checks.xml/) and make sure changed files are linted before you open a PR.

## Usage

### Pre-built Releases

Building a JAR is complicated and requires external tools to compile correctly. If you have a JRE and don't want to build yourself, use a [release!](https://github.com/bqbbo/jlox-interpreter/releases/) There are releases available from the completion of Chapter 6 (Parser) through Chapter 15.

I may provide releases from commits earlier than Chapter 6 in the future.

### Building & Executing from Source

Please see [the original repository](https://craftinginterpreters.com/repo/) for a cleaner build without additional features.

jlox requires at least Java 7 to properly build; the project was developed with [OpenJDK 21 LTE](https://openjdk.org/projects/jdk/21/) but can probably compile fine on any other version above Java 7.

Open a terminal, then `cd` into a project directory, such as `bqbbo-jlox` and type `git clone https://github.com/bqbbo/jlox-interpreter .` to clone the repository to a local environment. If you have an IDE, you may want to initialize a Java Project either before cloning or right after cloning.

Next, compile all the classes into a folder to prepare to be packaged into a JAR. For example, `javac -d bin src/com/bqbbo/lox/*.java src/com/bqbbo/tool/*.java` would compile classes into `bin`.

Finally, package the JAR with `jar cfm jlox.jar manifest.txt -C bin .`, or something similar. This command should be executed from the root directory of the project to successfully bind `manifest.txt` and all the compiled class files.

To execute your jar, simply run `java -jar jlox.jar`.

## License

**This repository is subject to multiple licenses, as required by the license of Crafting Interpreters by Robert Nystrom.** Please read `https://github.com/bqbbo/jlox-interpreter/blob/main/LICENSE` for all licensing information as it is unique from all of my other repositories.

All `.java` files are under the MIT license, per the [Crafting Interpreters Repository License](https://github.com/munificent/craftinginterpreters/blob/master/LICENSE/).

All other files are under the [GNU General Public License V3](https://www.gnu.org/licenses/gpl-3.0.en.html), with potentially a few exceptions.
