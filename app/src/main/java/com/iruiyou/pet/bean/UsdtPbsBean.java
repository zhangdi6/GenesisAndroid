package com.iruiyou.pet.bean;

/**
 * 作者：sgf
 * 获取pbs行情
 */
public class UsdtPbsBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"usdtPBS":{"id":44,"last":"0.01666","lowestAsk":"0.01673","highestBid":"0.01655","percentChange":"-0.89","quoteVolume":"6130522.22","isFrozen":0,"high24hr":"0.01723","low24hr":"0.01638"},"usdCNY":{"vol":"35947516.5289","last":"6.8977","sell":"6.8985","buy":"6.8977","high":"6.9099","low":"6.8569"}}
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private String message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private Object token;
    private Object rcToken;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(Object csrfToken) {
        this.csrfToken = csrfToken;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getRcToken() {
        return rcToken;
    }

    public void setRcToken(Object rcToken) {
        this.rcToken = rcToken;
    }

    public static class DataBean {
        /**
         * usdtPBS : {"id":44,"last":"0.01666","lowestAsk":"0.01673","highestBid":"0.01655","percentChange":"-0.89","quoteVolume":"6130522.22","isFrozen":0,"high24hr":"0.01723","low24hr":"0.01638"}
         * usdCNY : {"vol":"35947516.5289","last":"6.8977","sell":"6.8985","buy":"6.8977","high":"6.9099","low":"6.8569"}
         */

        private UsdtPBSBean usdtPBS;
        private UsdCNYBean usdCNY;

        public UsdtPBSBean getUsdtPBS() {
            return usdtPBS;
        }

        public void setUsdtPBS(UsdtPBSBean usdtPBS) {
            this.usdtPBS = usdtPBS;
        }

        public UsdCNYBean getUsdCNY() {
            return usdCNY;
        }

        public void setUsdCNY(UsdCNYBean usdCNY) {
            this.usdCNY = usdCNY;
        }

        public static class UsdtPBSBean {
            /**
             * id : 44
             * last : 0.01666
             * lowestAsk : 0.01673
             * highestBid : 0.01655
             * percentChange : -0.89
             * quoteVolume : 6130522.22
             * isFrozen : 0
             * high24hr : 0.01723
             * low24hr : 0.01638
             */

            private int id;
            private String last;
            private String lowestAsk;
            private String highestBid;
            private String percentChange;
            private String quoteVolume;
            private int isFrozen;
            private String high24hr;
            private String low24hr;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLast() {
                return last;
            }

            public void setLast(String last) {
                this.last = last;
            }

            public String getLowestAsk() {
                return lowestAsk;
            }

            public void setLowestAsk(String lowestAsk) {
                this.lowestAsk = lowestAsk;
            }

            public String getHighestBid() {
                return highestBid;
            }

            public void setHighestBid(String highestBid) {
                this.highestBid = highestBid;
            }

            public String getPercentChange() {
                return percentChange;
            }

            public void setPercentChange(String percentChange) {
                this.percentChange = percentChange;
            }

            public String getQuoteVolume() {
                return quoteVolume;
            }

            public void setQuoteVolume(String quoteVolume) {
                this.quoteVolume = quoteVolume;
            }

            public int getIsFrozen() {
                return isFrozen;
            }

            public void setIsFrozen(int isFrozen) {
                this.isFrozen = isFrozen;
            }

            public String getHigh24hr() {
                return high24hr;
            }

            public void setHigh24hr(String high24hr) {
                this.high24hr = high24hr;
            }

            public String getLow24hr() {
                return low24hr;
            }

            public void setLow24hr(String low24hr) {
                this.low24hr = low24hr;
            }
        }

        public static class UsdCNYBean {
            /**
             * vol : 35947516.5289
             * last : 6.8977
             * sell : 6.8985
             * buy : 6.8977
             * high : 6.9099
             * low : 6.8569
             */

            private String vol;
            private String last;
            private String sell;
            private String buy;
            private String high;
            private String low;

            public String getVol() {
                return vol;
            }

            public void setVol(String vol) {
                this.vol = vol;
            }

            public String getLast() {
                return last;
            }

            public void setLast(String last) {
                this.last = last;
            }

            public String getSell() {
                return sell;
            }

            public void setSell(String sell) {
                this.sell = sell;
            }

            public String getBuy() {
                return buy;
            }

            public void setBuy(String buy) {
                this.buy = buy;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }
        }
    }
}
