import java.util.Set;

/**
 * Class representing the directory node of the file system.
 * A real world directory consists of some directories and files in a directory.
 */
public class DirectoryNode {

    /**
     * String representing the name of the directory
     */
    private String name;

    /**
     * A logical entity to show the data in a directory.
     * Ideally the entities in the directories are files.
     */
    private Set<Integer> data;

    /**
     * Pointing the parent of this directory which is one step above in the hierarchy
     */
    private DirectoryNode parent;

    /**
     * The directory nodes a directory contains in itself.
     */
    private Set<DirectoryNode> children;


    /**
     * Constructs a new directory with no parent and no children.
     * No parents denotes that it is a root directory.
     *
     * @param name The name of the directory
     */
    public DirectoryNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getData() {
        return data;
    }

    public void setData(Set<Integer> data) {
        this.data = data;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    public void setParent(DirectoryNode parent) {
        this.parent = parent;
    }

    public Set<DirectoryNode> getChildren() {
        return children;
    }

    public void setChildren(Set<DirectoryNode> children) {
        this.children = children;
    }
}
