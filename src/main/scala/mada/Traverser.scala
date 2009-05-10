

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object traverser {

    @aliasOf("Traversable")
    type Type[+A] = Traverser[A]


    val compatibles: traverser.Compatibles = Traverser

}


/**
 * Yet another Iterator
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


@companionModule
object Traverser extends traverser.Compatibles {

    @returnThis
    val compatibles: traverser.Compatibles = this

}
