package org.lazydog.comicbox.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a location.
 *
 * @author  Ron Rickard
 */
public class Location extends Entity<Location> implements Comparable<Location> {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull(message="Name is required.") 
    @Size(max=45, message="Name cannot contain more than 45 characters.")
    private String name;
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
    public int compareTo(Location that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getUserUuid(), that.getUserUuid())
                .toComparison();
    }
        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Location copy() {
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
        if (object instanceof Location &&
            this.compareTo((Location)object) == 0) {
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
                .append(this.getName())
                .append(this.getUserUuid())
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
                .append("name", this.getName())
                .append("userUuid", this.getUserUuid())
                .toString();
    }
}

