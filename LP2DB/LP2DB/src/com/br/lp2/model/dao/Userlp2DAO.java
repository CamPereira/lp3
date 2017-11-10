package com.br.lp2.model.dao;

import com.br.lp2.model.connection.SingletonConnection;
import com.br.lp2.model.javabeans.Userinfo;
import com.br.lp2.model.javabeans.Userlp2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camila
 */
public class Userlp2DAO implements GenericDAO<Userlp2> {

    @Override
    public boolean insert(Userlp2 e) {
        boolean result = false;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();
        
        //Passo 2
        String sql = "INSERT INTO userlp2(username,password) VALUES(?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, e.getUsername());
            pst.setString(2, e.getPassword());
            
            //Passo 3
            int resp = pst.executeUpdate();
            long key = 0;
            ResultSet keys = pst.getGeneratedKeys();
            while(keys.next()){
                key = keys.getLong(1);
            }
            if(resp>0){
                //Passo 2b
                String sqlb = "INSERT INTO userinfo VALUES(?,?,?,?,?,null)";
                PreparedStatement pstb = con.prepareStatement(sqlb);
                pstb.setLong(1, key);
                pstb.setString(2, e.getUserinfo().getFirstname());
                pstb.setString(3, e.getUserinfo().getLastname());
                pstb.setString(4, e.getUserinfo().getEmail());
                pstb.setDate(5, new java.sql.Date(
                        e.getUserinfo().getBirthday().getTime()));
                //pstb.setString(6, "");
            
                //Passo 3b e 4b
                result = pstb.execute();
                
                //Passo 5b
                pstb.close();
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Userlp2DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }

    @Override
    public List<Userlp2> findAll() {
        List<Userlp2> users = new ArrayList<>();
        //Passo 1 - Estabelecer a conexão
        Connection con = SingletonConnection.getInstance().getConnection();
        //Passo 2 - Criar um statement
        String sql = "SELECT * FROM userlp2 INNER JOIN userinfo ON id_userlp2=id_userinfo";
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

                users.add(u);
            }

            //Passo 5 - fechar a conexão/statement
            stm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public Userlp2 findById(long id) {
        Userlp2 user = new Userlp2();
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
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public Userlp2 findByUsername(String username) {
        Userlp2 user = null;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();

        //Passo 2
        String sql = "SELECT * FROM userlp2 INNER JOIN userinfo ON id_userlp2=id_userinfo WHERE username=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            
            //Passo 3
            ResultSet rs = pst.executeQuery();
            
            //Passo 4
            while(rs.next()){
                Userinfo ui = new Userinfo();
                ui.setFirstname(rs.getString("firstname"));
                ui.setLastname(rs.getString("lastname"));
                ui.setEmail(rs.getString("email"));
                ui.setId_userinfo(rs.getLong("id_userinfo"));
                java.sql.Date bday = rs.getDate("birthday");
                ui.setBirthday(new java.util.Date(bday.getTime()));
                ui.setAvatar(null);

                user = new Userlp2();
                user.setUserinfo(ui);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_userlp2(rs.getLong("id_userlp2"));
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public Userlp2 findByEmail(String email) {
        Userlp2 user = null;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();

        //Passo 2
        String sql = "SELECT * FROM userlp2 INNER JOIN userinfo ON id_userlp2=id_userinfo WHERE email=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            
            //Passo 3
            ResultSet rs = pst.executeQuery();
            
            //Passo 4
            while(rs.next()){
                Userinfo ui = new Userinfo();
                ui.setFirstname(rs.getString("firstname"));
                ui.setLastname(rs.getString("lastname"));
                ui.setEmail(rs.getString("email"));
                ui.setId_userinfo(rs.getLong("id_userinfo"));
                java.sql.Date bday = rs.getDate("birthday");
                ui.setBirthday(new java.util.Date(bday.getTime()));
                ui.setAvatar(null);

                user = new Userlp2();
                user.setUserinfo(ui);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_userlp2(rs.getLong("id_userlp2"));
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean modify(Userlp2 e) {
        boolean result = false;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();
        
        //Passo 2
        String sql = "UPDATE userlp2 SET username=?, password=? WHERE id_userlp2=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, e.getUsername());
            pst.setString(2, e.getPassword());
            pst.setLong(3, e.getId_userlp2());
            
            //Passo 3
            int result2 = pst.executeUpdate();
            
            //Passo 4
            if(result2>0){
                System.out.println("AQUI");
                //Passo 2 b
                String sqlb = "UPDATE userinfo "
                        + "SET firstname=?, lastname=?, email=?, "
                        + "birthday=?, avatar=null WHERE id_userinfo=?";
                PreparedStatement pstb = con.prepareStatement(sqlb);
                pstb.setString(1, e.getUserinfo().getFirstname());
                pstb.setString(2, e.getUserinfo().getLastname());
                pstb.setString(3, e.getUserinfo().getEmail());
                pstb.setDate(4, new java.sql.Date(
                        e.getUserinfo().getBirthday().getTime())
                );
//                pstb.setString(5, "");
                pstb.setLong(5, e.getUserinfo().getId_userinfo());
                
                //Passo 3b e 4b (JUNTOS) 
                result = (pstb.executeUpdate()>0);
                
                //Passo 5b
                pstb.close();
            }
            
            //Passo 5
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Userlp2DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean remove(Userlp2 e) {
        boolean result = false;
        //Passo 1
        Connection con = SingletonConnection.getInstance().getConnection();
        
        //Passo 2
        String sql = "DELETE FROM userlp2 WHERE id_userlp2=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, e.getId_userlp2());
            
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
