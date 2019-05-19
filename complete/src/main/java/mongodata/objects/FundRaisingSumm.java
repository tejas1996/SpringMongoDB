package mongodata.objects;

import org.springframework.data.annotation.Id;

public class FundRaisingSumm {

    @Id
    public String id;

    private String year;
    private String budget;
    private String achieved;
    private String achievedPercentage;
    private String shortFall;
    private String surplus;

    public FundRaisingSumm(String year, String budget, String achieved, String achievedPercentage, String shortFall, String surplus) {
        this.year = year;
        this.budget = budget;
        this.achieved = achieved;
        this.achievedPercentage = achievedPercentage;
        this.shortFall = shortFall;
        this.surplus = surplus;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getAchieved() {
        return achieved;
    }

    public void setAchieved(String achieved) {
        this.achieved = achieved;
    }

    public String getAchievedPercentage() {
        return achievedPercentage;
    }

    public void setAchievedPercentage(String achievedPercentage) {
        this.achievedPercentage = achievedPercentage;
    }

    public String getShortFall() {
        return shortFall;
    }

    public void setShortFall(String shortFall) {
        this.shortFall = shortFall;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }
}