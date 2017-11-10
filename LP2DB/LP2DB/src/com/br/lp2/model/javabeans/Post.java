package com.br.lp2.model.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camila
 */
public class Post implements Serializable{
    private long id_post;
    private String posttext, image;
    private Date postdate;
    private Userlp2 userlp2;
    private List<Comment> comments;

    public Post() {
        comments = new ArrayList<>();
    }
    
    public void addComment(long id_comment, String commenttext, 
            Date commentdate, Userlp2 user){
        Comment c = new Comment();
        c.setId_comment(id_comment);
        c.setCommenttext(commenttext);
        c.setCommentdate(commentdate);
        c.setUserlp2(user);
        c.setFk_post(id_post);
        comments.add(c);
    }

    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }

    public String getPosttext() {
        return posttext;
    }

    public void setPosttext(String posttext) {
        this.posttext = posttext;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public Userlp2 getUserlp2() {
        return userlp2;
    }

    public void setUserlp2(Userlp2 userlp2) {
        this.userlp2 = userlp2;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", posttext=" + posttext + ", image=" + image + ", postdate=" + postdate + ", userlp2=" + userlp2 + '}';
    }
    
}
