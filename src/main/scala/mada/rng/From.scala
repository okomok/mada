
package mada.rng


object From extends From

trait From extends FromIntervalImpl {
    def from[A](x: Expr[Rng[A]]) = x
}
