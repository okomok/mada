
package mada.rng


object AsRngOf extends AsRngOf

trait AsRngOf {
    class MadaRngAsRngOf[From](base: Expr[Rng[From]]) {
        def asRngOf[To] = MapExpr(base, (_: From).asInstanceOf[To])
    }
    implicit def toMadaRngAsRngOf[From](base: Expr[Rng[From]]) = new MadaRngAsRngOf(base)
}
