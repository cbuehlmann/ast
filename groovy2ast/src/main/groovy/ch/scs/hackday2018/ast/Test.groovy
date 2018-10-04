package ch.scs.hackday2018.ast

import ch.scs.hackday2018.ast.transformations.Marker
import groovy.transform.CompileStatic

@CompileStatic
class StaticTest {

  @Marker
  void run() {
    println 'Running static compilation'
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
    new StaticTest().run()
  }
}

class TypedTest {

  @Marker
  void runTyped() {
    println 'Running type tested Groovy'
    int x = calculate(5, 4)
    println("The value is: $x")
  }

  int calculate(int a, int b) {
    if (b > 3) {
      return a * 4 - b
    }
    return a * 3 + b
  }

}

class DynamicTest {

  @Marker
  void runDynamic() {
    println 'Running dynamically typed Groovy'
    int x = calculate(5, 4)
    println("The value is: $x")
  }

  def calculate(int a, int b) {
    if (b > 3) {
      return a * 4 - b
    }
    return a * 3 + b
  }

}