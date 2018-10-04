package ch.scs.hackday2018.ast;

import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePathScanner;

public class AstTreeVisitor extends TreePathScanner<Void, Void> {

  private int intendation = 0;

  private void log(String message) {
    for (int i = 0; i < intendation; i++) {
      System.err.print("  ");
    }
    System.err.println(message);
  }

  private Shift shift() {
    intendation++;
    return new Shift();
  }

  private class Shift implements AutoCloseable {

    @Override
    public void close() {
      intendation--;
    }
  }

  @Override
  public Void visitClass(ClassTree classTree, Void aVoid) {
    log("Class: " + classTree.getSimpleName());
    try (Shift s = shift()) {
      return super.visitClass(classTree, aVoid);
    }
  }

  @Override
  public Void visitMethod(MethodTree methodTree, Void aVoid) {
    log("Method: " + methodTree.getName());

    if (!methodTree.getParameters().isEmpty()) {
      try (Shift s = shift()) {
        log("Parameters:");

        try (Shift s2 = shift()) {
          this.scan((Iterable) methodTree.getParameters(), null);
        }

        this.scan((Tree)methodTree.getBody(), null);
      }
    }
    return null;
  }

  @Override
  public Void visitVariable(VariableTree variableTree, Void aVoid) {
    log("Variable: " + variableTree.getName() + " : " + variableTree.getType());
    try (Shift s = shift()) {
      return super.visitVariable(variableTree, aVoid);
    }
  }

  @Override
  public Void visitBlock(BlockTree blockTree, Void aVoid) {
    log("Begin block");
    try (Shift s = shift()) {
      super.visitBlock(blockTree, aVoid);
    }
    log("End block");
    return null;
  }

  @Override
  public Void visitMethodInvocation(MethodInvocationTree methodInvocationTree, Void aVoid) {
    log("Call " + methodInvocationTree.getMethodSelect());
    try (Shift s = shift()) {
      return super.visitMethodInvocation(methodInvocationTree, aVoid);
    }
  }
}
