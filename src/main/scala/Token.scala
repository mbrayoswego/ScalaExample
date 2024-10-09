/* The Scanner for
 *    E -> A + E | A
 *    A -> 0|1|2|3|4|5|6|7|8|9 
 */

//The enum Token defines the type of tokens
enum Token:
  case Tok_Num(value: Char)
  case Tok_Sum
  case Tok_End

// The tokenizer will generate a list of typed tokens
def tokenize(s: String): List[Token] =
  if s.isEmpty then List(Token.Tok_End)
  else
    val head = s.charAt(0)
    val tail = s.substring(1)
    if head.isDigit then Token.Tok_Num(head) :: tokenize(tail)
    else if head == '+' then Token.Tok_Sum :: tokenize(tail)
    else throw new Exception("Exception thrown from tokenize")

