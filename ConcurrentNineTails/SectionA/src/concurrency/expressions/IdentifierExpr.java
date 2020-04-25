package concurrency.expressions;

import concurrency.Store;

public class IdentifierExpr implements Expr {

	private String name;

	public IdentifierExpr(String name) {
		this.name = name;
	}

	@Override
	public int eval(Store store) {
		return store.lookupVariable(name);
	}

}
