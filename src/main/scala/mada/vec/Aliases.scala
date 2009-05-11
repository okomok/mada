

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


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

    @aliasOf("vec.VectorProxy")
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
