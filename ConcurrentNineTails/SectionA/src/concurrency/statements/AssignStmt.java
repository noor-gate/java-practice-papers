package concurrency.statements;

import concurrency.Store;
import concurrency.expressions.Expr;

public class AssignStmt implements Stmt {

	private String lhs;
	private Expr rhs;

	public AssignStmt(String lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public boolean isEnabled(Store store) {
		return true;
	}

	@Override
	public void execute(Store store) {
		store.updateVariable(lhs, rhs.eval(store));
	}

}
