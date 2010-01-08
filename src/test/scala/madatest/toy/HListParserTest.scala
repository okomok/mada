

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toytest; package hlistparsertest



/*
import mada.blend._

/*
trait MyFunction1 {
    type Arg1
    type Result
    def apply[v1 <: Arg1](v1: v1): Result
}

class Compose1[f <: MyFunction1](f: f) {
    def apply[g <: MyFunction1 { type Result <: f#Arg1 }](g: g) = new MyFunction1 {
        type Arg1 = g.Arg1
        type Result = f#Result
        override def apply[v1 <: Arg1](v1: v1): Result = {
            val tmp = g(v1)
            f(tmp)
        }
    }
}
*/

/*
case class ComposeL[T1, R1, T2 <: List, R2 <: List](f: T1 => R1, g: T2 => R2) extends (T1 :: T2 => R1 :: R2) {
    override def apply(xs: T1 :: T2): R1 :: R2 = {
        val t: T2 = xs.tail
        f(xs.head) :: g(t)
//        Cons(f(xs.head), g(xs.tail))
    }
}
*/
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
    override def parse[In <: Input](in: In): parse[In] = {
        if (in.head.concat("") == e) {
            Cons(new Success(), in.tail)
        } else {
            throw new Error
        }
    }
}

/*
case class Sequential[L <: Parser, R <: Parser { type parse[In <: Input] <: List }](l: L, r: R) extends Parser {
    type Input = L#Input ::
    type parse[In <: Input] = R#parse[L#parse[In]#tail]
    override def parse[In <: Input](in: In) = {
        val s: L#parse[In]#tail = l.parse(in).tail
        r.parse(s)
    }
}*/

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
*/
