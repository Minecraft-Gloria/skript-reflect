package com.btk5h.skriptmirror.skript.reflect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.btk5h.skriptmirror.ObjectWrapper;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class ExprUnwrapObject extends SimpleExpression<Object> {

  static {
    Skript.registerExpression(ExprUnwrapObject.class, Object.class, ExpressionType.SIMPLE,
        "unwrap %object%");
  }

  private Expression<Object> object;

  @Override
  protected @Nullable Object[] get(Event e) {
    Object object = this.object.getSingle(e);
    if(object instanceof ObjectWrapper) {
      return new Object[] { ((ObjectWrapper)object).get() };
    }
    return new Object[] { object };
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @Override
  public Class<?> getReturnType() {
    return Object.class;
  }

  @Override
  public String toString(@Nullable Event e, boolean debug) {
    return "unwrapping";
  }

  @Override
  public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
    this.object = (Expression<Object>)exprs[0];
    return true;
  }
}
