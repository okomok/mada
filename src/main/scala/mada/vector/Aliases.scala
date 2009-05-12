

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


@provider
trait Aliases { this: Vector.type =>
    @aliasOf("Vector")
    type Type[A] = Vector[A]

    @aliasOf("Vector[A] => B")
    type Func[A, B] = Vector[A] => B

    @aliasOf("(Vector[A], Int, Int) => B")
    type Func3[A, B] = (Vector[A], Int, Int) => B

    @aliasOf("Func[A, Boolean]")
    type Pred[A] = Func[A, Boolean]

    @aliasOf("Func3[A, Boolean]")
    type Pred3[A] = Func3[A, Boolean]

    @aliasOf("vector.VectorProxy")
    type Forwarder[A] = vector.VectorProxy[A]

    @alias val Adapter = vector.Adapter
    @alias type Adapter[Z, A] = vector.Adapter[Z, A]
    @alias type VectorProxy[A] = vector.VectorProxy[A]
    @alias val Mixin = vector.Mixin
    @alias type Mixin = vector.Mixin
    @alias val Region = vector.Region
    @alias type Region[A] = vector.Region[A]
    @alias type IntFileVector = vector.IntFileVector
    @alias type LongFileVector = vector.LongFileVector
    @alias type Writer[A] = vector.Writer[A]
}
