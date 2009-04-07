

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


/**
 * Private "Optional" to work around the fact that
 * <code>null</code> and <code>None</code> are valid elements in vector.
 */
private[mada] sealed trait _Nullable[+A] {
    final def toOption: Option[A] = this match {
        case _NotNull(e) => Some(e)
        case _Null => None
    }
}

private[mada] final case class _NotNull[A](e: A) extends _Nullable[A]
private[mada] case object _Null extends _Nullable[Nothing]
