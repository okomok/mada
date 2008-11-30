
package mada.rng


object From extends From; trait From extends Namespace
        with Predefs
        with FromInterval {
    def from[A](x: Expr[Rng[A]]): Expr[Rng[A]] = x
}
