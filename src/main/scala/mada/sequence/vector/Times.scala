

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Times {
    def apply[A](v: Vector[A], n: Int): Vector[A] = new TimesVector(v, n)
}

private[mada] class TimesVector[A](v: Vector[A], n: Int) extends Forwarder[A] {
    override val delegate = v.permutation{ i => Div.remainder(i, v.size) }.nth(0, v.size * n).readOnly
    override def times(_n: Int) = v.times(n * _n) // times-times fusion
}
