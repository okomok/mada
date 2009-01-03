

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Rule {
    def make[A]: Rule[A] = new Rule[A]

    def make1[A]: (Rule[A]) = (make[A])
    def make2[A]: (Rule[A], Rule[A]) = (make[A], make[A])
    def make3[A]: (Rule[A], Rule[A], Rule[A]) = (make[A], make[A], make[A])
    def make4[A]: (Rule[A], Rule[A], Rule[A], Rule[A]) = (make[A], make[A], make[A], make[A])
    def make5[A]: (Rule[A], Rule[A], Rule[A], Rule[A], Rule[A]) = (make[A], make[A], make[A], make[A], make[A])
}


class Rule[A] extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        deref.parse(v, first, last)
    }

    private var deref: Parser[A] = null
    final def ::=(p: Parser[A]): Unit = deref = p
}
