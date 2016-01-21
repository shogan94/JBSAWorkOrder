package com.clearavenue.jbsaworkorder;

/**
 * Created by shane.hogan on 1/16/2016.
 */
public class SearchResults {

    private String woTitle;
    private String wonr;
    private String woDate;
    private String woStatus;
    private String flag;

    public SearchResults(){
        super();
        this.woTitle = "";
        this.wonr = "";
        this.woDate = "";
        this.woStatus = "";
        this.flag = "";
    }

    public SearchResults(String woTitle, String wonr, String woDate, String woStatus,String flag){
        super();
        this.woTitle = woTitle;
        this.wonr = wonr;
        this.woDate = woDate;
        this.woStatus = woStatus;
        this.flag = flag;
    }

    public void setWOTitle(String woTitle){
        this.woTitle = woTitle;
    }
    public String getWoTitle(){
        return woTitle;
    }
    public void setWonr(String wonr){
        this.wonr = wonr;
    }
    public String getWonr(){
        return wonr;
    }
    public void setWoDate(String woDate){
        this.woDate = woDate;
    }
    public String getWoDate(){
        return woDate;
    }
    public void setWoStatus(String woStatus){
        this.woStatus = woStatus;
    }
    public String getWoStatus(){
        return woStatus;
    }
    public String getFlag(){return flag;}
    public void setFlag(String flag){this.flag = flag;}

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null){
            return false;
        }
        if(getClass() != object.getClass()){
            return false;
        }
        SearchResults result = (SearchResults) object;
        if((this.wonr == null) ? (result.wonr == null): this.wonr.equals(result.wonr)){
            return true;
        }
        return false;
    }

}
