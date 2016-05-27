package org.lazydog.comicbox.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent a person.
 *
 * @author  Ron Rickard
 */
public class Person extends Entity<Person> implements Comparable<Person> {
    
    private static final long serialVersionUID = 1L;

    @NotNull(message="First name is required.")
    @Size(max=45, message="First name cannot contain more than 45 characters.")
    private String firstName;
    @NotNull(message="Last name is required.")
    @Size(max=45, message="Last name cannot contain more than 45 characters.")
    private String lastName;

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
    public int compareTo(Person that) {
        return new CompareToBuilder()
                .append(this.getLastName(), that.getLastName())
                .append(this.getFirstName(), that.getFirstName())
                .toComparison();
    }
                                        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Person copy() {
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
        if (object instanceof Person &&
            this.compareTo((Person)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the first name.
     *
     * @return  the first name.
     */
    public String getFirstName() {
        return this.firstName;
    }
                   
    /**
     * Get the last name.
     *
     * @return  the last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
                return new HashCodeBuilder(61, 15)
                .append(this.getLastName())
                .append(this.getFirstName())
                .toHashCode();
    }
 
    /**
     * Set the first name.
     *
     * @param  firstName  the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = trimmed(firstName);
    }
         
    /**
     * Set the last name.
     *
     * @param  lastName  the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = trimmed(lastName);
    }

    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", this.getFirstName())
                .append("lastName", this.getLastName())
                .toString();
    }
}