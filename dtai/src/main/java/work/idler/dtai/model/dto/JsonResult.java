package work.idler.dtai.model.dto;

import lombok.Data;

/**
 * 返回的json数据
 *
 * @Auther:https://blog.idler.work
 * @Date:2019/5/28
 * @Description:com.iscolt.action.hotel.server.model.dto
 * @version:1.0
 */
@Data
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
