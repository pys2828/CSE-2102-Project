import java.io.Serializable;

public class MedCond implements Serializable {

    //INITALIZE ALL PRIVATE ATTRIBUTES OF MEDCOND (NO ONE EXCEPT MEDCOND CLASS NEEDS THESE)
    private String mdContact;
    private String mdPhone;
    private String algType;
    private String illType;

   //CONSTRUCTOR FOR MEDCOND
    public MedCond(String mdContact, String mdPhone, String algType, String illType)
    {
        this.mdContact = mdContact;
        this.mdPhone = mdPhone;
        this.algType = algType;
        this.illType = illType;
    }

    //GETTER METHODS FOR MEDCOND .. PUBLIC BC THEY'RE USED OUTSIDE OF MEDCOND
    public String getMdContact()
    {return mdContact;}

    public String getMdPhone()
    {return mdPhone;}

    public String getAlgType()
    {return algType;}

    public String getIllType()
    {return illType;}

    //SETTER METHODS FOR MEDCOND .. PUBLIC BC THEY'RE USED OUTSIDE OF MEDCOND
    public void setMdContact(String mdContact)
    {this.mdContact = mdContact;}

    public void setMdPhone(String mdPhone)
    {this.mdPhone = mdPhone;}

    public void setAlgType(String algType)
    {this.algType = algType;}

    public void setIllType(String illType)
    {this.illType = illType;}
}
