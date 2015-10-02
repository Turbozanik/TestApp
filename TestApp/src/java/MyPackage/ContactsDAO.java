package MyPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Лапа
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactsDAO {
    
    String user = "root";//Логин пользователя
    String password = "3070409j";//Пароль пользователя
    String url = "jdbc:mysql://localhost:3306/TestDataBase?autoReconnect=true&"
               + "amp;useUnicode=true&amp;characterEncoding=utf-8";//URL адрес
    String driver = "com.mysql.jdbc.Driver";//Имя драйвера
    
    
     public List<Contact> readData(){
        List<Contact>contactList = new ArrayList<Contact>();
        Contact contact;
          try {
               Class.forName(driver);//Регистрируем драйвер
          } catch (ClassNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
          Connection c = null;//Соединение с БД
          try{
               c = DriverManager.getConnection(url, user, password);//Установка соединения с БД
               Statement st = c.createStatement();//Готовим запрос
               ResultSet rs = st.executeQuery("select * from Contacts");//Выполняем запрос к БД, результат в переменной rs
               while(rs.next()){
                    contact = new Contact();
                    contact.setID(Integer.valueOf(rs.getString("ID")));//Последовательно для каждой строки выводим значение из колонки ColumnName
                    contact.setName(rs.getString("Name"));
                    contact.setSureName(rs.getString("SureName"));
                    contact.setLogin(rs.getString("Login"));
                    contact.setEmail(rs.getString("Email"));
                    contact.setPhoneNumber(rs.getString("PhoneNumber"));
                    contactList.add(contact);
               }
          } catch(Exception e){
               e.printStackTrace();
          }
          finally{
               //Обязательно необходимо закрыть соединение
               try {
                    if(c != null)
                    c.close();
               } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
               }
          }
        return contactList;
     }
     
     
     public List<Contact> addData(List<Contact> list) throws SQLException{
                Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
                
		String insertTableSQL = "INSERT INTO Contacts"
				+ "(Name, SureName, Login, Email, PhoneNumber) VALUES"
				+ "(?,?,?,?,?)";

		try {
			dbConnection = DriverManager.getConnection(url, user, password);
                        //Statement st = dbConnection.createStatement();//Готовим запрос
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
                        for(Contact item : list){
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getSureName());
			preparedStatement.setString(3, item.getLogin());
                        preparedStatement.setString(4, item.getEmail());
                        preparedStatement.setString(5, item.getPhoneNumber());
                        preparedStatement.addBatch();
			//preparedStatement.executeUpdate();
                        }
                        preparedStatement.executeBatch();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
        return null;

	}
     public List<Contact> updateData(List<Contact> list) throws SQLException{//should be changed
                Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
                
		try {
			dbConnection = DriverManager.getConnection(url, user, password);
                        // create the java mysql update preparedstatement
                        String query = "update Contacts set Name=?,SecondName=?,Login=?Email=?,PhoneNumber=? where id = ?";
                        preparedStatement = dbConnection.prepareStatement(query);
                        for(Contact item : list){
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getSureName());
			preparedStatement.setString(3, item.getLogin());
                        preparedStatement.setString(4, item.getEmail());
                        preparedStatement.setString(5, item.getPhoneNumber());
                        preparedStatement.setInt(6, item.getID());
                        preparedStatement.addBatch();
			//preparedStatement.executeUpdate();
                        }
                        preparedStatement.executeBatch();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
        return null;

	}
//        List<Contact>contactList = new ArrayList<Contact>();
//        Contact contact;
//          try {
//               Class.forName(driver);//Регистрируем драйвер
//          } catch (ClassNotFoundException e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//          }
//          Connection c = null;//Соединение с БД
//          try{
//                c = DriverManager.getConnection(url, user, password);//Установка соединения с БД
//                Statement st = c.createStatement();//Готовим запрос
//                String insertTableSQL = "INSERT INTO Contacts"
//				+ "(ID, NAME, UreName, Login,Email,PhoneNumber) VALUES"
//				+ "(?,?,?,?,?)";
//
//                for(Contact item : list){
//                
//                st.executeUpdate(insertTableSQL);
//                }
//          } catch(Exception e){
//               e.printStackTrace();
//          }
//          finally{
//               //Обязательно необходимо закрыть соединение
//               try {
//                    if(c != null)
//                    c.close();
//               } catch (SQLException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//               }
//          }
//        return contactList;
//     }
}
