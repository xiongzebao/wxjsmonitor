package cn.net.yto.module_sign.event;

import java.io.Serializable;

public class MessageEvent implements Serializable {
   private int type;
   private String title;
    private String message;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public MessageEvent(int type,String title, String message) {
        this.type = type;
        this.message = message;
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
