# `Mada`

`Mada` is a set of Scala `objects`:

- `Compare`

    Strict weak ordering

- `Functions`

    Utility methods for functions

- `Iterators`

    Utility methods for Iterator type

- `Meta`

    Metaprogramming toys

- `Peg`

    Lightweight [PEG] parser combinators

- `Stl`

    Port of `C++` `STL`

- `Vector`

    Structurally-unmodifiable writable array



## `Compare`



## `Functions`



## `Iterators`



## `Meta`

    class DocTest {

    // run-time world

        // method
        def increment(n: Int) = n + 1

        // boolean value
        assert(true)

        // trait (cut-n-pasted from scala.Product1)
        trait Product1[+T1] { // takes type parameter.
            def _1(): T1 // abstract method
        }

        // value
        val p = new Product1[Int] { // passes type argument.
            override def _1() = 7 // implements method.// assertion
        }

        // another method
        def getAndInc(x: Product1[Int]) = x._1() + 1
        assert(getAndInc(p) == 8)

    // compile-time world

        import mada.Meta._

        // metamethod
        type mincrement[n <: Nat] = n#increment[_] // calls metamethod.

        // meta boolean value
        assert[`true`]

        // metatrait
        trait MProduct1 extends Object {
            type _T1 <: Object // takes metatype parameter. (possibly unneeded...)
            type _1[_] <: _T1 // abstract metamethod
        }

        // metavalue
        sealed trait mp extends MProduct1 {
            override type _T1 = Nat // passes metatype argument.
            override type _1[_] = _7N // implements metamethod.
        }

        // another metamethod
        type mgetAndInc[x <: MProduct1 { type _1[_] <: Nat }] = x#_1[_]#increment[_]
        assert[mgetAndInc[mp] == _8N]

        def testTrivial: Unit = ()
    }



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
1. [MetaScala]



Shunsuke Sogame <<okomok@gmail.com>>



[MIT License]: http://www.opensource.org/licenses/mit-license.php "MIT License"
[Browse Source]: http://github.com/okomok/mada/tree/master/src/main/scala/mada "Browse Source"
[Browse Test Source]: http://github.com/okomok/mada/tree/master/src/test/scala/madatest "Browse Test Source"
[The Scala Programming Language]: http://www.scala-lang.org/ "The Scala Programming Language"
[PEG]: http://en.wikipedia.org/wiki/Parsing_expression_grammar "PEG"
[MetaScala]: http://www.assembla.com/wiki/show/metascala

