package foobar

import org.specs2.mutable._

import scala.util.parsing.combinator._

class ArithmeticParser extends JavaTokenParsers {
  def parseExpression(s: String) = parseAll(expr, s)

  def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)

  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)

  def factor: Parser[Any] = floatingPointNumber | "(" ~ expr ~ ")"
}

object Calculator extends RegexParsers {
  def number: Parser[Double] = """\d+(\.\d)?""".r ^^ {
    _.toDouble
  }

  def factor: Parser[Double] = number | "(" ~> expr <~ ")"

  def term: Parser[Double] = factor ~ rep("" ~ factor | "/" ~ factor) ^^ {
    case number ~ list => (number /: list) {
      case (x, "" ~ y) => x * y
      case (x, "/" ~ y) => x / y
    }
  }

  def expr: Parser[Double] = term ~ rep("+" ~ log(term)("Plus term") | "-" ~ log(term)("Minus term")) ^^ {
    case number ~ list => list.foldLeft(number) {
      // same as before, using alternate name for /:
      case (x, "+" ~ y) => x + y
      case (x, "-" ~ y) => x - y
    }
  }

  def apply(input: String): Double = parseAll(expr, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => scala.sys.error(failure.msg)
  }
}


class HelloWorldSpec extends Specification {
  "The parser" should {
    "recognize simple actions" in {

      //      "Control + Tab"

      println(new ArithmeticParser().parseExpression("2 * (3 + 7)"))
      "Hello world" must have size (11)

    }
  }
  "The 'Hello world' string" should {
    "contain 11 characters" in {
      "Hello world" must have size (11)
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }
}


