import Token.*

object RDP {
  def parseE(tokens: List[Token]): (Exp, List[Token]) = {
    val (t1, r1) = parseT(tokens)
    lookahead(r1) match {
      case Tok_OR =>
        val t2 = matchToken(Tok_OR, r1)
        val (altTerm, r2) = parseE(t2)
        (Alternation(t1, altTerm), r2)
      case _ => (t1, r1)
    }
  }

  def parseA(tokens: List[Token]): (Exp, List[Token]) = {
    lookahead(tokens) match {
      case Tok_Char(c) => (C(c), matchToken(Tok_Char(c), tokens))
      case Tok_LPAREN =>
        val t1 = matchToken(Tok_LPAREN, tokens)
        val (inner, r1) = parseE(t1)
        val t2 = matchToken(Tok_RPAREN, r1)
        (inner, t2)
      case _ => throw new Exception("Illegal parse")
    }
  }

  def parseT(tokens: List[Token]): (Exp, List[Token]) = {
    val (t1, r1) = parseQ(tokens)
    lookahead(r1) match {
      case Tok_OR | Tok_RPAREN | Tok_End => (t1, r1)
      case _ =>
        val (t2, r2) = parseT(r1)
        (Concat(t1, t2), r2)
    }
  }

  def parseQ(tokens: List[Token]): (Exp, List[Token]) = {
    val (t1, r1) = parseA(tokens)
    lookahead(r1) match {
      case Token.Tok_Q =>
        val t2 = matchToken(Tok_Q, r1)
        (Optional(t1), t2)
      case _ => (t1, r1)
    }
  }

  def parse(input: String): Exp = {
    val tokens = tokenize(input)
    val (parsedExpression, remaining) = parseE(tokens)
    if remaining.head == Token.Tok_End then
      parsedExpression
    else
      throw new Exception("Illegal parse")
  }

  def lookahead(tokens: List[Token]): Token = tokens match {
    case Nil => throw new Exception("No tokens")
    case head :: _ => head
  }

  def matchToken(expectedToken: Token, tokens: List[Token]): List[Token] = tokens match {
    case head :: tail if head == expectedToken => tail
    case _ => throw new Exception("Token did not match")
  }
}
