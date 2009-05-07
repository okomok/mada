

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: Meta-Programming with Scala: Conditional Compilation and Times Unrolling
//      at http://michid.wordpress.com/2008/10/29/meta-programming-with-scala-conditional-compilation-and-loop-unrolling/


@specializer
sealed trait Times[n <: Meta.Int] extends ((Int => Unit) => Unit)


object Times {

    implicit object of_0I extends Times[Meta._0I] {
        override def apply(op: Int => Unit) = { }
    }

    implicit object of_1I extends Times[Meta._1I] {
        override def apply(op: Int => Unit) = { op(0) }
    }

    implicit object of_2I extends Times[Meta._2I] {
        override def apply(op: Int => Unit) = { op(0); op(1) }
    }

    implicit object of_3I extends Times[Meta._3I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2) }
    }

    implicit object of_4I extends Times[Meta._4I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3) }
    }

    implicit object of_5I extends Times[Meta._5I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4) }
    }

    implicit object of_6I extends Times[Meta._6I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5) }
    }

    implicit object of_7I extends Times[Meta._7I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6) }
    }

    implicit object of_8I extends Times[Meta._8I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6); op(7) }
    }

    implicit object of_9I extends Times[Meta._9I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6); op(7); op(8) }
    }

    implicit object of_10I extends Times[Meta._10I] {
        override def apply(op: Int => Unit) = { op(0); op(1); op(2); op(3); op(4); op(5); op(6); op(7); op(8); op(9) }
    }

}
