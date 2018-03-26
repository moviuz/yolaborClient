package mx.com.omnius.yolabor.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by omnius on 02/03/18.
 */

public class JobModel {


    public JobModel(String idJob, String idClient, String idWorker, String idSubcontractor, String idType, String idLocation, String idCurrentStatus, String startTime, String endTime, String description, String addressDetails, String comments, String totalCost, String idPayment, String paymentReference, String latitude, String longitude, String idService, String fullName) {
        this.idJob = idJob;
        this.idClient = idClient;
        this.idWorker = idWorker;
        this.idSubcontractor = idSubcontractor;
        this.idType = idType;
        this.idLocation = idLocation;
        this.idCurrentStatus = idCurrentStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.addressDetails = addressDetails;
        this.comments = comments;
        this.totalCost = totalCost;
        this.idPayment = idPayment;
        this.paymentReference = paymentReference;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idService = idService;
        this.fullName = fullName;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }


    public String getIdWorker() { return idWorker; }

    public void setIdWorker(String idWorker) {
        this.idWorker = idWorker;
    }

    public String getIdSubcontractor() { return idSubcontractor; }

    public void setIdSubcontractor(String idSubcontractor) {
        this.idSubcontractor = idSubcontractor;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdCurrentStatus() {
        return idCurrentStatus;
    }

    public void setIdCurrentStatus(String idCurrentStatus) {
        this.idCurrentStatus = idCurrentStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdJob() { return idJob; }

    public void setIdJob(String idJob) { this.idJob = idJob;}


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @SerializedName("idJob")
    private String idJob;

    @SerializedName("idClient")
    private String idClient;

    @SerializedName("idWorker")
    private String idWorker;

    @SerializedName("idSubcontractor")
    private String idSubcontractor;

    @SerializedName("idType")
    private String idType;

    @SerializedName("idLocation")
    private String idLocation;

    @SerializedName("idCurrentStatus")
    private String idCurrentStatus;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("description")
    private String description;

    @SerializedName("addressDetails")
    private String addressDetails;

    @SerializedName("comments")
    private String comments;

    @SerializedName("totalCost")
    private String totalCost;

    @SerializedName("idPayment")
    private String idPayment;

    @SerializedName("paymentReference")
    private String paymentReference;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("idService")
    private String idService;


    @SerializedName("fullName")
    private String fullName;
}
