

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


@provider
trait Aliases { this: Vector.type =>
    /**
     * Alias of <code>Vector</code>
     */
    type Type[A] = Vector[A]

    /**
     * Alias of <code>Vector[A] => B</code>
     */
    type Func[A, B] = Vector[A] => B

    /**
     * Alias of <code>(Vector[A], Int, Int) => B</code>
     */
    type Func3[A, B] = (Vector[A], Int, Int) => B

    /**
     * Alias of <code>Func[A, Boolean]</code>
     */
    type Pred[A] = Func[A, Boolean]

    /**
     * Alias of <code>Func3[A, Boolean]</code>
     */
    type Pred3[A] = Func3[A, Boolean]

    /**
     * Alias of <code>vec.VectorProxy</code>
     */
    type Forwarder[A] = vec.VectorProxy[A]

    @alias val Adapter = vec.Adapter
    @alias type Adapter[Z, A] = vec.Adapter[Z, A]
    @alias type VectorProxy[A] = vec.VectorProxy[A]
    @alias val Mixin = vec.Mixin
    @alias type Mixin = vec.Mixin
    @alias val Region = vec.Region
    @alias type Region[A] = vec.Region[A]
    @alias type IntFileVector = vec.IntFileVector
    @alias type LongFileVector = vec.LongFileVector
    @alias type Writer[A] = vec.Writer[A]
}
