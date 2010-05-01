# `Mada` 1.0-SNAPSHOT



`Mada` is a set of packages for Scala:

- `auto`

    Emulating `C# using` statement.

- `blend`

    Blending meta into runtime

- `meta`

    Metaprogramming toys

- `peg`

    Lightweight [PEG] parser combinators

- `sequence`

    Yet another collection library



## `auto`

`auto` provides deterministic resource management within a block.

    import com.github.okomok.mada.auto._
    import java.nio.channels
    import java.nio.channels.Channels

    class DocTest {
        def teztTrivial: Unit = {
            for {
                source <- use(Channels.newChannel(System.in))
                dest <- use(Channels.newChannel(System.out))
            } {
                channelCopy(source, dest)
            }
        }
    }

`dest.close` and `source.close` are automatically invoked in order.



## `blend`

`blend` contains heterogeneous-list implementation originally written in [Metascala].

    import com.github.okomok.mada.meta.nat.Literal._
    import com.github.okomok.mada.blend._
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            type l = String :: Boolean :: Char :: Int :: Nil
            val l: l = "hetero" :: true :: 'L' :: 7 :: Nil
            val i: l#nth[_3N] = l.nth[_3N]
            assertEquals(10, i + 3)
        }
    }

While `scala.List` contains elements of the same type, `blend.List` can contain
elements of different types.



## `meta`

The following example contrasts the non-meta versus meta programming in Scala:

    class DocTest {
        // boolean value
        assert(true)

        // method
        def increment(n: Int) = n + 1

        // trait (cut-n-pasted from scala.Product1)
        trait Product1[+T1] {
            def _1: T1 // abstract method
        }

        // value
        val p = new Product1[Int] {
            override def _1 = 7 // implements method.
        }

        // another method
        def getAndInc(x: Product1[Int]) = x._1 + 1
        assert(getAndInc(p) == 8)

        // converts method to function(value).
        val inc = increment(_: Int)

        // function invocation
        assert(inc.apply(3) == 4)

        def testTrivial: Unit = ()
    }

    class metaDocTest {
        import com.github.okomok.mada.meta._

        // meta boolean value
        assert[`true`]

        // metamethod
        type increment[n <: Nat] = n#increment // metamethod invocation by `#`

        // metatrait
        trait Product1 {
            type _1 // abstract metamethod
        }

        // metavalue
        trait p extends Product1 {
            override type _1 = _7N // implements metamethod.
        }

        // another metamethod
        type getAndInc[x <: Product1 { type _1 <: Nat }] = x#_1#increment
        assert[getAndInc[p] == _8N]

        // converts metamethod to metafunction(metavalue).
        type inc = quote1[increment, Nat, Nat]

        // metafunction invocation
        assert[inc#apply1[_3N] == _4N]

        def testTrivial: Unit = ()
    }

Scala metaprogramming seems to put several restrictions:

1. Requires -"Yrecursion 50" flag.
1. No metamethod overloading.
1. Meta-eq is infeasible.
1. Generic metamethod can't be overridden. (As a result, meta-if is infeasible.)



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

These construct a loosely arranged hierarchy:
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



Shunsuke Sogame <<okomok@gmail.com>>



[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala

