

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


object Unmeta {

    private def make[From, To](v: To) = new Unmeta[From, To] {
        override def apply() = v
    }

    implicit val unmeta_true_Boolean = make[`true`, scala.Boolean](true)
    implicit val unmeta_false_Boolean = make[`false`, scala.Boolean](false)

    implicit val unmeta_0I_Int = make[_0I, scala.Int](0)
    implicit val unmeta_1I_Int = make[_1I, scala.Int](1)
    implicit val unmeta_2I_Int = make[_2I, scala.Int](2)
    implicit val unmeta_3I_Int = make[_3I, scala.Int](3)
    implicit val unmeta_4I_Int = make[_4I, scala.Int](4)
    implicit val unmeta_5I_Int = make[_5I, scala.Int](5)
    implicit val unmeta_6I_Int = make[_6I, scala.Int](6)
    implicit val unmeta_7I_Int = make[_7I, scala.Int](7)
    implicit val unmeta_8I_Int = make[_8I, scala.Int](8)
    implicit val unmeta_9I_Int = make[_9I, scala.Int](9)
    implicit val unmeta_10I_Int = make[_10I, scala.Int](10)

}

sealed trait Unmeta[From, To] extends Blend.Specializer {
    def apply(): To
}
