

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toytest; package hlistparsertest


import mada.blend._


// Polymorphic function.
trait Parser {
    type Input <: List
    type parse[In <: Input] <: List
    def parse[In <: Input](in: In): parse[In]
}

case class Success()

case class SingleString(e: String) extends Parser {
    type Input = List { type head <: String }
    type parse[In <: Input] = Success :: In#tail
    override def parse[In <: Input](in: In) = {
        if (in.head.concat("") == e) {
            Cons(new Success, in.tail)
        } else {
            throw new Error
        }
    }
}

/*

case class Sequential[L <: Parser, R <: Parser { type parse[In <: Input] <: List }](l: L, r: R) extends Parser {
    type Input = L#Input
    type parse[In <: Input] = R#parse[L#parse[In]#tail]
    override def parse[In <: Input](in: In) = {
        val s = l.parse[In](in)
        r.parse(s.tail)
    }
}
*/

class HListParserTest {
    import junit.framework.Assert._

    def testTrivial: Unit = {
        val in = "hello" :: 3 :: Nil
        val out = SingleString("hello").parse(in)
        assertEquals(new Success, out.head)
    }

    def compileFail {
        val in = 3 :: "wow" :: Nil
//        val out = SingleString("hello").parse(in)
    }
}
