/* The Interpreter for
*     E -: C | EE | E'|'E | E'?' | '(' E ')'
*     C -: '0' | ... | 'z' | '.'
 */

sealed trait Exp {
  def matches(input: String): Boolean
}

case class C(value: Char) extends Exp {
  def matches(input: String): Boolean =
    input.length == 1 && (input.head == value || value == '.')
}

case class Concat(left: Exp, right: Exp) extends Exp {
  def matches(input: String): Boolean = {
    (0 to input.length).exists { i =>
      left.matches(input.substring(0, i)) && right.matches(input.substring(i))
    }
  }
}

case class Alternation(left: Exp, right: Exp) extends Exp {
  def matches(input: String): Boolean =
    left.matches(input) || right.matches(input)
}

case class Optional(exp: Exp) extends Exp {
  def matches(input: String): Boolean =
    exp.matches(input) || input.isEmpty
}

case object Empty extends Exp {
  def matches(input: String): Boolean = input.isEmpty
}

def matcher(pattern: String, input: String): Boolean = {
  val tokens = tokenize(pattern)
  val parsedExp = RDP.parse(pattern)
  parsedExp.matches(input)
}
