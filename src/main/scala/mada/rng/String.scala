
package mada.rng


import Foreach._


//  String <-> Expr[Rng[Char]]

object StringCompatible; trait StringCompatible {
    implicit def madaRng_String2ExprRng(from: String): Expr.Of[Rng[Char]] = FromStringExpr(Expr.Constant(from)).expr
}


// toRng

object StringToRng extends StringToRng; trait StringToRng extends Predefs {
    class MadaRngStringToRng(_1: Expr.Of[String]) {
        def toRng = FromStringExpr(_1).expr
    }
    implicit def toMadaRngStringToRng(_1: Expr.Of[String]): MadaRngStringToRng = new MadaRngStringToRng(_1)
}

case class FromStringExpr(_1: Expr.Of[String]) extends Expr[String, Rng[Char]] {
    override def _eval[U](x: Expr[Rng[Char], U]): U = x match {
        case Self => methodOf(_1)
        case Default => _1 match {
            case StringizeExpr(x1) => x1.eval
            case _ => delegate.eval
        }
        case _ => delegate.eval(x)
    }

    private def delegate = IndexAccessRngExpr(new StringIndexAccess(_1.eval))
}

class StringIndexAccess(val base: String) extends IndexAccess[Char] {
    override def _get(i: Long) = base.charAt(i.toInt)
    override def _size = base.length
}


// stringize

object Stringize extends Stringize; trait Stringize extends Predefs {
    class MadaRngStringize(_1: Expr.Of[Rng[Char]]) {
        def stringize = StringizeExpr(_1).expr
    }
    implicit def toMadaRngStringize(_1: Expr.Of[Rng[Char]]): MadaRngStringize = new MadaRngStringize(_1)
}

case class StringizeExpr(override val _1: Expr.Of[Rng[Char]]) extends Expr.Method[Rng[Char], String] {
    override def _default = _1 match {
        case FromStringExpr(x1) => x1.eval
        case _ => {
            val sb = new StringBuilder
            _1.foreach(sb.append(_)).eval
            sb.toString
        }
    }
}
