package org.lazydog.comicbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a title.
 *
 * @author  Ron Rickard
 */
public class Title extends Entity<Title> implements Comparable<Title> {

    private static final long serialVersionUID = 1L;

    private List<Category> categories = new ArrayList<>();
    @Valid @NotNull(message="Format is required.")
    private Format format;
    private Image image;
    @NotNull(message="Name is required.") 
    @Size(max=45, message="Name cannot contain more than 45 characters.")
    private String name;
    private Date publishEndDate;
    private Date publishStartDate;
    @Valid @NotNull(message="Publisher is required.")
    private Publisher publisher;
    @Valid @NotNull(message="Type is required.")
    private Type type;
    @NotNull(message="Volume is required.")
    @Min(value=1, message="Volume must be at least 1.")
    @Max(value=99, message="Volume must be at most 99.")
    private Integer volume = 1;

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
    public int compareTo(Title that) {
        return new CompareToBuilder()
                .append(this.getName(), that.getName())
                .append(this.getPublisher(), that.getPublisher())
                .append(this.getVolume(), that.getVolume())
                .toComparison();
    }
                                    
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Title copy() {
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
        if (object instanceof Title &&
            this.compareTo((Title)object) == 0) {
            equals = true;
        }

        
        return equals;
    }
     
    /**
     * Get the categories.
     *
     * @return  the categories.
     */
    public List<Category> getCategories() {
        return this.categories;
    }

    /**
     * Get the format.
     *
     * @return  the format.
     */
    public Format getFormat() {
        return this.format;
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
     * Get the publish end date.
     *
     * @return  the publish end date.
     */
    public Date getPublishEndDate() {
        return this.publishEndDate;
    }
   
    /**
     * Get the publish start date.
     *
     * @return  the publish start date.
     */
    public Date getPublishStartDate() {
        return this.publishStartDate;
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
     * Get the type.
     *
     * @return  the type.
     */
    public Type getType() {
        return this.type;
    }
        
    /**
     * Get the volume.
     *
     * @return  the volume.
     */
    public Integer getVolume() {
        return this.volume;
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
                .append(this.getVolume())
                .toHashCode();
    }
        
    /**
     * Set the categories.
     *
     * @param  categories  the categories.
     */
    public void setCategories(List<Category> categories) {
        this.categories = replaceNull(categories, new ArrayList<Category>());
    }
        
    /**
     * Set the format.
     *
     * @param  format  the format.
     */
    public void setFormat(Format format) {
        this.format = format;
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
     * Set the publish end date.
     *
     * @param  publishEndDate  the publish end date.
     */
    public void setPublishEndDate(Date publishEndDate) {
        this.publishEndDate = trimmedDate(publishEndDate);
    }
  
    /**
     * Set the publish start date.
     *
     * @param  publishStartDate  the publish start date.
     */
    public void setPublishStartDate(Date publishStartDate) {
        this.publishStartDate = trimmedDate(publishStartDate);
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
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(Type type) {
        this.type = type;
    }
       
    /**
     * Set the volume.
     *
     * @param  volume  the volume.
     */
    public void setVolume(Integer volume) {
        this.volume = replaceNull(volume, 1);
    }
    
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("categories", this.getCategories())
                .append("format", this.getFormat())
                .append("image", this.getImage())
                .append("name", this.getName())
                .append("publishEndDate", this.getPublishEndDate())
                .append("publishStartDate", this.getPublishStartDate())
                .append("publisher", this.getPublisher())
                .append("type", this.getType())
                .append("volume", this.getVolume())
                .toString();
    }
}
