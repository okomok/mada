
package mada.rng


object From extends From

trait From extends Predefs with FromIntervalImpl {
    def from[A](x: Expr[Rng[A]]) = x
}
