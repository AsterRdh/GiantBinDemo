package space.cyberaster.cycling.web.vo;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AppResponse<T> {
    private int code;
    private boolean ok;
    private String message;
    private T data;
    private String errorInfo;


    private AppResponse(int code, boolean ok, String message, T data) {
        this.code = code;
        this.ok = ok;
        this.message = message;
        this.data = data;
    }


    public AppResponse(int code, boolean ok, String message, T data, String errorInfo) {
        this.code = code;
        this.ok = ok;
        this.message = message;
        this.data = data;
        this.errorInfo = errorInfo;
    }

    public static <T> AppResponse<T> OK(T data) {
        return OK("成功",data);
    }
    public static <T> AppResponse<T> OK(String msg) {
        return OK(msg, null);
    }
    public static <T> AppResponse<T> OK(String msg, T data) {
        return new AppResponse<>(200, true, msg, data);
    }

    public static <T> AppResponse<T> ERROR(String msg) {
        return ERROR(msg, null);
    }
    public static <T> AppResponse<T> ERROR(Throwable throwable) {
        return ERROR(throwable.getMessage(), throwable);
    }
    public static <T> AppResponse<T> ERROR(String msg, Throwable throwable) {
        String stackTraceString = null;
        if (throwable != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw  = new PrintWriter(sw, true);
            throwable.printStackTrace(pw);
            stackTraceString  =  sw.getBuffer().toString();
        }
        return new AppResponse<>(500, false, msg, null, stackTraceString );
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
