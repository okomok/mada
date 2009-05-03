

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * Contains eligibles for <code>unmeta</code>.
 */
object Unmeta {

    def apply[From <: Object, To](v: To) = new Unmeta[From, To] {
        override def apply() = v
    }

    implicit val unmeta_true_Boolean = apply[`true`, scala.Boolean](true)
    implicit val unmeta_false_Boolean = apply[`false`, scala.Boolean](false)

    implicit val unmeta_0N_Int = apply[_0N, scala.Int](0)
    implicit val unmeta_1N_Int = apply[_1N, scala.Int](1)
    implicit val unmeta_2N_Int = apply[_2N, scala.Int](2)
    implicit val unmeta_3N_Int = apply[_3N, scala.Int](3)
    implicit val unmeta_4N_Int = apply[_4N, scala.Int](4)
    implicit val unmeta_5N_Int = apply[_5N, scala.Int](5)
    implicit val unmeta_6N_Int = apply[_6N, scala.Int](6)
    implicit val unmeta_7N_Int = apply[_7N, scala.Int](7)
    implicit val unmeta_8N_Int = apply[_8N, scala.Int](8)
    implicit val unmeta_9N_Int = apply[_9N, scala.Int](9)
    implicit val unmeta_10N_Int = apply[_10N, scala.Int](10)
    // implicit def positiveNatToInt[n <: Nat](implicit c: Unmeta[n, scala.Int]) = apply[n#increment, scala.Int](1 + c.value) // doesn't work.

}

/**
 * Customization point for type-to-value specialization
 */
sealed trait Unmeta[From <: Object, To] {
    def apply(): To
}
