package org.lazydog.comicbox.internal.repository;

import javax.enterprise.context.ApplicationScoped;
import org.lazydog.comicbox.ComicBoxRepository;
import org.lazydog.repository.jpa.AbstractRepository;


/**
 * Comic box repository.
 * 
 * @author  Ron Rickard
 */
@ApplicationScoped
public class ComicBoxRepositoryImpl extends AbstractRepository implements ComicBoxRepository {
}