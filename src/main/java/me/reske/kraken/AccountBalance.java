package me.reske.kraken;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class AccountBalance implements Serializable {

    private String asset;
    private BigDecimal balance;

    public AccountBalance() {
    }

    AccountBalance(String asset, BigDecimal balance) {
        this.asset = asset;
        this.balance = balance;
    }

    public String getAsset() {
        return asset;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountBalance{");
        sb.append("asset='").append(asset).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
