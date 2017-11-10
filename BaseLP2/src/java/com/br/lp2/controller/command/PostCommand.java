package com.br.lp2.controller.command;

import com.br.lp2.model.dao.PostDAO;
import com.br.lp2.model.javabeans.Post;
import com.br.lp2.model.javabeans.Userlp2;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Camila
 */
public class PostCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage = "error.jsp";
    private PostDAO dao;
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        dao = new PostDAO();
    }

    @Override
    public void execute() {
        String action = request.getParameter("command")
                .split("\\.")[1];
        switch(action){
            case "post":
                Post post = new Post();
                post.setPosttext(request.getParameter("posttext"));
                post.setPostdate(new Date());
                Userlp2 user = (Userlp2)request.getSession().getAttribute("user");
                post.setUserlp2(user);
                boolean resp = dao.insert(post);
                if(resp) request.getSession().setAttribute("msg", "Post inserted");
                else request.getSession().setAttribute("msg", "Oops!");
                responsePage = "home.jsp";
                
                List<Post> posts = dao.findByUser(user);
                user.clearPosts();
                for (Post p : posts) {
                    user.addPost(p.getId_post(),
                            p.getPosttext(),p.getPostdate(),null);
                }
                
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }
    
}
