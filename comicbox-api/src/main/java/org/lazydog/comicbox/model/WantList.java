package org.lazydog.comicbox.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a want list.
 *
 * @author  Ron Rickard
 */
public class WantList extends Entity<WantList> implements Comparable<WantList> {

    private static final long serialVersionUID = 1L;
    
    @Valid @NotNull(message="Comic is required.")
    private Comic comic;
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
    public int compareTo(WantList that) {
        return new CompareToBuilder()
                .append(this.getUserUuid(), that.getUserUuid())
                .append(this.getComic(), that.getComic())
                .toComparison();
    }
                                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public WantList copy() {
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
        if (object instanceof WantList &&
            this.compareTo((WantList)object) == 0) {
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
                .append("userUuid", this.getUserUuid())
                .toString();
    }
}

