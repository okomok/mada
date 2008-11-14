
package mada.rng


//  String <-> Expr[Rng[Char]]

object StringConversions

trait StringConversions {
    implicit def toMadaStringRngExpr(from: => String) = FromStringExpr(Expr(from)).expr
//    implicit def fromMadaStringRngExpr(from: Expr[Rng[Char]]) = StringizeExpr(from).eval
}


// toRng

object StringToRng extends StringToRng

trait StringToRng extends Predefs {
    class MadaRngStringToRng(_1: Expr[String]) {
        def toRng = FromStringExpr(_1).expr
    }
    implicit def toMadaRngStringToRng(_1: Expr[String]) = new MadaRngStringToRng(_1)
}

case class FromStringExpr(_1: Expr[String]) extends Expr[Rng[Char]] {
    override def eval = _1 match {
        case StringizeExpr(x1) => x1.eval
        case _ => new StringRng(_1.eval)
    }
}

case class StringRng(base: String) extends IndexAccessRng[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


// stringize

object Stringize extends Stringize

trait Stringize extends Predefs {
    class MadaRngStringize(_1: Expr[Rng[Char]]) {
        def stringize = StringizeExpr(_1).expr
    }
    implicit def toMadaRngStringize(_1: Expr[Rng[Char]]) = new MadaRngStringize(_1)
}

case class StringizeExpr(_1: Expr[Rng[Char]]) extends Expr[String] {
    override def eval = _1 match {
        case FromStringExpr(x1) => x1.eval
        case _ => {
            val sb = new StringBuilder
            ForeachExpr(_1, Expr(sb.append(_: Char))).eval
            sb.toString

        }
    }
}
