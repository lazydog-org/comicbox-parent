package org.lazydog.comicbox.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent an imprint.
 *
 * @author  Ron Rickard
 */
public class Imprint extends Entity<Imprint> implements Comparable<Imprint> {
    
    private static final long serialVersionUID = 1L;
    
    private Image image;
    @NotNull(message="Name is required.") 
    @Size(max=45, message="Name cannot contain more than 45 characters.")
    private String name;
    @Valid @NotNull(message="Publisher is required.")
    private Publisher publisher;

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
    public int compareTo(Imprint that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getPublisher(), that.getPublisher())
                .toComparison();
    }
                
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Imprint copy() {
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
        if (object instanceof Imprint &&
            this.compareTo((Imprint)object) == 0) {
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
     * Get the publisher.
     *
     * @return  the publisher.
     */
    public Publisher getPublisher() {
        return this.publisher;
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
                .append(this.getPublisher())
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
     * Set the publisher.
     *
     * @param  publisher  the publisher.
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
                .append("publisher", this.getPublisher())
                .toString();
    }
}