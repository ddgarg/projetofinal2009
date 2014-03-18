package br.com.estudo.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReturnMessage {

    private boolean status;
    private String msg;
    private String token;

    public ReturnMessage(boolean status, String msg, String token) {
        super();
        this.status = status;
        this.msg = msg;
        this.token = token;
    }

    public ReturnMessage() {
        super();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new StringBuffer(" status : ").append(this.status)
                .append(" msg : ").append(this.msg)
                .append(" token : ").append(this.token).toString();
    }
}
