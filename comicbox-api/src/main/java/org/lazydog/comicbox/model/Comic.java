package org.lazydog.comicbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a comic.
 *
 * @author  Ron Rickard
 */
public class Comic extends Entity<Comic> implements Comparable<Comic> {

    private static final long serialVersionUID = 1L;

    private List<ComicActor> comicActors = new ArrayList<>();
    private Double coverPrice = 0.0;
    private List<Creator> creators = new ArrayList<>();
    private String description;
    @Valid @NotNull(message="Distribution is required.")
    private Distribution distribution;
    @NotNull(message="Identifier is required.") 
    @Size(max=45, message="Identifier cannot contain more than 45 characters.")
    private String identifier;
    private Image image;
    @NotNull(message="Print is required.")
    @Min(value=1, message="Print must be at least 1.")
    @Max(value=99, message="Print must be at most 99.")
    private Integer print = 1;
    private Date publishDate;
    @Valid @NotNull(message="Title is required.")
    private Title title;

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
    public int compareTo(Comic that) {
        return new CompareToBuilder()
                .append(this.getTitle(), that.getTitle())
                .append(this.getIdentifier(), that.getIdentifier())
                .toComparison();
    }
        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Comic copy() {
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
        if (object instanceof Comic &&
            this.compareTo((Comic)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the comic actors.
     * 
     * @return  the comic actors.
     */
    public List<ComicActor> getComicActors() {
        return this.comicActors;
    }
    
    /**
     * Get the cover price.
     *
     * @return  the cover price.
     */
    public Double getCoverPrice() {
        return this.coverPrice;
    }

    /**
     * Get the creators.
     *
     * @return  the creators.
     */
    public List<Creator> getCreators() {
        return this.creators;
    }

    /**
     * Get the description.
     *
     * @return  the description.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Get the distribution.
     *
     * @return  the distribution.
     */
    public Distribution getDistribution() {
        return this.distribution;
    }

    public String getIdentifier() {
        return this.identifier;
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
     * Get the print.
     *
     * @return  the print.
     */
    public Integer getPrint() {
        return this.print;
    }
                 
    /**
     * Get the publish date.
     *
     * @return  the publish date.
     */
    public Date getPublishDate() {
        return this.publishDate;
    }
                      
    /**
     * Get the title.
     *
     * @return  the title.
     */
    public Title getTitle() {
        return this.title;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getTitle())
                .append(this.getIdentifier())
                .toHashCode();
    }

    /**
     * Set the comic actors.
     * 
     * @param  comicActors  the comic actors.
     */
    public void setComicActors(List<ComicActor> comicActors) {
        this.comicActors = replaceNull(comicActors, new ArrayList<ComicActor>());
    }
    
    /**
     * Set the cover price.
     *
     * @param  coverPrice  the cover price.
     */
    public void setCoverPrice(Double coverPrice) {
        this.coverPrice =  replaceNull(coverPrice, 0.0);
    }

    /**
     * Set the creators.
     *
     * @param  creators  the creators.
     */
    public void setCreators(List<Creator> creators) {
        this.creators = replaceNull(creators, new ArrayList<Creator>());
    }

    /**
     * Set the description.
     *
     * @param  description  the description.
     */
    public void setDescription(String description) {
        this.description = trimmed(description);
    }
    
    /**
     * Set the distribution.
     *
     * @param  distribution  the distribution.
     */
    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }
 
    /**
     * Set the identifier.
     * 
     * @param  identifier  the identifier.
     */
    public void setIdentifier(String identifier) {
        this.identifier = trimmed(identifier);
    }
          
    /**
     * Set the print.
     *
     * @param  print  the print.
     */
    public void setPrint(Integer print) {   
        this.print = replaceNull(print, 1);
    }
                        
    /**
     * Set the publish date.
     *
     * @param  publishDate  the publish date.
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = trimmedDate(publishDate);
    }
                         
    /**
     * Set the title.
     *
     * @param  title  the title.
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("comicActors", this.getComicActors())
                .append("coverPrice", this.getCoverPrice())
                .append("creators", this.getCreators())
                .append("description", this.getDescription())
                .append("distribution", this.getDistribution())
                .append("identifier", this.getIdentifier())
                .append("image", this.getImage())
                .append("print", this.getPrint())
                .append("publishDate", this.getPublishDate())
                .append("title", this.getTitle())
                .toString();
    }
}