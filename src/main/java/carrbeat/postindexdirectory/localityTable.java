package carrbeat.postindexdirectory;

public class localityTable extends adminTable {
    String idlocality, locality;

    public localityTable(String idlocality, String locality){
        super();
        this.idlocality = idlocality;
        this.locality = locality;
    }

    public String getIdlocality(){
        return idlocality;
    }

    public void setIdlocality(String idlocality){
        this.idlocality = idlocality;
    }

    public String getLocality(){
        return locality;
    }

    public void setLogin(String locality){
        this.locality = locality;
    }


}
