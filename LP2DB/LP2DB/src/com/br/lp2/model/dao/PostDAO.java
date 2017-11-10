package com.br.lp2.model.dao;

import com.br.lp2.model.connection.SingletonConnection;
import com.br.lp2.model.javabeans.Userinfo;
import com.br.lp2.model.javabeans.Post;
import com.br.lp2.model.javabeans.Userlp2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camila
 */
public class PostDAO implements GenericDAO<Post> {

    @Override
    public boolean insert(Post e) {
        boolean result = false;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();
        
        //Passo 2
        String sql = "INSERT INTO post(posttext,postdate,"
                + "fk_userlp2) VALUES(?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, e.getPosttext());
            pst.setDate(2, new java.sql.Date(e.getPostdate().getTime()));
            pst.setLong(3, e.getUserlp2().getId_userlp2());
            
            //Passo 3
            int resp = pst.executeUpdate();
            System.out.println("RESP "+resp);
            if(resp>0){
                result = true;
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        //Passo 1 - Estabelecer a conexão
        Connection con = SingletonConnection.getInstance().getConnection();
        //Passo 2 - Criar um statement
        String sql = "SELECT * FROM post INNER JOIN userlp2 ON fk_userlp2=id_userlp2 JOIN userinfo ON id_userlp2=id_userinfo";
        try {
            Statement stm = con.createStatement();

            //Passo 3 - executar a consulta
            ResultSet rs = stm.executeQuery(sql);

            //Passo 4 - tratar os resultados
            while (rs.next()) {
                Userlp2 u = new Userlp2();

                Userinfo ui = new Userinfo();
                ui.setFirstname(rs.getString("firstname"));
                ui.setLastname(rs.getString("lastname"));
                ui.setEmail(rs.getString("email"));
                ui.setId_userinfo(rs.getLong("id_userinfo"));
                java.sql.Date bday = rs.getDate("birthday");
                ui.setBirthday(new java.util.Date(bday.getTime()));
                ui.setAvatar(null);

                u.setUserinfo(ui);
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setId_userlp2(rs.getLong("id_userlp2"));

                Post p = new Post();
                p.setId_post(rs.getLong("id_post"));
                p.setPostdate(new Date(rs.getDate("postdate").getTime()));
                p.setUserlp2(u);
                posts.add(p);
            }

            //Passo 5 - fechar a conexão/statement
            stm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(long id) {
        Post post = new Post();
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();

        //Passo 2
        String sql = "SELECT * FROM userlp2 INNER JOIN userinfo ON id_userlp2=id_userinfo WHERE id_userlp2=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            
            //Passo 3
            ResultSet rs = pst.executeQuery();
            
            //Passo 4
            while(rs.next()){
                Userlp2 user = new Userlp2();
                Userinfo ui = new Userinfo();
                ui.setFirstname(rs.getString("firstname"));
                ui.setLastname(rs.getString("lastname"));
                ui.setEmail(rs.getString("email"));
                ui.setId_userinfo(rs.getLong("id_userinfo"));
                java.sql.Date bday = rs.getDate("birthday");
                ui.setBirthday(new java.util.Date(bday.getTime()));
                ui.setAvatar(null);

                user.setUserinfo(ui);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_userlp2(rs.getLong("id_userlp2"));
                
                post.setUserlp2(user);
                post.setPostdate(new java.util.Date(rs.getDate("postdate").getTime()));
                post.setPosttext(rs.getString("posttext"));
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return post;
    }
    
    public List<Post> findByUser(Userlp2 user) {
        List<Post> posts = new ArrayList<>();
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();

        //Passo 2
        String sql = "SELECT * FROM post WHERE fk_userlp2=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, user.getId_userlp2());
            
            //Passo 3
            ResultSet rs = pst.executeQuery();
            
            //Passo 4
            while(rs.next()){
                Post post = new Post();
                post.setUserlp2(user);
                post.setPostdate(new java.util.Date(rs.getDate("postdate").getTime()));
                post.setPosttext(rs.getString("posttext"));
                posts.add(post);
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }

    @Override
    public boolean modify(Post e) {
        boolean result = false;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();
        
        //Passo 2
        String sql = "UPDATE post SET postdate=?, posttext=? WHERE id_post=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(e.getPostdate().getTime()));
            pst.setString(2, e.getPosttext());
            pst.setLong(3, e.getId_post());
            
            //Passo 3
            int result2 = pst.executeUpdate();
            
            //Passo 4
            if(result2>0){
                result = true;
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean remove(Post e) {
        boolean result = false;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();
        
        //Passo 2
        String sql = "DELETE FROM post WHERE id_post=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, e.getId_post());
            
            //Passo 3
            int resp = pst.executeUpdate();
            
            //Passo 4
            if(resp>0) result = true;
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return result;
    }

}
