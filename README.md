
# Mada 1.0.0-SNAPSHOT

Mada is a general purpose library for Scala.
The current status is pre-alpha: being updated day by day.



## Setup Dependencies for sbt

Append this in your project definition:

    val mada = "com.github.okomok" %% "mada" % "1.0.0-SNAPSHOT"
    val okomokSnapshots = "okomok snapshots" at "http://okomok.github.com/maven-repo/snapshots"

When you use `dual` package, append this to `compileOptions`:

    ++ compileOptions("-Yrecursion", "50")

When you use `arm` or `reactive` package,
setup the continuations plugin following [this](http://code.google.com/p/simple-build-tool/wiki/CompilerPlugins "CompilerPlugins").



## scaladoc

See [mada API](http://okomok.github.com/mada/api "mada API").



## Package Overview

`Mada` contains the following packages:

- `arm`

    Automatic resource management facility

- `dual`

    Offers method and metamethod duality.

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



## dual

**This package needs `Yrecursion 50` compiler option.**

`dual` introduces a new style of metaprogramming (now implicit parameters are unneeded!):

    import com.github.okomok.mada.dual
    import dual.{map, Nat, Box}
    import dual.nat.dense.Literal._

    class AbstractFactoryTest extends org.scalatest.junit.JUnit3Suite {
        // Notice there is no common super trait.
        class WinButton {
            def paint = "I'm a WinButton"
        }
        class OSXButton {
            def paint = "I'm a OSXButton"
        }

        object WinFactory {
            def createButton = new WinButton
        }
        object OSXFactory {
            def createButton = new OSXButton
        }

        // Needs explicit boxing to make a dual object from a non-dual one.
        val factoryMap = map.sorted1(_0, Box(WinFactory)).put(_1, Box(OSXFactory))

        def createFactory[n <: Nat](n: n) = {
            val option = factoryMap.get(n)
            option.get.undual
        }

        def testTrivial {
            // Concrete types are preserved.
            val factory = createFactory(_0)
            val button = factory.createButton
            expect("I'm a WinButton")(button.paint)
        }
    }

For now, `dual` provides the dual version of `Nat`, `List`, `Map`, `Set`, `Ordering`, `Option`, `Either` and `FunctionN`.

Terminology:

* _metatype_ is a type which extends `dual.Any`. (capitalized in source code.)
* _metamethod_ is a type, or a type constructor which takes a metatype.
* _dualmethod_ is an identifier which can be used as both method and metamethod.

Thanks to dualmethods, objects move around with their own types:

    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.dense.Literal._

    class DualityTest extends org.scalatest.junit.JUnit3Suite {
        // Define 0-ary dualmethod `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. `Function1` isn't parameterized.
            type self = not2 // `self` is the dual version of `this` reference. Manual setup is needed.
            // Again no meta-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asNat.equal(_2).not
            override type apply[x <: dual.Any]                 = x#asNat#equal[_2]#not
        }
        val not2 = new not2

        def testTrivial {
            // Filter a heterogeneous list.
            val xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            val ys = _3 :: _4 :: _5 :: _6 :: dual.Nil
            dual.free.assert(xs.filter(not2).equal(ys)) // checked in compile-time thanks to the duality.
        }
    }

The computational model of Scala metaprogramming (maybe):

* Pure(no side-effects) and object-oriented.
* The arguments to a metamethod are always evaluated completely before the metamethod is applied.
* Memoization in the whole metamethod call tree. (superior than Haskell.)
* No meta-`eq` (You can't tell two types are the same or not.)
* Meta-generics doesn't work. (e.g. metatype can't be a parameter.)
* Metamethod can't be re-overridden.
* Metatype is resurrected in parameter-nondependent context.

References:

* [MetaScala]
* [Michid's Weblog]
* [Apocalisp]
* [Boost.Fusion]



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

