package me.reske.kraken;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class TradeBalance implements Serializable {

    private BigDecimal equivalentBalance;
    private BigDecimal tradeBalance;
    private BigDecimal marginAmount;
    private BigDecimal unrealized;
    private BigDecimal costBasis;
    private BigDecimal currentValuation;
    private BigDecimal equity;
    private BigDecimal freeMargin;
    private BigDecimal marginLevel;

    public TradeBalance() {
    }

    TradeBalance(JSONObject jsonObject) {
        equivalentBalance = jsonObject.getBigDecimal("eb");
        tradeBalance = jsonObject.getBigDecimal("tb");
        marginAmount = jsonObject.getBigDecimal("m");
        unrealized = jsonObject.getBigDecimal("n");
        costBasis = jsonObject.getBigDecimal("c");
        currentValuation = jsonObject.getBigDecimal("v");
        equity = jsonObject.getBigDecimal("e");
        freeMargin = jsonObject.getBigDecimal("mf");
        marginLevel = jsonObject.optBigDecimal("ml", null);
    }

    /**
     * equivalent balance (combined balance of all currencies).
     *
     * @return
     */
    public BigDecimal getEquivalentBalance() {
        return equivalentBalance;
    }

    /**
     * trade balance (combined balance of all equity currencies).
     *
     * @return
     */
    public BigDecimal getTradeBalance() {
        return tradeBalance;
    }

    /**
     * margin amount of open positions.
     *
     * @return
     */
    public BigDecimal getMarginAmount() {
        return marginAmount;
    }

    /**
     * unrealized net profit/loss of open positions.
     *
     * @return
     */
    public BigDecimal getUnrealized() {
        return unrealized;
    }

    /**
     * cost basis of open positions.
     *
     * @return
     */
    public BigDecimal getCostBasis() {
        return costBasis;
    }

    /**
     * current floating valuation of open positions.
     *
     * @return
     */
    public BigDecimal getCurrentValuation() {
        return currentValuation;
    }

    /**
     * equity = trade balance + unrealized net profit/loss.
     *
     * @return
     */
    public BigDecimal getEquity() {
        return equity;
    }

    /**
     * free margin = equity - initial margin (maximum margin available to open new positions).
     *
     * @return
     */
    public BigDecimal getFreeMargin() {
        return freeMargin;
    }

    /**
     * margin level = (equity / initial margin) * 100.
     *
     * @return
     */
    public BigDecimal getMarginLevel() {
        return marginLevel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TradeBalance{");
        sb.append("equivalentBalance=").append(equivalentBalance);
        sb.append(", tradeBalance=").append(tradeBalance);
        sb.append(", marginAmount=").append(marginAmount);
        sb.append(", unrealized=").append(unrealized);
        sb.append(", costBasis=").append(costBasis);
        sb.append(", currentValuation=").append(currentValuation);
        sb.append(", equity=").append(equity);
        sb.append(", freeMargin=").append(freeMargin);
        sb.append(", marginLevel=").append(marginLevel);
        sb.append('}');
        return sb.toString();
    }
}
