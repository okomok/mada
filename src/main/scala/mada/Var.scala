

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Lightweight <code>Option</code>
 */
final class Var[A](@volatile private var x: A) {

    /**
     * Constructs null variable.
     */
    def this() = this(null.asInstanceOf[A])

    /**
     * Is this null?
     */
    def isNull: Boolean = null == x

    /**
     * Returns the contained reference.
     */
    def ref: A = x

    /**
     * Assigns <code>that</code>.
     */
    def assign(that: A) = { x = that }

    /**
     * Resigns.
     */
    def resign: Unit = { x = null.asInstanceOf[A] }

    /**
     * Swaps <code>self</code> and <code>that.self</code>.
     */
    def swap(that: Var[A]): Unit = {
        val tmp = ref
        assign(that.ref)
        that.assign(tmp)
    }

    @aliasOf("isNull")
    def unary_! : Boolean = isNull

    @aliasOf("ref")
    def unary_~ : A = ref

    @aliasOf("assign")
    def :=(that: A): Unit = assign(that)

    @aliasOf("resign")
    def :=(_null: Null): Unit = resign

    @conversion
    final def toBoolean: Boolean = !isNull

    @conversion
    final def toOption: Option[A] = if (isNull) None else Some(ref)

}


object Var {

    implicit def madaVarToBoolean[A](from: Var[A]): Boolean = from.toBoolean
    implicit def madaVarFromOption[A](from: Option[A]): Var[A] = fromOption(from)
    implicit def madaVarToOption[A](from: Var[A]): Option[A] = from.toOption

    @conversion
    def fromOption[A](from: Option[A]): Var[A] = {
        val v = new Var[A]
        if (!from.isEmpty) {
            v.assign(from.get)
        }
        v
    }

}
