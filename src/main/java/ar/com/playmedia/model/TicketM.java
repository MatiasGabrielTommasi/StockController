package ar.com.playmedia.model;

import java.util.Date;
import java.text.DecimalFormat;
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
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00"); 
        String id = String.valueOf(this.intIdTicket);
        String mount = decimalFormat.format(this.floMount);
        String date = this.datTicketDate.toString();
        String vendor = String.valueOf(this.oUser.getName());

        int aux = id.length();
        for(int i = aux;i<8;i++)
            id = id + " ";
                
        aux = mount.length();
        for(int i = aux;i<9;i++)
            mount = " " + mount;
        
        aux = date.length();
        for(int i = aux;i<12;i++)
            date = date + " ";
            
        aux = vendor.length();
        for(int i = aux;i<25;i++)
            vendor = vendor + " ";
            
        //System.out.println("ID      8|fecha       12|monto     10|vendedor                 25|");
        String result = String.format("%s%s$%s     %s", id, date, mount, vendor); 
        return result;
    }

    public String toDetailString(){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String result = "Detalle del tikcet\n\r";
        result = result + "PRODUCTO                                     PRECIO    |\n\r";
        for(ProductM product : this.iProducts){
            this.floMount = this.floMount + (product.getIntStock() * product.getFloPrice());
            result += product.toTicketString() + "\n\r";
        }

        result += String.format("TOTAL: $%s", decimalFormat.format(this.floMount));
        return result;
    }
}