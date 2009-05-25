

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.symbol


/**
 * A <code>Peg</code> to optimize the form <code>(k1 >> p1)|(k2 >> p2)|(k3 >> p3)|...</code>.
 */
trait Map[A] extends Peg[A] with scala.collection.mutable.Map[Vector[A], Peg[A]]
