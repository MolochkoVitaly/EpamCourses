package by.bsu.internetprovider.entity;


/**
 * Class Entity ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class Entity {
    /** Field id  */
    private Long id;

    /**
     * Constructor Entity creates a new Entity instance.
     */
    public Entity() {
    }

    /**
     * Constructor Entity creates a new Entity instance.
     *
     * @param id of type Long
     */
    public Entity(Long id) {
        this.id = id;
    }

    /**
     * Method getId returns the id of this Entity object.
     *
     *
     *
     * @return the id (type Long) of this Entity object.
     */
    public Long getId() {
        return id;
    }

    /**
     * Method setId sets the id of this Entity object.
     *
     *
     *
     * @param id the id of this Entity object.
     *
     */
    public void setId(Long id) {
        this.id = id;
    }
}
