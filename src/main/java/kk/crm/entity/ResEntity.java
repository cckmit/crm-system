package kk.crm.entity;

public class ResEntity {
    private int status;
    private String msg;
    private Object data;

    public int getStatus() {
        return status;
    }

    public ResEntity() {
    }

    public ResEntity setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResEntity setData(Object data) {
        this.data = data;
        return this;
    }

    public ResEntity(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
