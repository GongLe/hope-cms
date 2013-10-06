package org.hope.runner.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Gongle
 * @Description: 通用json对象
 */
public class JsonResult implements Serializable {

    public JsonResult() {
        super();

    }

    /**
     * 操作状态*
     */
    private boolean status = false;

    /**
     * 操作信息*
     */
    private String msg;


    /**
     * 新增操作*
     */
    private boolean isNewRecord = false;


    /**
     * 额外附带信息*
     */
    private Map data = new HashMap();


    /**
     * 操作完成，返回状态信息
     *
     * @param message
     * @return
     */
    public static JsonResult success(String message) {
        JsonResult result = new JsonResult();
        result.setStatus(true);
        result.setMsg(message);
        return result;
    }

    /**
     * 获得失败的操作结果
     *
     * @param message
     * @return
     */
    public static JsonResult failure(String message) {
        JsonResult result = new JsonResult();
        result.setStatus(false);
        result.setMsg(message);
        return result;
    }

    public void addData(String key, Object val) {
        data.put(key, val);
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }
}
