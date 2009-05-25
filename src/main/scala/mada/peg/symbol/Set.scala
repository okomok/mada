

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.symbol


trait Set[A] extends Peg[A] with scala.collection.mutable.Set[Vector[A]]
