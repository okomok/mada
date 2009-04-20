# `Mada`

`Mada` is a set of Scala `objects`:

- `Compare`

    Strict weak ordering

- `Functions`

    Utility methods for functions

- `Iterators`

    Utility methods for Iterator type

- `Peg`

    Lightweight [PEG] parser combinators

- `Stl`

    Port of `C++` `STL`

- `Vector`

    Structurally-unmodifiable writable array



## `Compare`



## `Functions`



## `Iterators`



## `Peg`

`Peg` provides "pure" [PEG] parser combinators:

    import mada.Peg._
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
1. `Peg.from` may be needed to bust ambiguity.
1. No scanners.
1. `Peg.Rule` is used to represent recursive grammars. (`lazy val` isn't used.)
1. *Semantic Action* is passed using `{...}`. (`(...)` too can be used.)



## `Stl`



## `Vector`

`Vector` is random access sequence which supports parallel algorithms.

    import mada.Vector
    import junit.framework.Assert._

    class DocTest {
        def testTrivial: Unit = {
            val v = Vector.from(Array(0,1,2,3,4))
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



Shunsuke Sogame <<okomok@gmail.com>>



[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala/mada "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala/madatest "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
