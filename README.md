
# Mada 1.0.0-SNAPSHOT

Mada is a general purpose library for Scala.
The current status is pre-alpha: being updated day by day.



## Setup Dependencies for sbt

Append this in your project definition:

    val mada = "com.github.okomok" %% "mada" % "1.0.0-SNAPSHOT"
    val okomokSnapshots = "okomok snapshots" at "http://okomok.github.com/maven-repo/snapshots"

When you use `arm` or `reactive` package,
setup the continuations plugin following [this](http://code.google.com/p/simple-build-tool/wiki/CompilerPlugins "CompilerPlugins").



## scaladoc

See [mada API](http://okomok.github.com/mada/api "mada API").



## Package Overview

`Mada` contains the following packages:

- `arm`

    Automatic resource management facility

- `peg`

    Lightweight [PEG] parser combinators

- `sequence`

    Yet another collection library



## arm

`arm` provides deterministic resource management within a block:

    import com.github.okomok.mada.arm.{scope, use}
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTezt { // extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            scope {
                val source = use(Channels.newChannel(System.in))
                val dest = use(Channels.newChannel(System.out))
                channelCopy(source, dest)
            }
        }

        def channelCopy(src: channels.ReadableByteChannel, dest: channels.WritableByteChannel) {
            // exercise.
        }
    }

`dest.close` and `source.close` are automatically invoked in order.

References:

* [scala-arm]
* [ARM in Java]




## peg

`peg` package provides "pure" [PEG] parser combinators:

    import com.github.okomok.mada
    import mada.peg._
    import mada.peg.Compatibles._

    class AbcTest extends org.scalatest.junit.JUnit3Suite {
        val S, A, B = new Rule[Char]

        S ::= ~(A >> !"b") >> from("a").+ >> B >> !("a"|"b"|"c")
        A ::= "a" >> A.? >> "b"
        B ::= ("b" >> B.? >> "c"){ println(_) }

        def testTrivial {
            assert(S matches "abc")
            assert(S matches "aabbcc")
            assert(S matches "aaabbbccc")
            assert(!(S matches "aaabbccc"))
        }
    }

You might notice that:

* *Sequence* is represented by `>>`, because Scala doesn't have "blank" operator.
* *And-predicate* is represented by `~`, because Scala doesn't have unary `&` operator.
* `peg.from` may be needed to bust ambiguity.
* No scanners.
* `peg.Rule` is used to represent recursive grammars. (`lazy val` isn't used.)
* *Semantic Action* is passed using `{...}`. (`(...)` too can be used.)



## sequence

`sequence` provides four sequence types:

1. `Reactive`, reactive sequence
1. `Iterative`, iterable sequence
1. `List`, recursive sequence
1. `Vector`, random-access sequence

These construct a loosely arranged hierarchy (like Scala-2.7 collections):
`List` and `Vector` is compatible to `Iterative`, which is compatible to `Reactive`.


### Reactive

`Reactive` sequence is a logical base trait for all kinds of `mada` sequences.
This is built upon (possibly) asynchronous `foreach`:

    reactive.block { * =>
        val mouse = reactive.Swing.Mouse(jl)
        for (p <- *(mouse.Pressed)) {
            println("pressed at: " + (p.getX, p.getY))
            for (d <- *(mouse.Dragged.stepTime(100).takeUntil(mouse.Released))) {
                println("dragging at: " + (d.getX, d.getY))
            }
            println("released")
        }
    }

`Reactive` summary:

* `foreach` may be asynchronous; a function passed to `foreach` may be called later.
* `block` can be used to build a sugared for-comprehension with the help of continuations plugin.
* Synchronous algorithms(`isEmpty`, `head` etc) are not supplied.

References:

* [scala.react]
* [scala.Responder]
* [scala.collection.Traversable]
* [Reactive Extensions]
* [nafg's reactive](http://github.com/nafg/reactive "nafg's reactive")


### Iterative

`Iterative` is yet another `Iterable`: any method is built upon the iterator abstraction.
Unlike the scala library, `Iterative` is projection (a.k.a. view) by default.
When you need strictly-evaluated one, apply method `strict`.

`Iterative` summary:

* Conversion from "iterable" sequence is lightweight.
* Optionally backtrackable only to the whole sequence by using method `begin`.
* `filter` and `map` is lightweight.
* Recursive sequence can't be built. (See below.)


### List

`List` emulates lazily-evaluated list like haskell's one,
which is useful to build recursive sequences:

    import com.github.okomok.mada.sequence._

    class FibonacciTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            lazy val fibs: List[Int] = 0 :: 1 :: fibs.zip(fibs.tail).map2(_ + _)
            expect(832040)(fibs.nth(30))
        }
    }

In fact, you could build recursive sequences using only iterator abstraction,
but number of iterator instances would be exponential-growth in a recursive sequence like above.

Note `scala.Stream.foldRight` is not lazy.

`List` summary:

* Sealed. Conversion from "iterable" sequence is heavyweight.
* Backtrackable to any "tail" sequence.
* `map` and `filter` is middleweight.
* Good at recursive sequence.


### Vector

`Vector` represents (optionally writable) random access sequence, that is, "array".
It supports also parallel algorithms. Parallelization is explicit but transparent:

    import com.github.okomok.mada.sequence._

    class ParTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            val v = Vector(0,1,2,3,4).par
            v.map(_ + 10).seek(_ == 13) match {
                case Some(e) => expect(13)(e)
                case None => fail("doh")
            }

            val i = new java.util.concurrent.atomic.AtomicInteger(0)
            v.each {
                _ => i.incrementAndGet
            }
            expect(5)(i.get)
        }
    }

`Vector` summary:

* Random access to any subsequence.
* `map` is lightweight, but `filter` is not available. (`mutatingFilter` is provided instead.)
* Parallel algorithm support. (`fold`, `seek`, and `sort` etc.)
* Recursive sequence is infeasible.



## Links

* [Browse Source]
* [Browse Test Source]
* [The Scala Programming Language]


Shunsuke Sogame <<okomok@gmail.com>>



[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala/ "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala/ "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala "MetaScala"
[Michid's Weblog]: http://michid.wordpress.com/ "Michid's Weblog"
[Apocalisp]: http://apocalisp.wordpress.com/ "Apocalisp"
[Boost.Fusion]: http://www.boost.org/doc/libs/release/libs/fusion/ "Boost.Fusion"
[scala.react]: http://lamp.epfl.ch/~imaier/ "scala.react"
[Reactive Extensions]: http://msdn.microsoft.com/en-us/devlabs/ee794896.aspx "Reactive Extensions"
[scala.Responder]: http://scala.sygneca.com/libs/responder "scala.Responder"
[scala.collection.Traversable]: http://www.scala-lang.org/archives/downloads/distrib/files/nightly/docs/library/scala/collection/Traversable.html "scala.collection.Traversable"
[scala-arm]: http://github.com/jsuereth/scala-arm "scala-arm"
[ARM in Java]: http://www.infoq.com/news/2010/08/arm-blocks "Automatic Resource Management in Java"

