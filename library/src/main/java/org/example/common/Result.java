package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.example.common.Constant.CODE_OK;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(CODE_OK);
        result.setMsg(Constant.MSG_OK);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<>();
        result.setCode(Constant.CODE_ERROR);
        result.setMsg(Constant.MSG_ERROR);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(CODE_OK);
        result.setMsg(Constant.MSG_OK);
        return result;
    }
}
