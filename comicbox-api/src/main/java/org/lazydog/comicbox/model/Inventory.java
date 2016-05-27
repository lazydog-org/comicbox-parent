package org.lazydog.comicbox.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent an inventory.
 *
 * @author  Ron Rickard
 */
public class Inventory extends Entity<Inventory> implements Comparable<Inventory> {

    private static final long serialVersionUID = 1L;

    @Valid @NotNull(message="Comic is required.")
    private Comic comic;
    @Valid @NotNull(message="Grade is required.")
    private Grade grade;
    @Valid @NotNull(message="Location is required.")
    private Location location;
    private Double purchasePrice = 0.0;
    @NotNull(message="Quantity is required.")
    @Min(value=1, message="Quantity must be at least 1.")
    private Integer quantity = 1;
    @NotNull(message="User UUID is required.")
    private String userUuid;

    /**
     * Compare this object to the specified object.
     *
     * @param  that  the object to compare this object against.
     *
     * @return  the value 0 if this object is equal to the object;
     *          a value less than 0 if this object is less than the object;
     *          and a value greater than 0 if this object is greater than the
     *          object.
     */
    @Override
    public int compareTo(Inventory that) {
        return new CompareToBuilder()
                .append(this.getUserUuid(), that.getUserUuid())
                .append(this.getComic(), that.getComic())
                .append(this.getGrade(), that.getGrade())
                .append(this.getLocation(), that.getLocation())
                .toComparison();
    }
                                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Inventory copy() {
        return SerializationUtils.clone(this);
    }
    
    /**
     * Compare this object to the specified object.
     *
     * @param  object  the object to compare this object against.
     *
     * @return  true if the objects are equal; false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        
        // Assume the objects are not equal.
        boolean equals = false;
        
        // Check if the object is an instance of this class
        // and is equal to this object.
        if (object instanceof Inventory &&
            this.compareTo((Inventory)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the comic.
     *
     * @return  the comic.
     */
    public Comic getComic() {
        return this.comic;
    }
   
    /**
     * Get the comic grade.
     *
     * @return  the comic grade.
     */
    public Grade getGrade() {
        return this.grade;
    }

    /**
     * Get the location.
     *
     * @return  the location.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Get the purchase price.
     *
     * @return  the purchase price.
     */
    public Double getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Get the quantity.
     *
     * @return  the quantity.
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Get the user UUID.
     *
     * @return  the user UUID.
     */
    public String getUserUuid() {
        return this.userUuid;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getUserUuid())
                .append(this.getComic())
                .append(this.getGrade())
                .append(this.getLocation())
                .toHashCode();
    }
  
    /**
     * Set the comic.
     *
     * @param  comic  the comic.
     */
    public void setComic(Comic comic) {
        this.comic = comic;
    }
   
    /**
     * Set the grade.
     *
     * @param  grade  the grade.
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Set the location.
     *
     * @param  location  the location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Set the purchase price.
     *
     * @param  purchasePrice  the purchase price.
     */
    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = replaceNull(purchasePrice, new Double(0.0));
    }

    /**
     * Set the quantity.
     *
     * @param  quantity  the quantity.
     */
    public void setQuantity(Integer quantity) {

        if (quantity == null || quantity < 1) {
            this.quantity = 1;
        }
        else {
            this.quantity = quantity;
        }
    }

    /**
     * Set the user UUID.
     *
     * @param  userUuid  the user UUID.
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("comic", this.getComic())
                .append("grade", this.getGrade())
                .append("location", this.getLocation())
                .append("purchasePrice", this.getPurchasePrice())
                .append("quantity", this.getQuantity())
                .append("userUuid", this.getUserUuid())
                .toString();
    }
}

