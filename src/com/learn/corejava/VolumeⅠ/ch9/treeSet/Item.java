package com.learn.corejava.VolumeⅠ.ch9.treeSet;

import java.util.Objects;

/**
 * An item with a description and a part number
 * @author HP
 */
public class Item implements Comparable {
    private String description;
    private int partNumber;

    /**
     * Constructs an item
     * @param description the item's description
     * @param partNumber the item's partNumber
     */
    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[description=" + description + ", partNumber=" + partNumber + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return Objects.equals(description, other.description)
                && partNumber == other.partNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, partNumber);
    }

    @Override
    public int compareTo(Object obj) {
        Item other = (Item) obj;
        int diff = Integer.compare(partNumber, other.partNumber);
        return diff != 0 ? diff : description.compareTo(other.description);
    }
}
