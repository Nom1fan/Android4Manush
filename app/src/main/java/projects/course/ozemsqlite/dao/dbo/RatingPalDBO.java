package projects.course.ozemsqlite.dao.dbo;

import java.util.Date;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */
public class RatingPalDBO extends DBO {

    private String date;
    private int journeyId;
    private int customerNumber;
    private int localPalNumber;
    private int rate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RatingPalDBO{" +
                "date=" + date +
                ", journeyId=" + journeyId +
                ", customerNumber=" + customerNumber +
                ", localPalNumber=" + localPalNumber +
                ", rate=" + rate +
                '}';
    }
}
