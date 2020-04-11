package ar.com.playmedia.controller;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import ar.com.playmedia.model.ProductM;
import ar.com.playmedia.model.TicketM;
import ar.com.playmedia.model.UserM;

public class TicketC{  
	private Connection dbConnection;
	private Statement query;
	private ResultSet result;
	private String server;
	private String username;
	private String pass;

	public TicketC() {
		server = "jdbc:postgresql://127.0.0.1:5432/StockController";
		username = "dba";
		pass = "123456";
    }
        
	public void connect() {	
		try {
			Class.forName("org.postgresql.Driver");
			dbConnection = DriverManager.getConnection(server, username, pass);
		} catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}


	public void disconnect() {
		try {
			dbConnection.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}  
    public ArrayList<TicketM> listTicket(TicketM oTicket){
        ArrayList<TicketM> iProducts = new ArrayList<TicketM>();
        return iProducts;
    }
    
    public void saveTicket(TicketM oTicket){
        String queryString = String.format("SELECT * from tickets_ins(%s, %d)"
            ,oTicket.getFloMount().toString().replace(",", ".")
            ,oTicket.getUser().getPersonId()
        );

		try {
			query = dbConnection.createStatement();
            query.execute(queryString);

            queryString = "select * from tickets order by id_ticket desc limit 1;";
            result = query.executeQuery(queryString);

            while(result.next()){
                Integer intIdTicket = result.getInt(1);
                for(ProductM product : oTicket.getProducts()){
                    queryString = String.format("SELECT * from tickets_products_ins(%d, %d, %s, %d)"
                        ,intIdTicket
                        ,product.getIntIdProduct()
                        ,product.getFloPrice().toString().replace(",", ".")
                        ,product.getIntStock()
                    );
    
                    query.execute(queryString);
                }
            }
            query.close();
		} catch (Exception e) {
            
        }
    }

	public ArrayList<TicketM> getWeekTickets() {
        ArrayList<TicketM> iTickets = new ArrayList<TicketM>();
        String queryString = "select * from tickets_week();";
        try {
			query = dbConnection.createStatement();
            result = query.executeQuery(queryString);

            while(result.next()){
                TicketM oTicket = new TicketM();
                oTicket.setIntIdTicket(result.getInt(1));//1
                oTicket.setDatTicketDate(result.getDate(2));//2
                oTicket.setFloMount(result.getFloat(3));//3

                UserC userController = new UserC();
                UserM oUser = new UserM(result.getInt(4), "", "", "", "", "");
                userController.connect();
                oTicket.setUser(userController.searchUsers(oUser).get(0));
                userController.disconnect();

                iTickets.add(oTicket);
            }
            query.close();
		} catch (Exception e) {
            
        }
		return iTickets;
	}
}