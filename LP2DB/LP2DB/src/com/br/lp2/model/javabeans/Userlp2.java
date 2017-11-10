package com.br.lp2.model.javabeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camila
 */
public class Userlp2 implements Serializable{
    private long id_userlp2;
    private String username, password;
    private Userinfo userinfo;
    private List<Post> posts;

    public Userlp2() {
        posts = new ArrayList<>();
    }
    
    public void addPost(long id_post, String posttext, 
            Date postdate, String image ){
        Post post = new Post();
        post.setId_post(id_post);
        post.setPosttext(posttext);
        post.setPostdate(postdate);
        post.setImage(image);
        post.setUserlp2(this);
        posts.add(post);
    }

    public void clearPosts(){
        this.posts = new ArrayList<>();
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public long getId_userlp2() {
        return id_userlp2;
    }

    public void setId_userlp2(long id_userlp2) {
        this.id_userlp2 = id_userlp2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "Userlp2{" + "id_userlp2=" + id_userlp2 + ", username=" + username + ", password=" + password + ", userinfo=" + userinfo + ", posts=" + posts + '}';
    }

}
