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
 * Entity class used to represent a country.
 *
 * @author  Ron Rickard
 */
public class Country extends Entity<Country> implements Comparable<Country> {
    
    private static final long serialVersionUID = 1L;

    @NotNull(message="Name is required.") 
    @Size(max=45, message="Name cannot contain more than 45 characters.")
    private String name;
    @Valid @NotNull(message="Publisher is required.")
    private List<Publisher> publishers = new ArrayList<>();

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
    public int compareTo(Country that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .toComparison();
    }
            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Country copy() {
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
        if (object instanceof Country &&
            this.compareTo((Country)object) == 0) {
            equals = true;
        }
        
        return equals;
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
     * Get the publishers.
     *
     * @return  the publishers.
     */
    public List<Publisher> getPublishers() {
        return this.publishers;
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
                .toHashCode();
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
     * Set the publishers.
     *
     * @param  publishers  the publishers.
     */
    public void setPublishers(List<Publisher> publishers) {
        this.publishers = replaceNull(publishers, new ArrayList<Publisher>());
    }

    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", this.getName())
                .append("publishers", this.getPublishers())
                .toString();
    }
}
