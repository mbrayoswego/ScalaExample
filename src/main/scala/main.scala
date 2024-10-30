@main
def main(): Unit = {
  val input = List(
    ("ab", "ab"),
    ("a.b", "axb"),
    ("a|b", "ab"),
    ("a|b", "a"),
    ("a|b", "b"),
    ("ab?", "a"),
    ("ab?", "ab"))
  for((pattern, str) <- input) {
    println(s"The pattern:        $pattern")
    println(s"The string:         $str")
    val PatTokens = tokenize(pattern)
    val tokens = tokenize(str)
    println(s"Pat Tokenizing:     $PatTokens")
    println(s"After Tokenizing:   $tokens")
    val patParseResult = RDP.parse(pattern)
    val parseResult = RDP.parse(str)
    println (s"Pat Parsing:        $patParseResult")
    println (s"After Parsing:      $parseResult")
    val value = matcher(pattern, str)
    println(s"After evaluation:   $value")
    println()
  }
}









