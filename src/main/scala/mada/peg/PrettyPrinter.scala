

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
        override def close = ()
        override def write[A](p: Peg[A]) = p
    }
}


/**
 * Prints matched input information.
 */
trait PrettyPrinter {
    /**
     * Closes output resource.
     */
    def close: Unit

    /**
     * Outputs matched inputs.
     */
    def write[A](p: Peg[A]): Peg[A]

    /**
     * @return  <code>write(p.named(name))</code>.
     */
    final def write[A](name: String, p: Peg[A]): Peg[A] = write(p.named(name))

    /**
     * Alias of <code>write</code>
     */
    final def apply[A](p: Peg[A]): Peg[A] = write(p)

    /**
     * Alias of <code>write</code>
     */
    final def apply[A](name: String, p: Peg[A]): Peg[A] = write(name, p)
}
