
package mada.rng


object From extends From

trait From
        extends ExprConversions
        with Conversions
        with FromIntervalImpl {
    def from[A](x: Expr[Rng[A]]) = x
}
