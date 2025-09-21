package lk.ijse.b72.finalproject.back_end.util;

public class ResponceUtil {


    private int code;
    private String message;


    // most super type eka object nisa thamai methana data eke type eka object kiyala gaththe .mokada methanata onama type ekaka data ekak etenne puluwwan nisa (iteka array list ekak ,iteka thawath ekak)
    private Object data;

    public ResponceUtil() {
    }

    public ResponceUtil(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "ResponceUtil{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
