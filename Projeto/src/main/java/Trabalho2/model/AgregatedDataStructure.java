package Trabalho2.model;

public class AgregatedDataStructure implements Comparable<AgregatedDataStructure>{

    private Year firstYear, lastYear;
    private Item item;
    private Element element;
    private double averageValue;

    public AgregatedDataStructure(Year firstYear, Year lastYear, Item item, Element element, double averageValue) {
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.item = item;
        this.element = element;
        this.averageValue = averageValue;

    }

    @Override
    public int compareTo(AgregatedDataStructure o) {
        return (int)(o.averageValue-averageValue);
    }

    public boolean equals(AgregatedDataStructure obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        AgregatedDataStructure otherAgregatedDataStructure = (AgregatedDataStructure) obj;


        return firstYear.getYear() == otherAgregatedDataStructure.firstYear.getYear()
                && lastYear.getYear() == otherAgregatedDataStructure.lastYear.getYear()
                && item.equals(otherAgregatedDataStructure.item)
                && element.equals(otherAgregatedDataStructure.element)
                && averageValue == otherAgregatedDataStructure.averageValue;
    }

}
