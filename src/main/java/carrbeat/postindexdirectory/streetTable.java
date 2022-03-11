package carrbeat.postindexdirectory;

public class streetTable {
    String idStreet, nameStreet, idLocalityCol;

    public streetTable(String idStreet, String nameStreet, String idLocalityCol){
        this.idStreet = idStreet;
        this.nameStreet = nameStreet;
        this.idLocalityCol = idLocalityCol;
    }

    public String getIdStreet(){
        return idStreet;
    }

    public void setId(String idStreet){
        this.idStreet = idStreet;
    }

    public String getNameStreet(){
        return nameStreet;
    }

    public void setNameStreet(String nameStreet){
        this.nameStreet = nameStreet;
    }

    public String getIdLocalityCol(){
        return idLocalityCol;
    }

    public void setIdLocalityCol(String idLocalityCol){
        this.idLocalityCol = idLocalityCol;
    }
}
