
package mada.rng


object From extends From

trait From extends Predefs {
    def from[A](x: Expr[Rng[A]]) = x
}
