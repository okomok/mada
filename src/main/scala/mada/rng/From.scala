
package mada.rng


object From extends From

trait From extends Traits
        with Predefs
        with FromIntervalImpl {
    def from[A](x: Expr[Rng[A]]): Expr[Rng[A]] = x
}
