package com.wanxi.result;

import lombok.Data;

@Data
public class ResultModel {
    private Integer code = 0;//状态编码，一般默认情况下  成功返回0，
    private String msg;
    private Integer count;
    private Object data;
    private static ResultModel resultModel;

    private ResultModel() {
    }

    private static ResultModel getSingtenModel(Integer code, String msg, Integer count, Object data) {
        if (resultModel == null) {
            resultModel = new ResultModel();
        }
        resultModel.setData(data);
        resultModel.setCount(count);
        resultModel.setCode(code);
        resultModel.setMsg(msg);
        return resultModel;
    }

    public static ResultModel getModel(Integer count, Object data) {
        return getSingtenModel(0, "success", count, data);
    }

    public static ResultModel getModel(Integer count) {
        return getSingtenModel(0, "success", count, null);
    }

    public static ResultModel getModel(Object data) {
        return getSingtenModel(0, "success", 1, data);
    }

    public static ResultModel noLogin() {
        return getSingtenModel(ResultTool.NO_LOGIN.getCode(), ResultTool.NO_LOGIN.getMsg(), 1, null);
    }
    public  ResultModel logined(){
        return  getSingtenModel (ResultTool.SUCCESS.getCode (),ResultTool.SUCCESS.getMsg (),1,resultModel.getData ());
    }
}
