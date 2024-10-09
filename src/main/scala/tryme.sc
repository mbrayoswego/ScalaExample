import RDP.{lookahead, matchToken, parse_A, parse_E}
import Token.Tok_Sum

def parse_E(l: List[Token]): (Exp, List[Token])=
  val (a1,l1) = parse_A(l)
  lookahead(l1)
  match
    case Tok_Sum =>
      val n = matchToken(Tok_Sum,l1)
      val m = parse_E(n)
      (Sum(a1,m(0)),m(1))

    case _ =>(a1, l1)
