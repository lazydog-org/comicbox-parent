package org.lazydog.comicbox.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a comic grade.
 *
 * @author  Ron Rickard
 */
public class Grade extends Entity<Grade> implements Comparable<Grade> {
    
    private static final long serialVersionUID = 1L;

    @NotNull(message="Code is required.")
    @Size(max=5, message="Code cannot contain more than 5 characters.")
    private String code;
    @NotNull(message="Name is required.")
    @Size(max=20, message="Name cannot contain more than 20 characters.")
    private String name;
    @NotNull(message="Scale is required.")
    private Double scale = 0.0;

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
    public int compareTo(Grade that) {
        return new CompareToBuilder()
                .append(this.getScale(), that.getScale())
                .toComparison();
    }
        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Grade copy() {
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
        if (object instanceof Grade &&
            this.compareTo((Grade)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the code.
     *
     * @return  the code.
     */
    public String getCode() {
        return this.code;
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
     * Get the scale.
     *
     * @return  the scale.
     */
    public Double getScale() {
        return this.scale;
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
     * Set the code.
     *
     * @param  code  the code.
     */
    public void setCode(String code) {
        this.code = trimmed(code);
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
     * Set the scale.
     *
     * @param  scale  the scale.
     */
    public void setScale(Double scale) {
        this.scale = replaceNull(scale, 0.0);
    }
     
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", this.getCode())
                .append("name", this.getName())
                .append("scale", this.getScale())
                .toString();       
    }
}
