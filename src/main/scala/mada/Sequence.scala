

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * The marker trait of sequences to work around problems around erasure.
 */
trait Sequence[+A] {

    @conversion
    def toIterative: sequence.Iterative[A] // The "logical" base trait

    /**
     * Compares the specified object with this sequence for equality.
     * Returns true if and only if the specified object is also a sequence,
     * both sequences have the same size, and all corresponding pairs of
     * elements in the two sequences are equal.
     * You shall not override this in a purpose except optimization.
     *
     * @see Effective Java 2nd Edition - Item 8
     */
    override def equals(that: Any) = that match {
        case that: Sequence[_] => toIterative.equalsIf(that.toIterative)(function.equal)
        case _ => false
    }

    override def hashCode = {
        var r = 1
        val it = toIterative.begin
        while (it) {
            r = 31 * r + (~it).hashCode
            it.++
        }
        r
    }

    override def toString = {
        val sb = new StringBuilder
        sb.append('[')

        val it = toIterative.begin
        if (it) {
            sb.append(~it)
            it.++
        }
        while (it) {
            sb.append(", ")
            sb.append(~it)
            it.++
        }

        sb.append(']')
        sb.toString
    }

}
