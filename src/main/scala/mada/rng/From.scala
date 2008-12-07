
package mada.rng


object From extends From; trait From extends Namespace
        with Predefs
        with FromInterval {
    def from[A](x: ExprV2.Of[Rng[A]]): ExprV2.Of[Rng[A]] = x
}
