
package mada.rng


object From extends From; trait From extends Namespace
        with Predefs
        with FromInterval {
    def from[A](x: Expr.Of[Rng[A]]): Expr.Of[Rng[A]] = x
}
