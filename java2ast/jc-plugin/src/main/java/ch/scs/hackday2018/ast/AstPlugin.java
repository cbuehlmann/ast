package ch.scs.hackday2018.ast;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;

public class AstPlugin implements Plugin {
  @Override
  public String getName() {
    return "astplugin";
  }

  @Override
  public void init(JavacTask javacTask, String... strings) {
    System.err.println("astplugin initialized");
  }
}
