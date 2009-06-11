

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


/**
 * The strict matcher for cons list.
 */
object StrictCons {

    def apply[A](x: A, xs: List[A]): List[A] = cons(x, xs)

    def unapply[A](xs: List[A]): Option[(A, List[A])] = xs match {
        case Nil => None
        case Cons(x, xs) => Some(x(), xs())
    }

}
