package org.lazydog.comicbox;

import java.util.List;
import org.lazydog.comicbox.model.Entity;
import org.lazydog.repository.Criteria;


/**
 * Comic box service.
 *
 * @author  Ron Rickard
 */
public interface ComicBoxService {

    /**
     * Find the entity.
     *
     * @param  entityClass  the entity class.
     * @param  id           the ID.
     *
     * @return  the entity.
     */
    public <T extends Entity<T>> T find(Class<T> entityClass, Integer id);

    /**
     * Find the entity.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the entity.
     */
    public <T extends Entity<T>> T find(Class<T> entityClass, Criteria<T> criteria);

    /**
     * Find the first entity.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the first entity.
     */
    public <T extends Entity<T>> T findFirst(Class<T> entityClass);

    /**
     * Find the first entity.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the first entity.
     */
    public <T extends Entity<T>> T findFirst(Class<T> entityClass, Criteria<T> criteria);

    /**
     * Find the last entity.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the last entity.
     */
    public <T extends Entity<T>> T findLast(Class<T> entityClass);

    /**
     * Find the last entity.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the last entity.
     */
    public <T extends Entity<T>> T findLast(Class<T> entityClass, Criteria<T> criteria);

    /**
     * Find the list of entities.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the list of entities.
     */
    public <T extends Entity<T>> List<T> findList(Class<T> entityClass);

    /**
     * Find the list of entities.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the list of entities.
     */
    public <T extends Entity<T>> List<T> findList(Class<T> entityClass, Criteria<T> criteria);

    /**
     * Find the next entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     *
     * @return  the next entity.
     */
    public <T extends Entity<T>> T findNext(T current, Class<T> entityClass);

    /**
     * Find the next entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the next entity.
     */
    public <T extends Entity<T>> T findNext(T current, Class<T> entityClass, Criteria<T> criteria);

    /**
     * Find the previous entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     *
     * @return  the previous entity.
     */
    public <T extends Entity<T>> T findPrevious(T current, Class<T> entityClass);

    /**
     * Find the previous entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the next entity.
     */
    public <T extends Entity<T>> T findPrevious(T current, Class<T> entityClass, Criteria<T> criteria);

    /**
     * Get the criteria.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the criteria.
     */
    public <T extends Entity<T>> Criteria<T> getCriteria(Class<T> entityClass);

    /**
     * Remove the entity.
     *
     * @param  entityClass  the entity class.
     * @param  id           the ID.
     */
    public <T extends Entity<T>> void remove(Class<T> entityClass, Integer id);

    /**
     * Save the entity.
     *
     * @param  entity  the entity.
     *
     * @return  the entity.
     */
    public <T extends Entity<T>> T save(T entity);

    /**
     * Save the list of entities.
     *
     * @param  entities  the list of entities.
     *
     * @return  the list of entities.
     */
    public <T extends Entity<T>> List<T> saveList(List<T> entities);
}
