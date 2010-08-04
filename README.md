# `Mada` 1.0.0-SNAPSHOT



`Mada` is a set of packages for Scala:

- `auto`

    Emulating `C# using` statement.

- `dual`

    Offers method and metamethod duality.

- `peg`

    Lightweight [PEG] parser combinators

- `sequence`

    Yet another collection library



## `auto`

`auto` provides deterministic resource management within a block.

    import com.github.okomok.mada.auto.use
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTezt extends junit.framework.TestCase {
        def testTrivial: Unit = {
            for {
                source <- use(Channels.newChannel(System.in))
                dest <- use(Channels.newChannel(System.out))
            } {
                channelCopy(source, dest)
            }
        }
    }

`dest.close` and `source.close` are automatically invoked in order.



## `dual`

**This package needs `Yrecursion 50` compiler option.**

`dual` introduces a new way of Scala metaprogramming (now implicit parameters are unneeded!):

    import com.github.okomok.mada.dual
    import dual.::
    import dual.nat.peano.Literal._
    import junit.framework.Assert._

    class DocTest extends org.scalatest.junit.JUnit3Suite {
        // Define dualvalue `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. `Function1` isn't parameterized.
            type self = not2 // `self` is the dual version of `this` reference. Manual setup is needed.
            // Again no meta-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asInstanceOfNat !== _2
            override type apply[x <: dual.Any] = x#asInstanceOfNat# !==[_2]
        }
        val not2 = new not2

        def testTrivial {
            // Filter a heterogeneous list.
            type xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            type fs = xs#filter[not2] // `filter` returns a view.
            dual.meta.assertSame[_3 :: _4 :: _5 :: _6 :: dual.Nil, fs#force]
            // Because of duality, a runtime test might be unneeded.
            val xs: xs = _2 :: _3 :: _4 :: _2 :: _5 :: _6 :: _2 :: dual.Nil
            val fs: xs#filter[not2] = xs.filter(not2)
            assertEquals(_3 :: _4 :: _5 :: _6 :: dual.Nil, fs)
        }
    }

For now, `dual` provides the dual version of `Nat`, `List`, `Map`, `Set`, `Ordering`, `Option`, `Either` and `FunctionN`.

Terminology:

* _metatype_ is a type which extends `dual.Any`. (capitalized in source code.)
* _metavalue_ is an unextendable type which extends metatype.
* _metamethod_ is a type constructor which takes metavalues.
* _dualvalue_ is an identifier which can be used as both value and metavalue.
* _dualmethod_ is an identifier which can be used as both method and metamethod.

The computational model of Scala metaprogramming (maybe):

* Pure(no side-effects) and object-oriented.
* The arguments to a metamethod are always evaluated completely before the metamethod is applied.
* Global memoization (The type-expression is never evaluated more than once.)
* No meta-`eq` (You can't tell two types are the same or not.)
* Meta-generics doesn't work. (e.g. metatype can't be a parameter.)
* Metamethod can't be re-overridden.
* Metatype is resurrected in parameter-nondependent context.

References:

* [MetaScala]
* [Michid's Weblog]
* [Apocalisp]
* [Boost.Fusion]



## `peg`

`peg` package provides "pure" [PEG] parser combinators:

    import com.github.okomok.mada.peg._
    import com.github.okomok.mada.peg.compatibles._
    import junit.framework.Assert._

    class DocTest {
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



## `sequence`

`sequence` provides four sequence types:

1. `Iterative`, single-pass sequence
1. `List`, recursive sequence
1. `Vector`, random-access sequence
1. `Reactive`, reactive sequence

These construct a loosely arranged hierarchy (like Scala-2.7 collections):
`List` and `Vector` isn't subtype of `Iterative` but implicitly-convertible to it.


### `Iterative`

`Iterative` is yet another `Iterable`: any method is build upon the iterator abstraction.
Unlike the scala library, `Iterative` is projection (a.k.a. view) by default.
When you need strictly-evaluated one, apply method `strict`.

`Iterative` summary:

* Conversion from "iterable" sequence is lightweight.
* Optionally backtrackable only to the whole sequence by using method `begin`.
* `filter` and `map` is lightweight.
* Recursive sequence can't be built. (See below.)


### `List`

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


### `Vector`

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


### `Reactive`

`Reactive` sequence is a coarse-grained `scala.Responder` for sequences,
or you could say a sequence-specific, thread-less and trivial `scala.Actor`.

`Reactive` summary:

* TODO.



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
