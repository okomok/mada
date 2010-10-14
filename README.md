# Mada 1.0.0-SNAPSHOT



`Mada` contains the following packages in Scala:

- `arm`

    Automatic resource management facility

- `dual`

    Offers method and metamethod duality.

- `peg`

    Lightweight [PEG] parser combinators

- `sequence`

    Yet another collection library



## arm

`arm` provides deterministic resource management within a block.

    import com.github.okomok.mada.arm.use
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTezt { // extends org.scalatest.junit.JUnit3Suite {
        def testTrivial: Unit = {
            for {
                source <- use(Channels.newChannel(System.in))
                dest <- use(Channels.newChannel(System.out))
            } {
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
    import junit.framework.Assert.assertEquals

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
            assertEquals("I'm a WinButton", button.paint)
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
    import junit.framework.Assert._

    class DocTest extends org.scalatest.junit.JUnit3Suite {
        val S, A, B = new Rule[Char]

        S ::= ~(A >> !"b") >> from("a").+ >> B >> !("a"|"b"|"c")
        A ::= "a" >> A.? >> "b"
        B ::= ("b" >> B.? >> "c"){ println(_) }

        def testTrivial: Unit = {
            assertTrue(S matches "abc")
            assertTrue(S matches "aabbcc")
            assertTrue(S matches "aaabbbccc")
            assertFalse(S matches "aaabbccc")
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

1. `Iterative`, iterable sequence
1. `List`, recursive sequence
1. `Vector`, random-access sequence
1. `Reactive`, reactive sequence

These construct a loosely arranged hierarchy (like Scala-2.7 collections):
`List` and `Vector` isn't subtype of `Iterative` but implicitly-convertible to it.


### Iterative

`Iterative` is yet another `Iterable`: any method is build upon the iterator abstraction.
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
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            lazy val fibs: List[Int] = 0 :: 1 :: fibs.zipBy(fibs.tail)(_ + _)
            assertEquals(832040, fibs.nth(30))
        }
    }

In fact, you could build recursive sequences using only iterator abstraction,
but number of iterator instances would be exponential-growth in a recursive sequence like above.

Note `scala.Stream` is not thread-safe, and its `foldRight`, which is the most important method, is not lazy.

`List` summary:

* Sealed. Conversion from "iterable" sequence is heavyweight.
* Backtrackable to any "tail" sequence.
* `map` and `filter` is middleweight.
* Good at recursive sequence.


### Vector

`Vector` represents (optionally writable) random access sequence, that is, "array".
It supports also parallel algorithms. Parallelization is explicit but transparent:

    import com.github.okomok.mada.sequence._
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            val v = Vector(0,1,2,3,4).parallelize
            v.map(_ + 10).seek(_ == 13) match {
                case Some(e) => assertEquals(13, e)
                case None => fail("doh")
            }

            val i = new java.util.concurrent.atomic.AtomicInteger(0)
            v.each {
                _ => i.incrementAndGet
            }
            assertEquals(5, i.get)
        }
    }

`Vector` summary:

* Random access to any subsequence.
* `map` is lightweight, but `filter` is not available. (`mutatingFilter` is provided instead.)
* Parallel algorithm support. (`fold`, `seek`, and `sort` etc.)
* Recursive sequence is infeasible.


### Reactive

`Reactive` sequence is a logical base trait for all kinds of `mada` sequences.
This is built upon (possibly) asynchronous `foreach`:

    import com.github.okomok.mada
    import mada.sequence.reactive
    import javax.swing

    class SwingTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial {
            val frame = new swing.JFrame("SwingTest")
            val label = new swing.JLabel("testTrivial")
            frame.getContentPane.add(label)
            frame.setDefaultCloseOperation(swing.JFrame.EXIT_ON_CLOSE)
            frame.pack
            frame.setVisible(true)

            // This is really built upon `foreach` only.
            val mouse = reactive.Swing.Mouse(label)
            for {
                _ <- mouse.Pressed.take(10).doing(println("pressed"))
                e <- mouse.Dragged.takeUntil(mouse.Released).then(println("released"))
            } {
                println("dragging at: " + (e.getX, e.getY))
            }

            Thread.sleep(20000)
        }
    }

`Reactive` summary:

* `foreach` may be asynchronous; a function passed to `foreach` may be called later.
* Synchronous algorithms(`isEmpty`, `head` etc) are not supplied.

References:

* [scala.react]
* [scala.Responder]
* [scala.collection.Traversable]
* [Reactive Extensions]


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

