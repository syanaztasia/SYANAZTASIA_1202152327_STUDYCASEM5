package com.example.syanaz.syanaztasia_1202152327_studycase5;

/**
 * Created by Syanaz on 3/27/2018.
 */
public class itemTodo {
    String todo, desc, prior;

    public itemTodo(String todo, String desc, String prior) {
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
