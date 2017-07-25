package me.reske.kraken;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Dirk Reske
 */
public class AssetInfo implements Serializable {

    private String name;
    private String altName;
    private String assetClass;
    private BigDecimal decimals;
    private BigDecimal displayDecimals;

    public AssetInfo() {
    }

    public AssetInfo(String name, String altName, String assetClass, BigDecimal decimals, BigDecimal displayDecimals) {
        this.name = name;
        this.altName = altName;
        this.assetClass = assetClass;
        this.decimals = decimals;
        this.displayDecimals = displayDecimals;
    }

    public String getName() {
        return name;
    }

    /**
     * alternate name.
     *
     * @return alternate name
     */
    public String getAltName() {
        return altName;
    }

    /**
     * asset class.
     *
     * @return asset class
     */
    public String getAssetClass() {
        return assetClass;
    }

    /**
     * scaling decimal places for record keeping.
     *
     * @return decimals for record
     */
    public BigDecimal getDecimals() {
        return decimals;
    }

    /**
     * scaling decimal places for output display.
     *
     * @return decimals for display
     */
    public BigDecimal getDisplayDecimals() {
        return displayDecimals;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssetInfo{");
        sb.append("altName='").append(altName).append('\'');
        sb.append(", assetClass='").append(assetClass).append('\'');
        sb.append(", decimals=").append(decimals);
        sb.append(", displayDecimals=").append(displayDecimals);
        sb.append('}');
        return sb.toString();
    }
}
