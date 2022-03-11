package carrbeat.postindexdirectory;

public class houseNumTable {
    String idhouseNum, houseNum, postIndex;

    public houseNumTable(String idhouseNum, String houseNum, String postIndex){
        this.idhouseNum = idhouseNum;
        this.houseNum = houseNum;
        this.postIndex = postIndex;
    }

    public String getIdhouseNum(){
        return idhouseNum;
    }

    public void setIdhouseNum(String idhouseNum){
        this.idhouseNum = idhouseNum;
    }

    public String getHouseNum(){
        return houseNum;
    }

    public void setHouseNum(String houseNum){
        this.houseNum = houseNum;
    }

    public String getPostIndex(){
        return postIndex;
    }

    public void setPostIndex(String postIndex){
        this.postIndex = postIndex;
    }
}
