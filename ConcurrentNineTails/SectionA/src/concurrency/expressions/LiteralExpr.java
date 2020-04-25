package concurrency.expressions;

import concurrency.Store;

public class LiteralExpr implements Expr {

	private int val;

	public LiteralExpr(int val) {
		this.val = val;
	}

	@Override
	public int eval(Store store) {
		return val;
	}

}
