/*
    Interpreter for
 *    E -> A + E | A
 *    A -> 0|1|2|3|4|5|6|7|8|9
 */


// The AST featuring Sum and Num nodes
//The Sum nodes are pairs of nodes which are to be added, and Num nodes have integer values.
trait Exp
case class Num(n: Int) extends Exp
case class Sum(n1: Num, e2: Exp) extends Exp

object Exp{

def eval(e: Exp): Int = e match
  case Num(n) => n-'0'
  case Sum(n, e2) => eval(n) + eval(e2)

def show(e: Exp): String = e match
  case Num(n) => (n-'0')+""
  case Sum(n, e2) => show(n) + "+" + show(e2)
}