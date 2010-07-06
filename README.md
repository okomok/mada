# `Mada` 1.0-SNAPSHOT



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
    import dual.nat.Literal._
    import junit.framework.Assert._

    class DocTest extends junit.framework.TestCase {
        // Define dualvalue `not2`.
        final class not2 extends dual.Function1 { // No meta-generics. Function1 isn't parameterized.
            // `self` is the dual version of `this` reference. manual setup is needed.
            override  def self = this
            override type self = not2
            // Again no meta-generics. Downcast is needed as you did in 90s.
            override  def apply[x <: dual.Any](x: x): apply[x] = x.asInstanceOfNat !== _2N
            override type apply[x <: dual.Any] = x#asInstanceOfNat# !==[_2N]
        }
        val not2 = new not2

        def testTrivial {
            // Filter heterogeneous list.
            type xs = _2N :: _3N :: _4N :: _2N :: _5N :: _6N :: _2N :: dual.Nil
            dual.meta.assertSame[_3N :: _4N :: _5N :: _6N :: dual.Nil, xs#filter[not2]]
            // Because of duality, a runtime test might be unneeded.
            val xs: xs = _2N :: _3N :: _4N :: _2N :: _5N :: _6N :: _2N :: dual.Nil
            val fs: xs#filter[not2] = xs.filter(not2)
            assertEquals(_3N :: _4N :: _5N :: _6N :: dual.Nil, fs)
        }
    }

Terminology:

* _metatype_ is a type which extends `dual.Any`. (capitalized in source code.)
* _metavalue_ is an unextendable type which extends metatype.
* _metamethod_ is a type constructor which takes metavalues.
* _dualvalue_ is an identifier which can be used as both value and metavalue.
* _dualmethod_ is an identifier which can be used as both method and metamethod.

In the Scala metaprogramming world:

* meta-`eq`(type identity equality) is infeasible.
* meta-generics doesn't work. (e.g. metatype can't be a parameter.)
* metatype is resurrected in parameter-nondependent context.



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

1. *Sequence* is represented by `>>`, because Scala doesn't have "blank" operator.
1. *And-predicate* is represented by `~`, because Scala doesn't have unary `&` operator.
1. `peg.from` may be needed to bust ambiguity.
1. No scanners.
1. `peg.Rule` is used to represent recursive grammars. (`lazy val` isn't used.)
1. *Semantic Action* is passed using `{...}`. (`(...)` too can be used.)



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

1. [Browse Source]
1. [Browse Test Source]
1. [The Scala Programming Language]
1. [MetaScala]
1. [Apocalisp]



Shunsuke Sogame <<okomok@gmail.com>>



[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala
[Apocalisp]: http://apocalisp.wordpress.com/

