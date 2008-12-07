
package mada.rng


import Foreach._


//  String <-> Expr[Rng[Char]]

object StringCompatible; trait StringCompatible {
    implicit def madaRng_String2ExprRng(from: String): ExprV2.Of[Rng[Char]] = FromStringExpr(ExprV2.Constant(from)).expr
}


// toRng

object StringToRng extends StringToRng; trait StringToRng extends Predefs {
    class MadaRngStringToRng(_1: ExprV2.Of[String]) {
        def toRng = FromStringExpr(_1).expr
    }
    implicit def toMadaRngStringToRng(_1: ExprV2.Of[String]): MadaRngStringToRng = new MadaRngStringToRng(_1)
}

case class FromStringExpr(_1: ExprV2.Of[String]) extends ExprV2[String, Rng[Char]] {
    override def _eval[U](x: ExprV2[Rng[Char], U]): U = x match {
        case Self => _1.eval(this)
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
    class MadaRngStringize(_1: ExprV2.Of[Rng[Char]]) {
        def stringize = StringizeExpr(_1).expr
    }
    implicit def toMadaRngStringize(_1: ExprV2.Of[Rng[Char]]): MadaRngStringize = new MadaRngStringize(_1)
}

case class StringizeExpr(override val _1: ExprV2.Of[Rng[Char]]) extends ExprV2.Method[Rng[Char], String] {
    override def _default = _1 match {
        case FromStringExpr(x1) => x1.eval
        case _ => {
            val sb = new StringBuilder
            _1.foreach(sb.append(_)).eval
            sb.toString
        }
    }
}
