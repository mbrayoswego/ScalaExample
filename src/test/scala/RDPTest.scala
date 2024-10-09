import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.a
import org.scalatest.matchers.should.Matchers.shouldBe


class RDPTest extends AnyFunSuite:
  test("Tokenizer") {
    assert(tokenize("1") ===List(Token.Tok_Num('1'), Token.Tok_End))
    assert(tokenize("11") === List(Token.Tok_Num('1'),Token.Tok_Num('1'),Token.Tok_End))
    assert(tokenize("11+") === List(Token.Tok_Num('1'),Token.Tok_Num('1'),Token.Tok_Sum,Token.Tok_End))
    assert(tokenize("11+2") === List(Token.Tok_Num('1'),Token.Tok_Num('1'),Token.Tok_Sum,Token.Tok_Num('2'),Token.Tok_End))

  }
  test("parse") {
    assert(RDP.parse(tokenize("1")) === Num('1'))
    assert(RDP.parse(tokenize("1+2")) === Sum(Num('1'),Num('2')) )
    assert(RDP.parse(tokenize("1+2+3")) === Sum(Num('1'),Sum(Num('2'),Num('3'))))
    assert(RDP.parse(tokenize("1+2+3+4")) === Sum(Num('1'),Sum(Num('2'),Sum(Num('3'),Num('4')))))
  }
  test("eval") {
    assert(Exp.eval(RDP.parse(tokenize("1"))) === 1)
    assert(Exp.eval(RDP.parse(tokenize("1+2"))) === 3)
    assert(Exp.eval(RDP.parse(tokenize("1+2+3"))) === 6)
    assert(Exp.eval(RDP.parse(tokenize("1+2+3+4"))) === 10)
  }

  val exception1: Exception = intercept[Exception](tokenize("a"))
  exception1 shouldBe a[Exception]

  val exception2: Exception = intercept[Exception](RDP.parse(tokenize("11")))
  exception2 shouldBe a[Exception]
  val exception3: Exception = intercept[Exception](RDP.parse(tokenize("1+")))
  exception3 shouldBe a[Exception]
  val exception4: Exception = intercept[Exception](RDP.parse(tokenize("+1")))
  exception4 shouldBe a[Exception]

