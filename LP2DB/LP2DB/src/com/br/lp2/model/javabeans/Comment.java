package com.br.lp2.model.javabeans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Camila
 */
public class Comment implements Serializable{
    private long id_comment;
    private String commenttext;
    private Date commentdate;
    private long fk_post; // id do post
    private Userlp2 userlp2; //quem comentou

    public Comment() {
    }

    public long getId_comment() {
        return id_comment;
    }

    public void setId_comment(long id_comment) {
        this.id_comment = id_comment;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public long getFk_post() {
        return fk_post;
    }

    public void setFk_post(long fk_post) {
        this.fk_post = fk_post;
    }

    public Userlp2 getUserlp2() {
        return userlp2;
    }

    public void setUserlp2(Userlp2 userlp2) {
        this.userlp2 = userlp2;
    }

    @Override
    public String toString() {
        return "Comment{" + "id_comment=" + id_comment + ", commenttext=" + commenttext + ", commentdate=" + commentdate + ", fk_post=" + fk_post + ", userlp2=" + userlp2 + '}';
    }
}
