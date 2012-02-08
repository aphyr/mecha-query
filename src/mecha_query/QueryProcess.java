package mecha_query;

import java.util.ArrayList;
import java.util.List;

public class QueryProcess {
  // Represents a single Mecha process in a query.

  public String name;
  public String fun;
  public List<Object> args;
  public ArrayList<QueryProcess> children;
  public ArrayList<QueryProcess> parents;

  public QueryProcess(String fun, String name, List<Object> args) {
    this.name = name;
    this.fun = fun;
    this.args = args;
    children = new ArrayList<QueryProcess>();
    parents = new ArrayList<QueryProcess>();
  }

  public boolean equals(Object o) {
    if (this == o) return true;

    if ( !(o instanceof QueryProcess) ) return false;

    QueryProcess p = (QueryProcess) o;
    return (name == p.name);
  }

  public QueryProcess addChild(QueryProcess child) {
    children.add(child);
    return this;
  }

  public QueryProcess addParent(QueryProcess parent) {
    parents.add(parent);
    return this;
  }

  public String toString() {
    StringBuilder r = new StringBuilder();
    r.append("#<QueryProcess ");
    r.append(name);
    r.append(" ");
    r.append(fun);
    r.append(" ");
    r.append(args.toString());
    r.append(">");
    return r.toString();
  }
}
