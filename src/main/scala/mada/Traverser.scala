

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import traverser._


/**
 * Yet another Iterator: backend of <code>Traversable</code>.
 * Unlike <code>Iterator</code>, this separates element-access and traversing method.
 */
@notThreadSafe
trait Traverser[+A] {

    /**
     * Is traverser pass-the-end?
     */
    def isEnd: Boolean

    /**
     * Returns the current element.
     */
    def deref: A

    /**
     * Traverses to the next position.
     */
    def increment: Unit

    @aliasOf("isEnd")
    final def unary_! = isEnd

    @aliasOf("deref")
    final def unary_~ = deref

    @aliasOf("next")
    final def ++ = increment

}


object Traverser extends Compatibles {

    @returnThis
    val Compatibles: Compatibles = this

}
