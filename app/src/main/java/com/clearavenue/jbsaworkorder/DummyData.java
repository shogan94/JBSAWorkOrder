package com.clearavenue.jbsaworkorder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shane.hogan on 1/14/2016.
 */
public class DummyData implements Parcelable{

    String installation;
    String organization;
    String workOrderNumber;
    String facilityIDNumber;
    String workOrderTitle;
    String workOrderStatus;
    int pizzaTrackerCode;
    int dateOpened;
    double totalCost;

    public DummyData(){
        super();
        this.installation = "";
        this.organization = "";
        this.workOrderNumber = "";
        this.facilityIDNumber = "";
        this.workOrderTitle = "";
        this.workOrderStatus = "";
        this.pizzaTrackerCode = 0;
        this.dateOpened = 0;
        this.totalCost = 0.0;
    }

    public DummyData(String installation, String organization, String workOrderNumber, String facilityIDNumber, String workOrderTitle, String workOrderStatus, int pizzaTrackerCode, int dateOpened, double totalCost){
        super();
        this.installation = installation;
        this.organization = organization;
        this.workOrderNumber = workOrderNumber;
        this.facilityIDNumber = facilityIDNumber;
        this.workOrderTitle = workOrderTitle;
        this.workOrderStatus = workOrderStatus;
        this.pizzaTrackerCode = pizzaTrackerCode;
        this.dateOpened = dateOpened;
        this.totalCost = totalCost;
    }

    protected DummyData(Parcel in) {
        installation = in.readString();
        organization = in.readString();
        workOrderNumber = in.readString();
        facilityIDNumber = in.readString();
        workOrderTitle = in.readString();
        workOrderStatus = in.readString();
        pizzaTrackerCode = in.readInt();
        dateOpened = in.readInt();
        totalCost = in.readDouble();
    }

    public static final Parcelable.Creator<DummyData> CREATOR = new Parcelable.Creator<DummyData>() {
        @Override
        public DummyData createFromParcel(Parcel in) {
            return new DummyData(in);
        }

        @Override
        public DummyData[] newArray(int size) {
            return new DummyData[size];
        }
    };

    public void setInstallation(String installation){
        this.installation = installation;
    }

    public String getInstallation(){
        return installation;
    }

    public void setOrganization(String organization){
        this.organization = organization;
    }

    public String getOrganization(){
        return organization;
    }

    public void setWorkOrderNumber(String workOrderNumber){
        this.workOrderNumber = workOrderNumber;
    }

    public String getWorkOrderNumber(){
        return workOrderNumber;
    }

    public void setFacilityIDNumber(String facilityIDNumber){
        this.facilityIDNumber = facilityIDNumber;
    }

    public String getFacilityIDNumber(){
        return facilityIDNumber;
    }

    public void setWorkOrderTitle(String workOrderTitle){
        this.workOrderTitle = workOrderTitle;
    }

    public String getWorkOrderTitle(){
        return workOrderTitle;
    }

    public void setWorkOrderStatus(String workOrderStatus){
        this.workOrderStatus = workOrderStatus;
    }

    public String getWorkOrderStatus(){
        return workOrderStatus;
    }

    public void setPizzaTrackerCode(int pizzaTrackerCode){
        this.pizzaTrackerCode = pizzaTrackerCode;
    }

    public int getPizzaTrackerCode(){
        return pizzaTrackerCode;
    }

    public void setDateOpened(int dateOpened){
        this.dateOpened = dateOpened;
    }

    public int getDateOpened(){
        return dateOpened;
    }

    public void setTotalCost(double totalCost){
        this.totalCost = totalCost;
    }

    public double getTotalCost(){
        return totalCost;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(installation);
        dest.writeString(organization);
        dest.writeString(workOrderNumber);
        dest.writeString(facilityIDNumber);
        dest.writeString(workOrderTitle);
        dest.writeString(workOrderStatus);
        dest.writeInt(pizzaTrackerCode);
        dest.writeInt(dateOpened);
        dest.writeDouble(totalCost);
    }
}
