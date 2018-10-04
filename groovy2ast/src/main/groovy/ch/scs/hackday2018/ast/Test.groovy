package ch.scs.hackday2018.ast

import ch.scs.hackday2018.ast.transformations.Marker
import groovy.transform.CompileStatic

@CompileStatic
class Test {

  @Marker
  void run() {
    println 'Running main'
    int x = calculate(5, 4)
    println("The value is: $x")
  }

  @Marker
  int calculate(int a, int b) {
    if (b > 3) {
      return a * 4 - b
    }
    return a * 3 + b
  }

  static void main(String[] args) {
    new Test().run()
  }
}
