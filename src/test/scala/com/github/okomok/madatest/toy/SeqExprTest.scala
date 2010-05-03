

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package seqexpr


import junit.framework.Assert._

/*
trait SequenceExpr[+A] extends Sequence[A] {
    def eval: Sequence[A]

    //def map[B](f: A => B): Sequence[B] = //super.map(f).eval
    //def filter(p: A => Boolean): Sequence[A] = //super.filter(p).eval
}

case class FilterExpr[A](s: SequenceExpr[A], p: A => Boolean) extends SequenceExpr[A] {
    override def eval = new FilterSequence(s.eval, p)
}
class FilterSequence[A](s: Sequence[A], p: A => Boolean) extends Sequence[A] {
    override def begin = throw new Error
}


trait SequenceIterator[+A]

trait Sequence[+A] {
    def begin: SequenceIterator[A]

    def map[B](f: A => B): Sequence[B] = throw new Error
    def filter(p: A => Boolean): Sequence[A] = throw new Error
}
*/

class SeqExprTest {

    def testTrivial: Unit = {
        //sequence.from(Array(1,2,3)).map(f) // expr
        //  .eval // sequence
    }

}
