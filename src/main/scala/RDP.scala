import Token.*

/*Recursive Decent Parser for
 *    E -> A + E | A
 *    A -> 0|1|2|3|4|5|6|7|8|9
 */

object RDP {

  //  Function parse_E, and parse_S has takes the list of tokens
  //  and returns an AST (a value of type Exp) and the remaining tokens.
  //  List[Token] => (Exp, List[Token])

  def parse_E(l: List[Token]): (Exp, List[Token])=
      val (a1,l1) = parse_A(l)
       lookahead(l1)
       match
         case Tok_Sum =>  
           val n = matchToken(Tok_Sum,l1)
           val m = parse_E(n)
           (Sum(a1,m(0)),m(1))
         
         case _ =>(a1, l1)


  def parse_A(l: List[Token]): (Num, List[Token]) =
    lookahead(l)
    match
      case Token.Tok_Num(n) => (Num(n),matchToken(Token.Tok_Num(n),l))
      case _ => throw new Exception("Illegal parse_A")

  //  return the parsing result
  def parse(l: List[Token]):Exp= {
    val (a, b) = parse_E(l)
    if b.head==Token.Tok_End then
      a
    else
      throw new Exception("Illegal parse_E")
  }
  

  //return the head of a token list
  def lookahead(l: List[Token]): Token = l match
    case List() => throw new Exception("no Tokens")
    case head :: tail => head

  //match token with the head of tokeList if match return tail else throw exception
  def matchToken(tok: Token, tokList: List[Token]): List[Token] = tokList match
    case head :: tail if tok == head => tail
    case _ => throw new Exception("token is not matched")

}
