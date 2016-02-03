
package com.test.viaplay.biocram.viaplaytest.web.model;

import com.google.gson.annotations.SerializedName;

public class RawResponseCodeModel {

    @SerializedName("httpStatus")
    private Integer httpStatus;
    @SerializedName("statusCode")
    private Integer statusCode;
    @SerializedName("code")
    private Integer code;

    /**
     * 
     * @return
     *     The httpStatus
     */
    public Integer getHttpStatus() {
        return httpStatus;
    }

    /**
     * 
     * @param httpStatus
     *     The httpStatus
     */
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * 
     * @return
     *     The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * 
     * @param statusCode
     *     The statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 
     * @return
     *     The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

}
