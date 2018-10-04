package ch.scs.hackday2018.ast

import ch.scs.hackday2018.ast.transformations.Marker

class Test {

  @Marker
  def run() {
    println 'Running main'
    int x = calculate(5, 4)
    println("The value is: $x")
  }

  @Marker
  def calculate(int a, int b) {
    return a * 3 + b
  }

  static void main(String[] args) {
    new Test().run()
  }
}
