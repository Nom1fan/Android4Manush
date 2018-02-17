package projects.course.ozemsqlite.dao.dbo;

import java.util.Date;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */
public class RatingPalDBO extends DBO {

    private Date date;
    private int journeyId;
    private int customerNumber;
    private int localPalNumber;
    private double rate;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getLocalPalNumber() {
        return localPalNumber;
    }

    public void setLocalPalNumber(int localPalNumber) {
        this.localPalNumber = localPalNumber;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
