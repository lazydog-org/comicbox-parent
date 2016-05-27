package org.lazydog.comicbox.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a comic actor.
 *
 * @author  Ron Rickard
 */
public class ComicActor extends Entity<ComicActor> implements Comparable<ComicActor> {
    
    private static final long serialVersionUID = 1L;

    @Valid @NotNull(message="Character is required.")
    private Actor actor;
    @Valid @NotNull(message="Comic is required.")
    private Comic comic;
    private Boolean firstAppearance;

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
    public int compareTo(ComicActor that) {
        return new CompareToBuilder()
                .append(this.getComic(), that.getComic())
                .append(this.getActor(), that.getActor())
                .toComparison();
    }
        
    /**
     * Create a copy of this object.y.
     *
     * @return  a copy of this object.
     */
    @Override
    public ComicActor copy() {
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
        if (object instanceof ComicActor &&
            this.compareTo((ComicActor)object) == 0) {
            equals = true;
        }
        
        return equals;
    }
         
    /**
     * Get the actor.
     *
     * @return  the actor.
     */
    public Actor getActor() {
        return this.actor;
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
     * Get the first appearance.
     *
     * @return  the first appearance.
     */
    public Boolean getFirstAppearance() {
        return this.firstAppearance;
    }
 
    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getComic())
                .append(this.getActor())
                .toHashCode();
    }
  
    /**
     * Set the actor.
     *
     * @param  actor  the actor.
     */
    public void setActor(Actor actor) {
        this.actor = actor;
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
     * Set the first appearance.
     *
     * @param  firstAppearance  the first appearance.
     */
    public void setFirstAppearance(Boolean firstAppearance) {   
        this.firstAppearance = replaceNull(firstAppearance, false);
    }
     
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("actor", this.getActor())
                .append("comic", this.getComic())
                .append("firstAppearance", this.getFirstAppearance())
                .toString();
    }
}