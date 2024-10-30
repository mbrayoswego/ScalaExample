/* The Scanner for
*     E -: C | EE | E'|'E | E'?' | '(' E ')'
*     C -: '0' | ... | 'z' | '.'
 */

//The enum Token defines the type of tokens
enum Token:
  case Tok_Char(value: Char)
  case Tok_OR
  case Tok_Q
  case Tok_LPAREN
  case Tok_RPAREN
  case Tok_End

// The tokenizer will generate a list of typed tokens
def tokenize(s: String): List[Token] =
  if s.isEmpty then List(Token.Tok_End)
  else
    val head = s.charAt(0)
    val tail = s.substring(1)

    head match {
      case c if c.isLetter || c.isDigit || c == '.' =>
        Token.Tok_Char(c) :: tokenize(tail)
      case '|' => Token.Tok_OR :: tokenize(tail)
      case '?' => Token.Tok_Q :: tokenize(tail)
      case '(' => Token.Tok_LPAREN :: tokenize(tail)
      case ')' => Token.Tok_RPAREN :: tokenize(tail)

      case _ => throw new Exception(s"Unexpected character: $head")
    }
