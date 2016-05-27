package org.lazydog.comicbox.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent an image.
 *
 * @author  Ron Rickard
 */
public class Image extends Entity<Image> implements Comparable<Image> {
    
    private static final long serialVersionUID = 1L;

    @NotNull(message="File name is required.") 
    @Size(max=45, message="File name cannot contain more than 45 character.")
    private String fileName;

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
    public int compareTo(Image that) {
        return new CompareToBuilder()
                .append(this.getFileName(), that.getFileName())
                .toComparison();
    }
                                    
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Image copy() {
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
        // and is equal to this object.t.
        if (object instanceof Image &&
            this.compareTo((Image)object) == 0) {
            equals = true;
        }
        
        return equals;
    }
     
    /**
     * Get the file name.
     *
     * @return  the file name.
     */
    public String getFileName() {
        return this.fileName;
    }
   
    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getFileName())
                .toHashCode();
    }

    /**
     * Set the file name.
     *
     * @param  fileName  the file name.
     */
    public void setFileName(String fileName) {
        this.fileName = trimmed(fileName);
    }
 
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fileName", this.getFileName())
                .toString();
    }
}

