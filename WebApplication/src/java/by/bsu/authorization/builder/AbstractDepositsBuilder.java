package by.bsu.authorization.builder;


import by.bsu.authorization.entity.EstimatedDeposit;
import by.bsu.authorization.entity.MultiDeposit;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDepositsBuilder {
    protected Set<MultiDeposit> multiDeposits;
    protected Set<EstimatedDeposit> estimatedDeposits;

    public AbstractDepositsBuilder() {
        multiDeposits = new HashSet<MultiDeposit>();
        estimatedDeposits = new HashSet<EstimatedDeposit>();
    }

    public Set<MultiDeposit> getMultiDeposits() {
        return multiDeposits;
    }

    public Set<EstimatedDeposit> getEstimatedDeposits() {
        return estimatedDeposits;
    }

    public abstract void buildSetDeposits(String fileName);
}
