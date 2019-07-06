package com.iscolt.weatherforecast.model.dto;

/**
 * 返回的json数据
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/5/28
 * @Description:com.iscolt.action.hotel.server.model.dto
 * @version:1.0
 */
public class JsonResult {
    /**
     * 返回的状态码 (HttpStatus.value()).
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 开发消息.(仅在开发环境中设置)
     */
    private String devMsg;

    /**
     * 返回的数据
     */
    private Object result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDevMsg() {
        return devMsg;
    }

    public void setDevMsg(String devMsg) {
        this.devMsg = devMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public JsonResult() {
    }

    /**
     * 只返回状态码
     *
     * @param code 状态码
     */
    public JsonResult(Integer code) {
        this.code = code;
    }

    /**
     * 只返回对象
     * @param result
     */
    public JsonResult(Object result) {
        this.result = result;
    }

    /**
     * 不返回数据的构造方法
     *
     * @param code 状态码
     * @param msg  信息
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回数据的构造方法
     *
     * @param code   状态码
     * @param msg    信息
     * @param result 数据
     */
    public JsonResult(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    /**
     * 返回状态码和数据
     *
     * @param code   状态码
     * @param result 数据
     */
    public JsonResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    public JsonResult(Integer code, String msg, String devMsg, Object result) {
        this.code = code;
        this.msg = msg;
        this.devMsg = devMsg;
        this.result = result;
    }
}
