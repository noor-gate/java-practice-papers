package concurrency.statements;

import concurrency.Store;
import concurrency.expressions.*;

public class WaitStmt implements Stmt {

    private Expr lhs;
    private Expr rhs;

    public WaitStmt(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public boolean isEnabled(Store store) {
        return lhs.eval(store) == rhs.eval(store);
    }

    @Override
    public void execute(Store store) {
    }
}
