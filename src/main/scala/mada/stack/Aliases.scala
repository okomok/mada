

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


/**
 * Contains implicit conversions around <code>Stack</code>.
 */
trait Aliases { this: Stack.type =>
    /**
     * Alias of <code>Stack</code>
     */
    type Type[A] = Stack[A]
    /**
     * Alias of <code>stack.StackProxy</code>
     */
    type Forwarder[A] = stack.StackProxy[A]

    @alias type StackProxy[A] = stack.StackProxy[A]
}
