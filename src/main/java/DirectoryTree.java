import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the methods to add, remove, traverse a directory structure
 */
public class DirectoryTree {

    public static final String ROOT = "/";
    public static final String DIR_PATTERN = "([a-zA-z0-9_.]+)";
    private DirectoryNode root;

    /**
     * This is the current node at which the application is
     */
    private DirectoryNode currentNode;

    public DirectoryTree() {
        root = new DirectoryNode(ROOT);
        root.setChildren(new HashSet<DirectoryNode>());
        currentNode = root;
    }

    /**
     * An overloaded method to add a directory to the end of path.
     *
     * @param path add child to the end of the path
     * @return if operation is successful
     */
    public boolean addChild(String path) {

        // find the directory to which a directory has to be added
        String pathLastButOneDir = path.substring(0, path.lastIndexOf(ROOT) + 1);
        String name = path.substring(path.lastIndexOf(ROOT) + 1, path.length());

        DirectoryNode node = find(pathLastButOneDir);
        if (node != null && !(name == null || name.length() == 0)) {
            DirectoryNode newNode = new DirectoryNode(name);
            Set<DirectoryNode> children = node.getChildren();
            if (children == null) {
                children = new HashSet<DirectoryNode>();
                node.setChildren(children);
            } else {
                for (DirectoryNode child : children) {
                    if (child.getName().equals(name))
                        return false;
                }
            }
            newNode.setParent(node);
            children.add(newNode);
            return true;
        }
        return false;
    }

    /**
     * Add a child relative to the current directory
     *
     * @param path name of the directory
     * @return if operation is successful
     */
    public boolean addChildRelativeDirectory(String path) {
        String name;
        DirectoryNode node;
        if (path.contains(ROOT)) {
            String pathLastButOneDir = path.substring(0, path.lastIndexOf(ROOT) + 1);
            name = path.substring(path.lastIndexOf(ROOT) + 1, path.length());
            node = findRelative(currentNode, pathLastButOneDir);
            if (node == null) {
                return false;
            }
        } else {
            node = currentNode;
            name = path;
        }
        if (node != null) {
            DirectoryNode newNode = new DirectoryNode(name);
            Set<DirectoryNode> children = node.getChildren();
            if (children == null) {
                children = new HashSet<DirectoryNode>();
            } else {
                for (DirectoryNode child : children) {
                    if (child.getName().equals(name))
                        return false;
                }
            }
            newNode.setParent(node);
            children.add(newNode);
        }
        return true;
    }

    /**
     * Traverse on the given path
     *
     * @param path the string representing the directory structure
     * @return if the path can be traversed
     */
    public boolean traverse(String path) {
        DirectoryNode node = find(path);
        if (node != null) {
            currentNode = node;
            return true;
        }
        return false;
    }

    /**
     * Traverse on the given path relative to the current path
     *
     * @param path the string representing the directory structure
     * @return if the path can be traversed
     */
    public boolean traverseRelative(String path) {
        DirectoryNode node = findRelative(currentNode, path);
        if (node != null) {
            currentNode = node;
            return true;
        }
        return false;
    }

    /**
     * Find a directory node with its given full path i.e. from root
     *
     * @param path path representing the directory path
     * @return directory node
     */
    private DirectoryNode find(String path) {

        if (path.equals("/"))
            return root;

        DirectoryNode node = root;

        Pattern reg = Pattern.compile(DIR_PATTERN);
        List<String> dirs = new ArrayList<String>();
        Matcher m = reg.matcher(path);
        while (m.find()) dirs.add(m.group());

        int i = 0;
        while (i < dirs.size()) {
            Set<DirectoryNode> children = node.getChildren();
            boolean found = false;
            if (children != null && children.size() > 0) {
                for (DirectoryNode child : children) {
                    if (child.getName().equals(dirs.get(i))) {
                        found = true;
                        node = child;
                        break;
                    }
                }
            }
            i++;
            if (!found) {
                // could not find on this level so break from here.
                node = null;
                break;
            }
        }
        return node;
    }


    /**
     * Find a node relative to the given node
     *
     * @param node from which the relative path has to be traversed
     * @param path the string path to be traversed
     * @return the node
     */
    private DirectoryNode findRelative(DirectoryNode node, String path) {
        DirectoryNode currentNode = node;

        Pattern reg = Pattern.compile(DIR_PATTERN);
        List<String> dirs = new ArrayList<String>();
        Matcher m = reg.matcher(path);
        while (m.find()) dirs.add(m.group());

        int i = 0;
        while (i < dirs.size()) {
            Set<DirectoryNode> children = currentNode.getChildren();
            boolean found = false;
            if (children != null) {
                for (DirectoryNode child : children) {
                    if (child.getName().equals(dirs.get(i).trim())) {
                        found = true;
                        node = child;
                        break;
                    }
                }
            }
            i++;
            if (!found) {
                node = null;
                break;
            }
        }
        return node;
    }

    /**
     * Get the current path for this node
     *
     * @return the path's name till the parent
     */
    public String getCurrentDirectoryPath() {
        DirectoryNode current = currentNode;
        List<String> parents = new ArrayList<String>();
        while (current != root) {
            parents.add(current.getName());
            current = current.getParent();
            if (current != root) {
                parents.add(ROOT);
            }
        }
        String path = "/";
        for (int i = parents.size() - 1; i >= 0; i--) {
            path += parents.get(i);
        }
        return path;
    }


    /**
     * Delete directory found at the end of this path
     *
     * @param path directory to be deleted is at the end of this path
     * @return if the operation is successful
     */
    public boolean deleteDirectory(String path) {

        DirectoryNode node = find(path);
        if (node != null && node != root) {
            node.getParent().getChildren().remove(node);
            node.setChildren(null);
            node = null;
            currentNode = root;
            return true;
        }
        return false;
    }

    /**
     * Delete directory relative to current node
     *
     * @param path directory to be deleted is at the end of this path
     * @return if the operation is successful
     */
    public boolean deleteDirectoryRelative(String path) {

        DirectoryNode node = findRelative(currentNode, path);
        if (node != null) {
            node.setChildren(null);
            return true;
        } else return false;
    }


    //########################### getters and setters #############################

    public DirectoryNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(DirectoryNode currentNode) {
        this.currentNode = currentNode;
    }

    public boolean clear() {
        root.setChildren(null);
        currentNode = root;
        return true;
    }
}
