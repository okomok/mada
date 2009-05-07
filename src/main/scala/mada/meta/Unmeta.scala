

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


@specializer
sealed trait Unmeta[From, To] extends scala.Function0[To]


object Unmeta {

    private def make[From, To](v: To) = new Unmeta[From, To] {
        override def apply() = v
    }

    implicit val ofTrue = make[`true`, scala.Boolean](true)
    implicit val ofFalse = make[`false`, scala.Boolean](false)

    implicit val of_0I = make[_0I, scala.Int](0)
    implicit val of_1I = make[_1I, scala.Int](1)
    implicit val of_2I = make[_2I, scala.Int](2)
    implicit val of_3I = make[_3I, scala.Int](3)
    implicit val of_4I = make[_4I, scala.Int](4)
    implicit val of_5I = make[_5I, scala.Int](5)
    implicit val of_6I = make[_6I, scala.Int](6)
    implicit val of_7I = make[_7I, scala.Int](7)
    implicit val of_8I = make[_8I, scala.Int](8)
    implicit val of_9I = make[_9I, scala.Int](9)
    implicit val of_10I = make[_10I, scala.Int](10)

}
