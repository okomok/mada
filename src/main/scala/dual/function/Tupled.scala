

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package function


private[dual]
object Tupled2 {

    final case class Impl[f <: Function2](f: f) extends Function1 {
        type self = Impl[f]
        override  def apply[v1 <: Any](v1: v1): apply[v1] = _aux(v1.asInstanceOfProduct2)
        override type apply[v1 <: Any]                    = _aux[v1#asInstanceOfProduct2]

        private  def _aux[p <: Product2](p: p): _aux[p] = f.apply(p._1, p._2)
        private type _aux[p <: Product2]                = f#apply[p#_1, p#_2]
    }

}


private[dual]
object Tupled3 {

    final case class Impl[f <: Function3](f: f) extends Function1 {
        type self = Impl[f]
        override  def apply[v1 <: Any](v1: v1): apply[v1] = _aux(v1.asInstanceOfProduct3)
        override type apply[v1 <: Any]                    = _aux[v1#asInstanceOfProduct3]

        private  def _aux[p <: Product3](p: p): _aux[p] = f.apply(p._1, p._2, p._3)
        private type _aux[p <: Product3]                = f#apply[p#_1, p#_2, p#_3]
    }

}


private[dual]
object TupledLeft3 {

    final case class Impl[f <: Function3](f: f) extends Function2 {
        type self = Impl[f]
        override  def apply[v1 <: Any, v2 <: Any](v1: v1, v2: v2): apply[v1, v2] = _aux(v1.asInstanceOfProduct2, v2)
        override type apply[v1 <: Any, v2 <: Any]                                = _aux[v1#asInstanceOfProduct2, v2]

        private  def _aux[p <: Product2, v2 <: Any](p: p, v2: v2): _aux[p, v2] = f.apply(p._1, p._2, v2)
        private type _aux[p <: Product2, v2 <: Any]                            = f#apply[p#_1, p#_2, v2]
    }

}
