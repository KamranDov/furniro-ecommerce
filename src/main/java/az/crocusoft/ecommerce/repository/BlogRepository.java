package az.crocusoft.ecommerce.repository;

import az.crocusoft.ecommerce.model.Blog;
import az.crocusoft.ecommerce.model.BlogCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {


    List<Blog> findByDateGreaterThanEqual(Date startDate);

    @Query(value = "SELECT COUNT(b.title) FROM Blog b WHERE b.category.id = :categoryId")
    Integer countByCategoryId(@Param("categoryId") Integer categoryId);

    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);


    @Query(value = "SELECT c.name as categoryName, COUNT(b.pid) as blogCount FROM Blog b JOIN b.category c GROUP BY c.name")
    List<Map<String, Integer>> countBlogsPerCategory();


    List<Blog> findBlogPostByCategory(BlogCategory category);

}
