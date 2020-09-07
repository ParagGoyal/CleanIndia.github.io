
package beans;

public class reqDetails {
    int reqId, sreqId, assignTo;
    String processingDate,reqStatus,dispatchLocation,reqDate;

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public reqDetails() {
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getSreqId() {
        return sreqId;
    }

    public void setSreqId(int sreqId) {
        this.sreqId = sreqId;
    }

    public int getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(int assignTo) {
        this.assignTo = assignTo;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getDispatchLocation() {
        return dispatchLocation;
    }

    public void setDispatchLocation(String dispatchLocation) {
        this.dispatchLocation = dispatchLocation;
    }
    
    
}
