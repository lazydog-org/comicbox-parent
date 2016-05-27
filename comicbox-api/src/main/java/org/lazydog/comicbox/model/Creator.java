package org.lazydog.comicbox.model;
 
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent an creator.
 *
 * @author  Ron Rickard
 */
public class Creator  extends Entity<Creator> implements Comparable<Creator> {

    private static final long serialVersionUID = 1L;

    @Valid @NotNull(message="Person is required.")
    private Person person;
    @Valid @NotNull(message="Profession is required.")
    private Profession profession;

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
    public int compareTo(Creator that) {
        return new CompareToBuilder()
                .append(this.getPerson(), that.getPerson())
                .append(this.getProfession(), that.getProfession())
                .toComparison();
    }
                                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Creator copy() {
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
        if (object instanceof Creator &&
            this.compareTo((Creator)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the person.
     *
     * @return  the person.
     */
    public Person getPerson() {
        return this.person;
    }
      
    /**
     * Get the profession.
     *
     * @return  the profession.
     */
    public Profession getProfession() {
        return this.profession;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(61, 15)
                .append(this.getPerson())
                .append(this.getProfession())
                .toHashCode();
    }
  
    /**
     * Set the person.
     *
     * @param  person  the person.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
               
    /**
     * Set the profession.
     *
     * @param  profession  the profession.
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("person", this.getPerson())
                .append("profession", this.getProfession())
                .toString();
    }
}

