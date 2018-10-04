package ch.scs.hackday2018.ast;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;

public class AstTaskListener implements TaskListener {

  private final AstTreeVisitor visitor;

  AstTaskListener() {
    visitor = new AstTreeVisitor();
  }

  @Override
  public void started(TaskEvent taskEvent) {
  }

  @Override
  public void finished(TaskEvent taskEvent) {
    if (taskEvent.getKind().equals(TaskEvent.Kind.ANALYZE)) {
      CompilationUnitTree compilationUnit = taskEvent.getCompilationUnit();
      visitor.scan(compilationUnit, null);
    }
  }
}
