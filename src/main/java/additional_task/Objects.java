package additional_task;

import java.util.List;

public class Objects {
    private int id;
    private Integer parentId;
    private List<Objects> children;

    public Objects(int id, Integer parentId, List<Objects> children) {
        this.id = id;
        this.parentId = parentId;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public List<Objects> getChildren() {
        return children;
    }

    public void setChildren(List<Objects> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Objects{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
