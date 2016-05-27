package org.lazydog.comicbox.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent an actor.
 *
 * @author  Ron Rickard
 */
public class Actor extends Entity<Actor> implements Comparable<Actor> {
    
    private static final long serialVersionUID = 1L;

    private Image image;
    @NotNull(message="Name is required.") 
    @Size(max=45, message="Name cannot contain more than 45 characters.")
    private String name;
    @NotNull(message="Version is required.")
    @Min(value=1, message="Version must be at least 1.")
    @Max(value=99, message="Version must be at most 99.")
    private Integer version;

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
    public int compareTo(Actor that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getVersion(), that.getVersion())
                .toComparison();
    }
        
    /**
     * Create a copy of this object.y.
     *
     * @return  a copy of this object.
     */
    @Override
    public Actor copy() {
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
        if (object instanceof Actor &&
            this.compareTo((Actor)object) == 0) {
            equals = true;
        }
        
        return equals;
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
     * Get the name.
     *
     * @return  the name.
     */
    public String getName() {
        return this.name;
    }
          
    /**
     * Get the version.
     *
     * @return  the version.
     */
    public Integer getVersion() {
        return this.version;
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
                .append(this.getVersion())
                .toHashCode();
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
     * Set the name.
     *
     * @param  name  the name.
     */
    public void setName(String name) {
        this.name = trimmed(name);
    }
          
    /**
     * Set the version.
     *
     * @param  version  the version.
     */
    public void setVersion(Integer version) {   
        this.version = replaceNull(version, 1);
    }
     
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("image", this.getImage())
                .append("name", this.getName())
                .append("version", this.getVersion())
                .toString();
    }
}