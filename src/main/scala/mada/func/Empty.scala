

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Returns a function doing nothing.
 * (This trait is for exposition only: use <code>Functions</code> methods instead.)
 */
trait Empty {
    val empty1: Function1[Any, Unit] = { v1 => () }
    val empty2: Function2[Any, Any, Unit] = { (v1, v2) => () }
    val empty3: Function3[Any, Any, Any, Unit] = { (v1, v2, v3) => () }
}
