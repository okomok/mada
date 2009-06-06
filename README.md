# `Mada` 0.90



`Mada` is a set of packages for Scala:

- `blend`

    Blending meta into runtime

- `meta`

    Metaprogramming toys

- `peg`

    Lightweight [PEG] parser combinators

- `sequence`

    Yet another collection library



## `blend`

`blend` contains heterogeneous-list implementation originally written in [Metascala].

    import mada.meta.literal.nat._
    import mada.blend._
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            type l = String :: Boolean :: Char :: Int :: Nil
            val l: l = "hetero" :: true :: 'L' :: 7 :: Nil
            val i: l#at[_3N] = l.at[_3N]
            assertEquals(10, i + 3)
        }
    }



## `meta`

The following example contrasts the non-meta versus meta programming in Scala:

    class DocTest {
        // boolean value
        assert(true)

        // method
        def increment(n: Int) = n + 1

        // trait (cut-n-pasted from scala.Product1)
        trait Product1[+T1] {
            def _1N: T1 // abstract method
        }

        // value
        val p = new Product1[Int] {
            override def _1N = 7 // implements method.
        }

        // another method
        def getAndInc(x: Product1[Int]) = x._1N + 1
        assert(getAndInc(p) == 8)

        // converts method to function(value).
        val inc = increment(_: Int)

        // function invocation
        assert(inc.apply(3) == 4)

        def testTrivial: Unit = ()
    }

    class metaDocTest {
        import mada.meta._

        // meta boolean value
        assert[`true`]

        // metamethod
        type increment[n <: Nat] = n#increment // metamethod invocation by `#`

        // metatrait
        trait Product1 {
            type _1N // abstract metamethod
        }

        // metavalue
        trait p extends Product1 {
            override type _1N = _7N // implements metamethod.
        }

        // another metamethod
        type getAndInc[x <: Product1 { type _1N <: Nat }] = x#_1N#increment
        assert[getAndInc[p] == _8N]

        // converts metamethod to metafunction(metavalue).
        type inc = quote1[increment, Nat, Nat]

        // metafunction invocation
        assert[inc#apply1[_3N] == _4N]

        def testTrivial: Unit = ()
    }

Scala metaprogramming seems to put several restrictions:

1. Requires -"Yrecursion 50" flag.
1. Pure: no meta variables.
1. No metamethod overloading.
1. meta-eq is infeasible.
1. meta-if may be infeasible.



## `peg`

`peg` package provides "pure" [PEG] parser combinators:

    import mada.peg._
    import mada.peg.compatibles._
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

`sequence` provides `vector` which supports parallel algorithms.

    import mada.sequence.vector
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            val v = vector.Of(0,1,2,3,4)
            v.parallel.map(_ + 10).parallel.seek(_ == 13) match {
                case Some(e) => assertEquals(13, e)
                case None => fail("doh")
            }

            val i = new java.util.concurrent.atomic.AtomicInteger(0)
            v.parallel.each {
                _ => i.incrementAndGet
            }
            assertEquals(5, i.get)
        }
    }



## Links

1. [Browse Source]
1. [Browse Test Source]
1. [The Scala Programming Language]
1. [MetaScala]



Shunsuke Sogame <<okomok@gmail.com>>



[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala/mada "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala/madatest "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala

