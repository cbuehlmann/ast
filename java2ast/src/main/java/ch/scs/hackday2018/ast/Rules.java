package ch.scs.hackday2018.ast;

public class Rules {
  public void execute() {
    System.out.println("Result " + addFive(10));
  }

  int addFive(int x) {
    if (x > 100) {
      return x + 4;
    }
    return x + 5;
  }
}
