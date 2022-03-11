package carrbeat.postindexdirectory;

public class commonTable {
    String idCommon, streetId, houseNumID, localityID;

    public commonTable(String idCommon, String streetID, String houseNumID, String localityID){
        this.idCommon = idCommon;
        this.streetId = streetID;
        this.houseNumID = houseNumID;
        this.localityID = localityID;
    }


    public String getIdCommon(){
        return idCommon;
    }

    public void setId(String idStreet){
        this.idCommon = idCommon;
    }

    public String getStreetId(){
        return streetId;
    }

    public void setStreetId(String streetID){
        this.streetId = streetID;
    }

    public String getHouseNumID(){
        return houseNumID;
    }

    public void setHouseNumID(String houseNumID){
        this.houseNumID = houseNumID;
    }

    public String getLocalityID() { return localityID; }

    public void setLocalityID(String localityID) { this.localityID = localityID; }
}
