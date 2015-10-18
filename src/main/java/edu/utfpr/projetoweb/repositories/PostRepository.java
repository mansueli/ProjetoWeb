package edu.utfpr.projetoweb.repositories;

import edu.utfpr.projetoweb.entities.PostEntity;
import java.util.List;
import static org.torpedoquery.jpa.Torpedo.*;


public class PostRepository extends Repository<PostEntity>{
    private static class PostHolder {
        public static PostRepository INSTANCE = new PostRepository();
    }
    public static PostRepository getInstance() {
        return PostHolder.INSTANCE;
    }
    private PostRepository(){
        super(PostEntity.class);
    }

    public List<PostEntity> findAllbyTitle(String title){
        PostEntity from = from(PostEntity.class);
        where(from.getTitle()).like().any(title);
        List<PostEntity> result = select(from).list(em);
        return result;
    }
    
    public List<PostEntity> findAllbyCategory(String category){
        PostEntity from = from(PostEntity.class);
        where(from.getCategory()).like().any(category);
        List<PostEntity> result = select(from).list(em);
        return result;
    }
    
    public List<PostEntity> getPostsbyLikes(int pageNumber){
        PostEntity from = from(PostEntity.class);
        orderBy(desc(from.getLikes()), desc(from.getId()));
        List<PostEntity> result = select(from).setMaxResults(30).list(em);
        return result;
    }
    
    
}
