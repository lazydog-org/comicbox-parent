package org.lazydog.comicbox.model;

import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Entity class used to represent preference.
 *
 * @author  Ron Rickard
 */
public class Preference extends Entity<Preference> implements Comparable<Preference> {

    private static final long serialVersionUID = 1L;

    @Valid @NotNull(message="Distribution is required.")
    private Distribution distribution;
    @Valid @NotNull(message="Format is required.")
    private Format format;
    @Valid @NotNull(message="Grade is required.")
    private Grade grade;
    @NotNull(message="Minimum publish date is required.")
    private Date minimumPublishDate;
    @Valid @NotNull(message="Publisher is required.")
    private Publisher publisher;
    @Valid @NotNull(message="Type is required.")
    private Type type;
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
    public int compareTo(Preference that) {
        return new CompareToBuilder()
                .append(this.getUserUuid(), that.getUserUuid())
                .toComparison();
    }

    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Preference copy() {
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
        if (object instanceof Preference &&
            this.compareTo((Preference)object) == 0) {
            equals = true;
        }

        return equals;
    }

    /**
     * Get the distribution.
     *
     * @return  the distribution.
     */
    public Distribution getDistribution() {
        return this.distribution;
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
     * Get the grade.
     *
     * @return  the grade.
     */
    public Grade getGrade() {
        return this.grade;
    }

    /**
     * Get the minimum publish date.
     *
     * @return  the minimum publish date.
     */
    public Date getMinimumPublishDate() {
        return this.minimumPublishDate;
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
                .toHashCode();
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
     * Set the format.
     *
     * @param  format  the format.
     */
    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * Set the grade.
     *
     * @param  grade  the grade.
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Set the minimum publish date.
     *
     * @param  minimumPublishDate  the minimum publish date.
     */
    public void setMinimumPublishDate(Date minimumPublishDate) {
        this.minimumPublishDate = trimmedDate(minimumPublishDate);
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
                .append("distribution", this.getDistribution())
                .append("format", this.getFormat())
                .append("grade", this.getGrade())
                .append("minimumPublishDate", this.getMinimumPublishDate())
                .append("publisher", this.getPublisher())
                .append("type", this.getType())
                .append("userUuid", this.getUserUuid())
                .toString();
    }
}
