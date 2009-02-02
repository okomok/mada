

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import Vector.Compatibles._


/**
 * Contains utility methods operating on type <code>PrettyPrinter</code>.
 */
object PrettyPrinter {
    /**
     * Returns a <code>PrettyPrinter</code> which outputs XML.
     */
    def xml: PrettyPrinter = new XMLPrettyPrinter

    /**
     * Returns a <code>PrettyPrinter</code> which outputs nothing.
     */
    val trash: PrettyPrinter = new PrettyPrinter {
        override def write[A](p: Peg[A]) = p
        override def close = { }
    }
}

/**
 * Prints matched input information.
 */
trait PrettyPrinter {
    /**
     * Outputs matched inputs.
     */
    def write[A](p: Peg[A]): Peg[A]

    /**
     * Closes output resource.
     */
    def close: Unit

    /**
     * Alias of <code>write</code>
     */
    final def apply[A](p: Peg[A]): Peg[A] = write(p)

    /**
     * @return  <code>apply(p.named(name))</code>.
     */
    final def apply[A](name: String, p: Peg[A]): Peg[A] = apply(p.named(name))
}
