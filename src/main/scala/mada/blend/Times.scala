

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: Meta-Programming with Scala: Conditional Compilation and Times Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


object Times {

    implicit object times_0N extends Times[Meta._0N] {
        override def apply(op: Int => Unit) = { }
    }

    implicit object times_1N extends Times[Meta._1N] {
        override def apply(op: Int => Unit) = { op(0) }
    }

    implicit object times_2N extends Times[Meta._2N] {
        override def apply(op: Int => Unit) = { op(0); op(1) }
    }

    implicit object times_3N extends Times[Meta._3N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2) }
    }

    implicit object times_4N extends Times[Meta._4N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3) }
    }

    implicit object times_5N extends Times[Meta._5N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4) }
    }

    implicit object times_6N extends Times[Meta._6N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5) }
    }

    implicit object times_7N extends Times[Meta._7N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6) }
    }

    implicit object times_8N extends Times[Meta._8N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6); op(7) }
    }

    implicit object times_9N extends Times[Meta._9N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6); op(7); op(8) }
    }

    implicit object times_10N extends Times[Meta._10N] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6); op(7); op(8); op(9) }
    }

}

sealed trait Times[n <: Meta.Nat] extends Specializer {
    def apply(op: Int => Unit): Unit
}
