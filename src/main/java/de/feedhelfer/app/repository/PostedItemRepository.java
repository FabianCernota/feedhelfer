package de.feedhelfer.app.repository;

import de.feedhelfer.app.entity.PostedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cernota on 23.12.15.
 */
@Repository
public interface PostedItemRepository extends JpaRepository<PostedItem, Long>{
}
