

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Lefts {
    def apply[A, B](v: Vector[Either[A, B]]): Vector[A] = v.filter(_.isLeft).map(_.left.get)
}

private[mada] object Rights {
    def apply[A, B](v: Vector[Either[A, B]]): Vector[B] = v.filter(_.isRight).map(_.right.get)
}
