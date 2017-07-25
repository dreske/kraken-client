package me.reske.kraken;


/**
 * @author Dirk Reske
 */
public class ServerTime {

    private Long unixtime;
    private String rfc1123;

    public ServerTime() {
    }

    public ServerTime(Long unixtime, String rfc1123) {
        this.unixtime = unixtime;
        this.rfc1123 = rfc1123;
    }

    /**
     * RFC 1123 time format
     *
     * @return RFC 1123 time format
     */
    public String getRfc1123() {
        return rfc1123;
    }

    /**
     * unix timestamp
     *
     * @return unix timestamp
     */
    public Long getUnixtime() {
        return unixtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerTime{");
        sb.append("unixtime=").append(unixtime);
        sb.append(", rfc1123='").append(rfc1123).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
