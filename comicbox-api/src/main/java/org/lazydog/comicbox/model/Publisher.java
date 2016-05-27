package org.lazydog.comicbox.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a publisher.
 *
 * @author  Ron Rickard
 */
public class Publisher extends Entity<Publisher> implements Comparable<Publisher> {
    
    private static final long serialVersionUID = 1L;
    
    @Valid @NotNull(message="Country is required.")
    private Country country;
    private Image image;
    private List<Imprint> imprints = new ArrayList<>();
    @NotNull(message="Name is required.") 
    @Size(max=45, message="Name cannot contain more than 45 characters.")
    private String name;

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
    public int compareTo(Publisher that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getCountry(), that.getCountry())
                .toComparison();
    }
                
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Publisher copy() {
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
        if (object instanceof Publisher &&
            this.compareTo((Publisher)object) == 0) {
            equals = true;
        }
        
        return equals;
    }
    
    /**
     * Get the country.
     *
     * @return  the country.
     */
    public Country getCountry() {
        return this.country;
    }

    /**
     * Get the image.
     *
     * @return  the image.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Get the imprints.
     * 
     * @return  the imprints.
     */
    public List<Imprint> getImprints() {
        return this.imprints;
    }

    /**
     * Get the name.
     *
     * @return  the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getName())
                .append(this.getCountry())
                .toHashCode();
    }
    
    /**
     * Set the country.
     *
     * @param  country  the country.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Set the image.
     *
     * @param  image  the image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Set the imprints.
     * 
     * @param  imprints  the imprints.
     */
    public void setImprints(List<Imprint> imprints) {
        this.imprints = replaceNull(imprints, new ArrayList<Imprint>());
    }

    /**
     * Set the name.
     *
     * @param  name  the name.
     */
    public void setName(String name) {
        this.name = trimmed(name);
    }

    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("country", this.getCountry())
                .append("image", this.getImage())
                .append("imprints", this.getImprints())
                .append("name", this.getName())
                .toString();
    }
}