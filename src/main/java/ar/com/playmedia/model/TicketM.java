package ar.com.playmedia.model;

import java.util.Date;
import java.util.ArrayList;
import ar.com.playmedia.model.ProductM;

public class TicketM{
    private Integer intIdTicket;
    private Date datTicketDate;
    private Float floMount;
    private UserM oUser;
    private ArrayList<ProductM> iProducts;

    public Date getDatTicketDate(){return this.datTicketDate;}
    public void setDatTicketDate(Date datTicketDate){this.datTicketDate = datTicketDate;}

    public Integer getIntIdTicket(){return this.intIdTicket;}
    public void setIntIdTicket(Integer intIdTicket){this.intIdTicket = intIdTicket;}

    public Float getFloMount(){return this.floMount;}
    public void setFloMount(Float floMount){this.floMount = floMount;}

    public UserM getUser(){return this.oUser;}
    public void setUser(UserM oUser){this.oUser = oUser;}

    public ArrayList<ProductM> getProducts(){return this.iProducts;}
    public void setProducts(ArrayList<ProductM> iProducts){this.iProducts = iProducts;}

    public TicketM(){
        Integer _cero = 0;

        this.intIdTicket = _cero;
        this.datTicketDate = new Date();
        this.floMount = Float.valueOf(_cero);
        this.oUser = new UserM();
        this.iProducts = new ArrayList<ProductM>();
    }

    public TicketM(Integer intIdTicket, Date datTicketDate, Float floMount, UserM oUser, ArrayList<ProductM> iProducts){
        this.intIdTicket = intIdTicket;
        this.datTicketDate = datTicketDate;
        this.floMount = floMount;
        this.oUser = oUser;
        this.iProducts = iProducts;
    }    

    public String toString(){
        return String.format("%d\t%t\t%d\t%f\t%s", this.intIdTicket, this.datTicketDate, this.iProducts.size(), this.floMount, this.oUser.getName());
    }

    public String toReportString(){
        String result = String.format("%d\t%s\t%s\t%s", this.intIdTicket, this.datTicketDate.toLocaleString(), this.floMount.toString(), this.oUser.getName()); 
        return result;
    }

    public String toDetailString(){
        String result = String.format("Detalle del tikcet $d\n\r", this.intIdTicket);
        for(ProductM product : this.iProducts){
            this.floMount = this.floMount + (product.getIntStock() * product.getFloPrice());
            result += String.format("\t(%d) %s\t\t$%s\n\r", product.getIntStock(), product.getStrName(), product.getFloPrice().toString());
        }

        result += String.format("TOTAL: $%f", this.floMount);
        return result;
    }
}