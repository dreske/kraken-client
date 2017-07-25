package me.reske.kraken;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class AssetPair implements Serializable {

    private String name;
    private String altname;
    private String aclassBase;
    private String base;
    private String aclassQuote;
    private String quote;
    private String lot;
    private BigDecimal pairDecimals;
    private BigDecimal lotDecimals;
    private BigDecimal lotMultiplier;

    public AssetPair() {
    }

    public AssetPair(String name, String altname, String aclassBase, String base, String aclassQuote, String quote, String lot, BigDecimal pairDecimals, BigDecimal lotDecimals, BigDecimal lotMultiplier) {
        this.name = name;
        this.altname = altname;
        this.aclassBase = aclassBase;
        this.base = base;
        this.aclassQuote = aclassQuote;
        this.quote = quote;
        this.lot = lot;
        this.pairDecimals = pairDecimals;
        this.lotDecimals = lotDecimals;
        this.lotMultiplier = lotMultiplier;
    }

    public String getName() {
        return name;
    }

    public String getAltname() {
        return altname;
    }

    public String getAclassBase() {
        return aclassBase;
    }

    public String getBase() {
        return base;
    }

    public String getAclassQuote() {
        return aclassQuote;
    }

    public String getQuote() {
        return quote;
    }

    public String getLot() {
        return lot;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssetPair{");
        sb.append("name='").append(name).append('\'');
        sb.append(", altname='").append(altname).append('\'');
        sb.append(", aclassBase='").append(aclassBase).append('\'');
        sb.append(", base='").append(base).append('\'');
        sb.append(", aclassQuote='").append(aclassQuote).append('\'');
        sb.append(", quote='").append(quote).append('\'');
        sb.append(", lot='").append(lot).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
