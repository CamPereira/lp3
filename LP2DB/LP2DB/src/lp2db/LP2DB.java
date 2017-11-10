/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp2db;

import com.br.lp2.model.dao.Userlp2DAO;
import com.br.lp2.model.javabeans.Userinfo;
import com.br.lp2.model.javabeans.Userlp2;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camila
 */
public class LP2DB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Userlp2DAO dao = new Userlp2DAO();
        
        //-------- TESTE DO FINDALL ---------
//        List<Userlp2> listUsers = dao.findAll();
//        
//        for (Userlp2 user : listUsers) {
//            System.out.println(user);
//        }
        
        //-------- TESTE DO FIND BY ID ---------
//        Userlp2 user = dao.findById(2);
//        System.out.println(user);
        
        //-------- TESTE DO MODIFY ---------
//        Userlp2 user = dao.findById(2);
//        System.out.println(user);
//        user.getUserinfo().setEmail("teste@gmail.com");
//        dao.modify(user);
//        System.out.println(user);
        
        //-------- TESTE DO INSERT ---------
//        Userlp2 user = new Userlp2();
//        user.setUsername("ze");
//        user.setPassword("678");
//        
//        Userinfo ui = new Userinfo();
//        ui.setFirstname("Ze");
//        ui.setLastname("Goiaba");
//        ui.setBirthday(new Date());
//        ui.setEmail("zegoiaba@gmail.com");
//        ui.setAvatar("");
//        
//        user.setUserinfo(ui);
//        
//        dao.insert(user);
        
        //-------- TESTE DO REMOVE ---------
        Userlp2 user = dao.findById(3);
        dao.remove(user);
        
        List<Userlp2> users = dao.findAll();
        for (Userlp2 u : users) {
            System.out.println(u);
        }
        
    }
    
}
