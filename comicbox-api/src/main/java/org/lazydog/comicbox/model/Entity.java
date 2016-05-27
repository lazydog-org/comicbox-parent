package org.lazydog.comicbox.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * Generic entity.
 * 
 * @author  Ron Rickard
 * 
 * @param  <T>  the type of the entity.
 */
public abstract class Entity<T extends Entity<T>> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date createTime;
    private Integer id;
    private Date updateTime;

    /**
     * Create a copy of this object.
     * 
     * @return  a copy of this object.
     */
    abstract public T copy();

    /**
     * Get the create time.
     * 
     * @return  the create time.
     */
    public Date getCreateTime() {
        return this.createTime;
    }
    
    /**
     * Get the ID.
     *
     * @return  the ID.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Get the update time.
     * 
     * @return  the update time.
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * Replace the original object with the replacement object if the original object is null.
     * 
     * @param <U>
     * @param <V>
     * @param  original     the original object.
     * @param  replacement  the replacement object.
     * 
     * @return  the original object if it is not null, otherwise the replacement object.
     * 
     * @throws  IllegalArgumentException  if the replacement object is null.
     */
    protected static <U, V extends U> U replaceNull(final U original, final V replacement) {
    
        // Check if the replacement object is null.
        if (replacement == null) {
            throw new IllegalArgumentException("The replacement object cannot be null.");
        }
        
        return (original == null) ? replacement : original;
    }

    /**
     * Set the create time.
     * 
     * @param  createTime  the create time.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
     * Set the ID.
     *
     * @param  id  the ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set the update time.
     * 
     * @param  updateTime  the update time.
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Get the trimmed string.
     *
     * @param  value  the string to trim.
     *
     * @return  the trimmed string.
     */
    protected static String trimmed(String value) {
        return (value != null) ? value.trim() : null;
    }
   
    /**
     * Get the trimmed date.
     * 
     * A trimmed date has only the month and year.
     * 
     * @param  date  the date to trim.
     * 
     * @return  the trimmed date.
     */
    protected static Date trimmedDate(Date date) {
        
        Date trimmedDate = null;
        
        if (date != null) {
            // Declare.
            Calendar dateAsCalendar;

            // Initialize.
            dateAsCalendar = Calendar.getInstance();
            
            // Preserve the month and year only.
            dateAsCalendar.setTime(date);
            dateAsCalendar.set(Calendar.DAY_OF_MONTH, 1);
            dateAsCalendar.set(Calendar.HOUR_OF_DAY, 0);
            dateAsCalendar.set(Calendar.MINUTE, 0);
            dateAsCalendar.set(Calendar.SECOND, 0);
            dateAsCalendar.set(Calendar.MILLISECOND, 0);
            
            trimmedDate = dateAsCalendar.getTime();
        }
        
        return trimmedDate;
    }

    /**
     * Validate this object.
     * 
     * @return  a set of constraint violations or an empty set if there are no
     *          constraint violations.
     */
    @SuppressWarnings("unchecked")
    public Set<ConstraintViolation<T>> validate() {

        // Get the validator factory.
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        // Get the validator for this object type.
        Validator validator = validatorFactory.getValidator();

        // Validate this object.
        Set<ConstraintViolation<T>> constraintViolations = validator.validate((T)this);

        return constraintViolations;
    }
}
