package kk.crm.entity;

public enum Res {
    SUCCESS(200,"请求成功"),
    ERROR(500,"服务器错误"),
    FAIL(400,"获取失败"),
    AUTH(300,"账号验证错误");

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int status;
    private String msg;

    Res(int status, String msg) {
        this.msg=msg;
        this.status=status;
    }
}
